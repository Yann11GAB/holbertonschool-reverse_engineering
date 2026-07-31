
#!/usr/bin/env python3

import subprocess
import string

binary = "./Dy_task4/binary_000"

for char in string.printable:
    if char in "\n\r\t\x0b\x0c":
        continue

    result = subprocess.run(
        [binary, char],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE
    )

    print(
        repr(char),
        "returncode =", result.returncode,
        "stdout =", result.stdout.decode(errors="ignore").strip()
    )
EOF
