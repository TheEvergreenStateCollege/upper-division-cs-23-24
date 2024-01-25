import socket
def tcp_server():
    server_address = 'localhost'
    server_port = 60000  #  Change as needed, the port to listen on. (non-privileged ports are > 1023)

    # Create a TCP/IP socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        # Bind the socket to the server address and the server port
        s.bind((server_address, server_port))


        # Next, Listen for incoming connections
        s.listen()
        print(f"Server listening on {server_address}:{server_port}...")

        # Wait for a connection
        connection, client_address = s.accept()
        with connection:
            print(f"Connected to {client_address}")


            # Receive the data in small chunks and print it
            while True:
                data = connection.recv(1024)
                if not data:
                    break
            print(f"Received: {data.decode()}")


if __name__ == '__main__':
    tcp_server()