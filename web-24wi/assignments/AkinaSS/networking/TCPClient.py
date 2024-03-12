#!/usr/bin/env python3
from socket import *
import os
serverName = "10.14.10.222"
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName,serverPort))
filePath = input("Input file path: ")
print(filePath)
file = open(filePath, "rb")
print(file)
fileName = os.path.basename(filePath)
print(fileName)
clientSocket.send(fileName.encode())
clientSocket.send(file.read())
clientSocket.close()
