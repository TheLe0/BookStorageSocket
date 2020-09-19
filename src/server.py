import socket 
import sys

from settings import IP, SERVER_PORT 

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((IP, int(SERVER_PORT))) 
server.listen(10)

print("The TCP Server was Started!")

try:
    while True:
        s, client = server.accept()
        data = s.recv(1024)
        data = data + '-pong'
        s.send(data)
        s.close()
except:
    print("\nServer shutdown by keyboard exit!")
    sys.exit(0)
finally:
    server.close()

