# TCP server

import socket

# Define server address and port
SERVER_ADDRESS = '127.0.0.1'
SERVER_PORT = 12345

# Create a TCP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to the address and port
server_socket.bind((SERVER_ADDRESS, SERVER_PORT))

# Listen for incoming connections
server_socket.listen(1)

print("TCP server is listening on {}:{}".format(SERVER_ADDRESS, SERVER_PORT))

# Accept incoming connection
client_socket, client_address = server_socket.accept()
print("Connection established with client:", client_address)

# Receive data from client
data = client_socket.recv(1024)
print("Received data from client:", data.decode())

# Send response back to client
response = "Hello from server!"
client_socket.send(response.encode())

# Close the connection
client_socket.close()
server_socket.close()