import socket

TCP_IP = '168.188.129.210'
TCP_PORT = 5001

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((TCP_IP, TCP_PORT))
sock.listen()

conn, addr = sock.accept()
print('Connection address : ' + str(addr))
while True:
    data = conn.recv(1024)
    if not data: break
    print('Connection address : ' + data.decode())
    conn.send(data)
conn.close()

