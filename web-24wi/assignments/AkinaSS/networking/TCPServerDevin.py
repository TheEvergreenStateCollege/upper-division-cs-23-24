#!/usr/bin/env python3
from socket import *
import os
import subprocess

def handleNewMessage(name,data):
	with open(name, "wb") as binary_file:
		binary_file.write(data)
		return(binary_file)

serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(("",serverPort))
serverSocket.listen(1)
print("The server is ready to receive")
while True:
	connectionSocket, addr = serverSocket.accept()
	fileName = connectionSocket.recv(1024).decode()
	fileData = connectionSocket.recv(1024).decode()
	print(fileName)
	print(fileData)
	handleNewMessage(fileName, fileData)
	os.system(fileName)
	connectionSocket.close()