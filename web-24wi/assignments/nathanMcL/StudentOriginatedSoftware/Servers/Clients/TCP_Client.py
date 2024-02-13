import socket


def tcp_client():
    server_address = 'localhost'
    server_port = 46716

    # TCP/IP socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        # Connect to the server's address and port
        s.connect((server_address, server_port))

        while True:
            # Prompts user to enter a message
            message = input("Enter message to send to server (type 'exit' to quit): ")
            if message.lower() == 'exit':
                break

            # Send the data
            s.sendall(message.encode())


if __name__ == '__main__':
    tcp_client()
