import socket
import sys

# Server settings
serverName = 'localhost'
serverPort = 50000
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Binding the server socket to the specified port
try:
    serverSocket.bind((serverName, serverPort))
    print(f"Server is listening on port {serverPort}")
except OSError as e:
    print(f"Error: Unable to bind to port {serverPort}: {e}")
    sys.exit(1)

# Server main loop
try:
    while True:
        message, clientAddress = serverSocket.recvfrom(2048)

        # Log the received message
        print(f"Received message: {message.decode()}")

        # Process the message and send it back
        modifiedMessage = message.decode().upper()  # Returns client message capitalized
        serverSocket.sendto(modifiedMessage.encode(), clientAddress)

except KeyboardInterrupt:
    print("\nServer is shutting down.")
finally:
    serverSocket.close()
