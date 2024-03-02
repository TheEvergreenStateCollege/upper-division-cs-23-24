# This TCP server logs the connection of the client once they connect to the server.
# The server logs the date, time, Ip address, and Port of the client.
# The client is prompted to enter a sentence.
# If entered properly, the server will return the sentence in all capital letters.
# If the server is unable to process the sentence or the message is dropped,
# A message will print in the server's terminal.

from socket import *
import sys
import datetime

serverName = '127.0.0.1'
serverPort = 5000
serverSocket = socket(AF_INET, SOCK_STREAM)


def log_message(action, client_info, msg=""):
    time_now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print(f"{time_now} - {action} - {client_info}{' : ' + msg if msg else ''}")


try:
    serverSocket.bind((serverName, serverPort))
except OSError as e:
    print(f"Server failed to bind on port {serverPort}: {e}")
    sys.exit(1)

serverSocket.listen(1)
print('The server is ready to receive')

try:
    while True:
        connectionSocket, addr = serverSocket.accept()
        client_info = f"{addr[0]}:{addr[1]}"
        log_message("connection from", client_info)

        connectionOpen = True

        while connectionOpen:
            try:
                sentence = connectionSocket.recv(1024).decode('utf-8')
                if not sentence or sentence.lower() == 'quit':
                    connectionOpen = False
                    break
                
                # Validate input: only allow alphanumeric characters and basic punctuation
                if not all(char.isalnum() or char.isspace() or char in ",.!?'" for char in sentence):
                    log_message("Invalid message from", client_info, sentence)
                    continue

                log_message("Message received from", client_info, sentence)

                capitalizedSentence = sentence.upper()
                connectionSocket.send(capitalizedSentence.encode())
            except IOError as e:
                log_message("IOError, connection lost with", client_info)
                connectionOpen = False


finally:
    serverSocket.close()
