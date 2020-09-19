import socket 
import sys

from settings import IP, SERVER_PORT 

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect((IP, SERVER_PORT)) 

print("The Client connected to the server!")

client.send('Ping')
data = client.recv(1024)
client.close()

print 'Recebi: %s' % data

