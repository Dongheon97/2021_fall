import socket
import time

def main(port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('', port))
    server_socket.listen()

    while True:
        (csocket, address) = server_socket.accept()
        data = csocket.recv(1024)
        print(f"[client {address} Info] {data.decode()}")
        res = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n"
        csocket.send(res.encode('utf-8'))
        csocket.send(data)
        csocket.close()

if __name__ == '__main__':
    port = 8889
    main(port)
