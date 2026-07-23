#!/bin/bash

# Directory containing this script.
script_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Load the reusable display function.
messages_file="$script_directory/messages.sh"

if [[ ! -f "$messages_file" ]]; then
    echo "Error: messages.sh was not found." >&2
    exit 1
fi

source "$messages_file"

# Check that exactly one argument was provided.
if [[ $# -ne 1 ]]; then
    echo "Usage: $0 <ELF_file>" >&2
    exit 1
fi

elf_file="$1"
file_name="$(basename "$elf_file")"

# Check whether the file exists.
if [[ ! -f "$elf_file" ]]; then
    echo "Error: File '$elf_file' does not exist." >&2
    exit 1
fi

# Check whether the file is readable.
if [[ ! -r "$elf_file" ]]; then
    echo "Error: File '$elf_file' is not readable." >&2
    exit 1
fi

# Check whether the file has a valid ELF header.
if ! readelf -h "$elf_file" >/dev/null 2>&1; then
    echo "Error: File '$elf_file' is not a valid ELF file." >&2
    exit 1
fi

# Extract the required ELF header information.
magic_number="$(
    readelf -h "$elf_file" |
    awk '/Magic:/ {
        for (i = 2; i <= NF; i++) {
            printf "%s%s", $i, (i < NF ? " " : "")
        }
        print ""
    }'
)"

class="$(
    readelf -h "$elf_file" |
    awk -F: '/Class:/ {
        value = $2
        sub(/^[[:space:]]+/, "", value)
        print value
    }'
)"

byte_order="$(
    readelf -h "$elf_file" |
    awk -F: '/Data:/ {
        value = $2
        sub(/^[[:space:]]+/, "", value)

        if (value ~ /little endian/) {
            print "Little Endian"
        } else if (value ~ /big endian/) {
            print "Big Endian"
        } else {
            print value
        }
    }'
)"

entry_point_address="$(
    readelf -h "$elf_file" |
    awk -F: '/Entry point address:/ {
        value = $2
        sub(/^[[:space:]]+/, "", value)
        print value
    }'
)"

# Display the information using messages.sh.
display_elf_header_info
