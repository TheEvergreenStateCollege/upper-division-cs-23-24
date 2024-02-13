# TCP client

import socket

# Define server address and port
SERVER_ADDRESS = '127.0.0.1'
SERVER_PORT = 12345

# Create a TCP socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to the server
client_socket.connect((SERVER_ADDRESS, SERVER_PORT))

# Send data to the server
data_to_send = "Hello from client!"
client_socket.send(data_to_send.encode())

# Receive response from the server
response = client_socket.recv(1024)
print("Received response from server:", response.decode())

# Close the connection
client_socket.close()