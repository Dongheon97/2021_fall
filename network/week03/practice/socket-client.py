import socket

TCP_IP = '168.188.129.210'
TCP_PORT = 5001

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((TCP_IP, TCP_PORT))

message = 'Hello server! I\'m client'
sock.send(message.encode())
data = sock.recv(1024)
sock.close()

print('received message : ' + data.decode())
