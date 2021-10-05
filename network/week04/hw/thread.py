from threading import Thread
import socket
import time

def multithread_sr(csocket, addr):
    data = csocket.recv(1024)
    print(f"[Client {addr} Info] {data.decode()}")
    res = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n"
    csocket.send(res.encode('utf-8'))
    csocket.send(data)
    csocket.close()
        

def main(port):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('', port))
    server_socket.listen()
    req_clients = list()

    try:
        csocket, addr = server_socket.accept()
        th = Thread(target=multithread_sr, args=(csocket, addr,))
        req_clients.append(th)
        th.start()

    except:
        th.join()

if __name__ == '__main__':
    port = 8891
    main(port)
