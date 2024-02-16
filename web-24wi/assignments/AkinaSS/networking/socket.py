'''Udp socket'''

from socket import *

SERVER_NAME = 'I don\'t know'
SERVER_PORT = 12000
server = socket(AF_INET, SOCK_DGRAM)

server.bind((SERVER_NAME, SERVER_PORT))
print('ready')

while True:
    message, address = server.recvfrom(2048)
    text = message.decode()
    new_text = text.upper()
    server.sendto(address, new_text.encode("utf-8"))