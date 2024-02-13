#TCP server

from socket import *
from random import *

SERVER_PORT = 12000
SERVER_NAME  = ''

server = socket(AF_INET, SOCK_STREAM)

server.bind(SERVER_NAME, SERVER_PORT)
print('ready')
server.listen(3)

while True:
    connectionSocket, addr = server.accept()
    data_rec = connectionSocket.recv(1024).decode()
    if Random.random() < 0.1:
        new_message = data_rec.upper()
        server.send(new_message.encode())
    connectionSocket.close()