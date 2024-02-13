from socket import *
import sys

serverName = 'Localhost'
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)

try:
    serverSocket.bind((serverName, serverPort))
except OSError as e:
    print(f"Server failed to bind on port {serverPort}: {e}")
    sys.exit(1)

serverSocket.listen(1)
print('The server is listening')

try:
    while True:
        connectionSocket, addr = serverSocket.accept()
        print(f"Connection from {addr}")

        try:
            sentence = connectionSocket.recv(1024).decode()
            if not sentence:
                break
            capitalizedSentence = sentence.upper()
            connectionSocket.send(capitalizedSentence.encode())
        except IOError as e:
            print(f"IOError: {e}")
        finally:
            connectionSocket.close()
except KeyboardInterrupt:
    print("\nServer is shutting down.")
finally:
    serverSocket.close()
