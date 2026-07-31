#!/usr/bin/env python3

import angr
import claripy


BINARY_PATH = "./Dy_task2"

# Le programme accède aux indices 0 à 29.
FLAG_LENGTH = 30


def main() -> None:
    project = angr.Project(
        BINARY_PATH,
        auto_load_libs=False,
    )

    # 30 caractères symboliques.
    flag = claripy.BVS("flag", FLAG_LENGTH * 8)

    # Ajout explicite du caractère nul final pour argv[1].
    symbolic_argument = claripy.Concat(
        flag,
        claripy.BVV(0, 8),
    )

    state = project.factory.full_init_state(
        args=[
            BINARY_PATH,
            symbolic_argument,
        ]
    )

    # Le binaire impose déjà que le premier caractère soit H,
    # mais on l'ajoute pour accélérer la résolution.
    state.solver.add(flag.get_byte(0) == ord("H"))

    # Tous les caractères doivent être imprimables.
    for index in range(FLAG_LENGTH):
        character = flag.get_byte(index)
        state.solver.add(character >= 0x20)
        state.solver.add(character <= 0x7E)

    simulation = project.factory.simgr(state)

    print("[*] Résolution en cours...")
    print("[*] Cette étape peut prendre un certain temps.")

    simulation.explore(
        find=lambda current_state: (
            b"GG you can submit with this flag"
            in current_state.posix.dumps(1)
        ),
        avoid=lambda current_state: (
            b"Wrong flag!"
            in current_state.posix.dumps(1)
        ),
    )

    if not simulation.found:
        print("[-] Aucune solution trouvée.")
        print(f"[-] États actifs : {len(simulation.active)}")
        print(f"[-] États morts : {len(simulation.deadended)}")
        return

    found_state = simulation.found[0]
    solution = found_state.solver.eval(flag, cast_to=bytes)

    print("[+] Solution trouvée :", solution)
    print("[+] Flag :", solution.decode(errors="replace"))


if __name__ == "__main__":
    main()
