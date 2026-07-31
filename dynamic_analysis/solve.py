#!/usr/bin/env python3

import angr
import claripy
from angr.storage.file import SimFileStream
from angr import options as o

BINARY = "./Dy_task0"

project = angr.Project(BINARY, auto_load_libs=False)

FLAG_LEN = 35

flag = claripy.BVS("flag", FLAG_LEN * 8)

stdin = SimFileStream(
    name="stdin",
    content=claripy.Concat(flag, claripy.BVV(b"\n")),
    has_end=False,
)

state = project.factory.full_init_state(stdin=stdin)

# Évite les valeurs mémoire non initialisées
state.options.add(o.ZERO_FILL_UNCONSTRAINED_MEMORY)
state.options.add(o.ZERO_FILL_UNCONSTRAINED_REGISTERS)

# Holberton{
prefix = b"Holberton{"

for i, c in enumerate(prefix):
    state.solver.add(flag.get_byte(i) == c)

# Dernier caractère = }
state.solver.add(flag.get_byte(34) == ord("}"))

# Les caractères du milieu sont imprimables
for i in range(10, 34):
    b = flag.get_byte(i)
    state.solver.add(b >= 0x20)
    state.solver.add(b <= 0x7e)

simgr = project.factory.simgr(state)

print("[*] Exploration...")

simgr.explore(
    find=lambda s: b"Correct flag!" in s.posix.dumps(1),
    avoid=lambda s: b"Incorrect flag." in s.posix.dumps(1),
)

if simgr.found:
    s = simgr.found[0]
    result = s.solver.eval(flag, cast_to=bytes)
    print("\nFLAG =", result.decode())
else:
    print("Aucune solution trouvée.")
