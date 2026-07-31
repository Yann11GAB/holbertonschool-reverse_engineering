#!/usr/bin/env python3

import glob
import re
import subprocess


def get_main(binary):
    result = subprocess.run(
        ["objdump", "-d", "-M", "intel", binary],
        capture_output=True,
        text=True,
        check=True,
    )

    match = re.search(
        r"^[0-9a-fA-F]+ <main>:\n"
        r"(.*?)(?=^[0-9a-fA-F]+ <[^>]+>:|\Z)",
        result.stdout,
        re.MULTILINE | re.DOTALL,
    )

    if not match:
        raise ValueError("main introuvable")

    return match.group(1)


def extract_instructions(main_code):
    instructions = []

    for line in main_code.splitlines():
        match = re.match(
            r"\s*[0-9a-fA-F]+:\s+"
            r"(?:[0-9a-fA-F]{2}\s+)+\s*(.+)",
            line,
        )

        if match:
            instructions.append(match.group(1).strip())

    return instructions


def parse_number(value):
    return int(value.strip(), 0)


def normalize_register(register):
    aliases = {
        "al": "eax",
        "ah": "eax",
        "ax": "eax",
        "eax": "eax",
        "rax": "eax",

        "bl": "ebx",
        "bh": "ebx",
        "bx": "ebx",
        "ebx": "ebx",
        "rbx": "ebx",

        "cl": "ecx",
        "ch": "ecx",
        "cx": "ecx",
        "ecx": "ecx",
        "rcx": "ecx",

        "dl": "edx",
        "dh": "edx",
        "dx": "edx",
        "edx": "edx",
        "rdx": "edx",

        "sil": "esi",
        "si": "esi",
        "esi": "esi",
        "rsi": "esi",

        "dil": "edi",
        "di": "edi",
        "edi": "edi",
        "rdi": "edi",
    }

    return aliases.get(register, register)


def solve_binary(binary):
    main_code = get_main(binary)
    instructions = extract_instructions(main_code)

    # Chaque valeur est représentée ainsi :
    #
    # coefficient * caractère + constante
    #
    # Le caractère lu vaut donc :
    # (1, 0)
    #
    # Exemple :
    # caractère - 5 devient :
    # (1, -5)

    registers = {}
    stack_values = {}

    for instruction in instructions:

        # Exemple :
        # mov DWORD PTR [rbp-0xc],0x5
        match = re.match(
            r"mov\s+"
            r"(?:BYTE PTR |WORD PTR |DWORD PTR |QWORD PTR )?"
            r"\[(rbp|rsp)([+-]0x[0-9a-fA-F]+)\],"
            r"\s*(0x[0-9a-fA-F]+|\d+)$",
            instruction,
        )

        if match:
            location = match.group(1) + match.group(2)
            value = parse_number(match.group(3))
            stack_values[location] = (0, value)
            continue

        # Exemple :
        # movzx eax,BYTE PTR [rbp-0xd]
        #
        # La variable absente de stack_values est normalement
        # le caractère lu avec scanf.
        match = re.match(
            r"movzx\s+([a-z0-9]+),"
            r"\s*BYTE PTR \[(rbp|rsp)([+-]0x[0-9a-fA-F]+)\]$",
            instruction,
        )

        if match:
            destination = normalize_register(match.group(1))
            location = match.group(2) + match.group(3)

            if location in stack_values:
                registers[destination] = stack_values[location]
            else:
                registers[destination] = (1, 0)

            continue

        # Exemple :
        # movsx eax,al
        # movsx edx,al
        match = re.match(
            r"movsx\s+([a-z0-9]+),\s*([a-z0-9]+)$",
            instruction,
        )

        if match:
            destination = normalize_register(match.group(1))
            source = normalize_register(match.group(2))

            if source in registers:
                registers[destination] = registers[source]

            continue

        # Exemple :
        # mov eax,DWORD PTR [rbp-0xc]
        match = re.match(
            r"mov\s+([a-z0-9]+),"
            r"\s*(?:BYTE PTR |WORD PTR |DWORD PTR |QWORD PTR )?"
            r"\[(rbp|rsp)([+-]0x[0-9a-fA-F]+)\]$",
            instruction,
        )

        if match:
            destination = normalize_register(match.group(1))
            location = match.group(2) + match.group(3)

            if location in stack_values:
                registers[destination] = stack_values[location]

            continue

        # Exemple :
        # mov eax,edx
        match = re.match(
            r"mov\s+([a-z0-9]+),\s*([a-z0-9]+)$",
            instruction,
        )

        if match:
            destination = normalize_register(match.group(1))
            source = normalize_register(match.group(2))

            if source in registers:
                registers[destination] = registers[source]

            continue

        # Exemple :
        # mov eax,0x5
        match = re.match(
            r"mov\s+([a-z0-9]+),"
            r"\s*(0x[0-9a-fA-F]+|\d+)$",
            instruction,
        )

        if match:
            destination = normalize_register(match.group(1))
            value = parse_number(match.group(2))
            registers[destination] = (0, value)
            continue

        # Exemple :
        # add eax,DWORD PTR [rbp-0xc]
        # sub eax,DWORD PTR [rbp-0xc]
        match = re.match(
            r"(add|sub)\s+([a-z0-9]+),"
            r"\s*(?:BYTE PTR |WORD PTR |DWORD PTR |QWORD PTR )?"
            r"\[(rbp|rsp)([+-]0x[0-9a-fA-F]+)\]$",
            instruction,
        )

        if match:
            operation = match.group(1)
            destination = normalize_register(match.group(2))
            location = match.group(3) + match.group(4)

            if destination not in registers:
                continue

            if location not in stack_values:
                continue

            dest_coefficient, dest_constant = registers[destination]
            src_coefficient, src_constant = stack_values[location]

            if operation == "add":
                registers[destination] = (
                    dest_coefficient + src_coefficient,
                    dest_constant + src_constant,
                )
            else:
                registers[destination] = (
                    dest_coefficient - src_coefficient,
                    dest_constant - src_constant,
                )

            continue

        # Exemple :
        # add eax,edx
        # sub eax,edx
        match = re.match(
            r"(add|sub)\s+([a-z0-9]+),\s*([a-z0-9]+)$",
            instruction,
        )

        if match:
            operation = match.group(1)
            destination = normalize_register(match.group(2))
            source = normalize_register(match.group(3))

            if destination not in registers:
                continue

            if source not in registers:
                continue

            dest_coefficient, dest_constant = registers[destination]
            src_coefficient, src_constant = registers[source]

            if operation == "add":
                registers[destination] = (
                    dest_coefficient + src_coefficient,
                    dest_constant + src_constant,
                )
            else:
                registers[destination] = (
                    dest_coefficient - src_coefficient,
                    dest_constant - src_constant,
                )

            continue

        # Exemple :
        # add eax,0x5
        # sub eax,0x5
        match = re.match(
            r"(add|sub)\s+([a-z0-9]+),"
            r"\s*(0x[0-9a-fA-F]+|\d+)$",
            instruction,
        )

        if match:
            operation = match.group(1)
            destination = normalize_register(match.group(2))
            value = parse_number(match.group(3))

            if destination not in registers:
                continue

            coefficient, constant = registers[destination]

            if operation == "add":
                constant += value
            else:
                constant -= value

            registers[destination] = (coefficient, constant)
            continue

        # Exemple :
        # cmp eax,0x43
        match = re.match(
            r"cmp\s+([a-z0-9]+),"
            r"\s*(0x[0-9a-fA-F]+|\d+)$",
            instruction,
        )

        if match:
            register = normalize_register(match.group(1))
            target = parse_number(match.group(2))

            if register not in registers:
                continue

            coefficient, constant = registers[register]

            # On ne retient que les expressions qui dépendent
            # réellement du caractère utilisateur.
            if coefficient == 0:
                continue

            numerator = target - constant

            if numerator % coefficient != 0:
                continue

            character_value = numerator // coefficient

            if not 0 <= character_value <= 255:
                continue

            character = chr(character_value)

            return (
                character,
                coefficient,
                constant,
                target,
            )

    raise ValueError("formule arithmétique non reconnue")


def main():
    binaries = sorted(
        glob.glob("Dy_task4/binary_[0-9][0-9][0-9]")
    )

    if not binaries:
        print("[ERREUR] Aucun binaire trouvé dans Dy_task4/")
        return

    flag = []
    failures = []

    for binary in binaries:
        try:
            character, coefficient, constant, target = solve_binary(binary)
            flag.append(character)

            print(
                f"[OK] {binary}: "
                f"{coefficient}*x + ({constant}) = {target} "
                f"=> {character!r}"
            )

        except Exception as error:
            flag.append("?")
            failures.append(binary)

            print(
                f"[ERREUR] {binary}: {error}"
            )

    final_flag = "".join(flag)

    print("\nFLAG COMPLET :")
    print(final_flag)

    if failures:
        print(
            f"\nBINAIRES NON RÉSOLUS : {len(failures)}"
        )

        for binary in failures:
            print(binary)
    else:
        print("\nTous les binaires ont été résolus.")

    with open(
        "4-flag.txt",
        "w",
        encoding="utf-8",
    ) as output:
        output.write(final_flag + "\n")


if __name__ == "__main__":
    main()
