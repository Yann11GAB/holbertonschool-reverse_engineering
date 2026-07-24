section .data
    obfuscated_flag db 0x8a, 0x101, 0x11e, 0x178, 0x163, 0x108, 0x136, 0x101, 0x104, 0x12d, 0x178, 0x17f, 0x165, 0x11d, 0x171, 0x136, 0x101, 0x171, 0x17f, 0x135, 0x135, 0x163, 0x11b, 0x178, 0x11e, 0x127, 0x3f, 0x12b
    divisor dd 3
    user_input db 128

section .bss
    input resb 128

section .text
    global _start

_start:
    mov esi, input
    mov edi, obfuscated_flag
    xor ecx, ecx

check_loop:
    mov al, [esi + ecx]
    mov ebx, [edi + ecx * 4]
    xor ebx, 0x55
    sub ebx, 7
    mov edx, ebx
    cdq
    idiv dword [divisor]
    cmp al, dl
    jne fail
    inc ecx
    cmp ecx, 28
    jl check_loop

success:
    mov eax, 1
    int 0x80

fail:
    mov eax, 1
    int 0x80
