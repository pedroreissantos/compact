section .bss
	$buf2	resb	2
	$bt	resb	4

section .text
	global _exit, _mainCRTStartup, _prints, _readb
	extern _GetStdHandle@4, _WriteFile@20, _ReadFile@20, _ExitProcess@4
	extern _GetCommandLineA@0, _GetEnvironmentStringsA@0
	extern _strlen, _env, _main

_prints:
	push	dword 0
	push	dword $bt
	push	dword [esp+12]		; pushd 'prints' first argument
	call	_strlen
	pop	edx	; save string
	push	eax	; push length
	push	edx	; push the string back in correct order
	push	dword -11	; STD_OUTPUT_HANDLE: CONOUT$
	call	_GetStdHandle@4	; do not pop args: stdcall
	push	eax
	call	_WriteFile@20	; stdcall
	ret

_readb:
	push	ebx
	push	ecx
	push	edx
	push	dword -10
	call	_GetStdHandle@4	; do not pop args: stdcall
	push	dword 0
	push	dword $bt
	push	dword 1
	push	dword $buf2
	push	eax
	call	_ReadFile@20	; stdcall
	xor	eax, eax
	mov	al, [$buf2]
	pop	edx
	pop	ecx
	pop	ebx
	ret

_exit:
	push	dword [esp+4]
	call	_ExitProcess@4
	jmp	_exit

_mainCRTStartup:	
	push	dword 0		; NULL pointer for envp
	mov	ebp, esp	; init frame pointer (uses instruction above)
	call	_GetEnvironmentStringsA@0
	mov	edx, eax
.envp:	push	edx
	call	_strlen
	add	edx, eax
	inc	edx
	cmp	[edx], byte 0
	jne	.envp
	mov	edi, esp
	call	_GetCommandLineA@0
	mov	esi, eax	; str
	mov	edx, esi	; s
	xor	ecx, ecx	; argc
	push	dword 0		; NULL pointer for argv[argc]
.args0:	cmp	[edx], byte 0	; while (*s != 0) s++;
	je	.skip
	inc	edx
	jmp	.args0
.skip:	dec	edx
	cmp	[edx], byte 0x20 ; while (*s == ' ') *s-- = 0;
	jne	.str
	mov	[edx], byte 0
	jmp	.skip
.str:	cmp	[edx], byte 0x22	; if (*s == '"') *s-- = 0;
	jne	.args1
	mov	[edx], byte 0
	dec	edx
.str1:	cmp	edx, esi	; while (s >= str && *s != '"') s--;
	jl	.args2
	cmp	[edx], byte 0x22
	je	.str2
	dec	edx
	jmp	.str1
.str2:	mov	[edx], byte 0x20
.args1:	cmp	edx, esi	; while (s >= str && *s != ' ') s--;
	jl	.args2
	cmp	[edx], byte 0x20
	je	.args2
	dec	edx
	jmp	.args1
.args2: mov	eax, edx	; *sp-- = s+1;
	inc	eax
	push	eax
	inc	ecx		; argc++;
	cmp	[edx], byte 0x20	; if (*s == ' ') *s = 0;
	jne	.args3
	mov	[edx], byte 0
.args3:	dec	edx		; while (--s >= str && *s == ' ') ;
	cmp	edx, esi
	jl	.args4
	cmp	[edx], byte 0x20
	je	.args3
.args4:	cmp	edx, esi	; } while (s >= str);
	jge	.args1
	inc	edx
	mov	ebx, esp
	push	ecx		; argc
	mov	[_env], esp
	push	edi		; envp
	push	ebx		; argv
	push	ecx		; argc
; truncate real to integer conversions
	fstcw	word [buf2]
	or	word [buf2], 0x0C00
	fldcw	word [buf2]
; _start:
	call	_main
	push	eax
	call	_exit
