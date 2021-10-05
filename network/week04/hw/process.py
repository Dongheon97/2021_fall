from multiprocessing import Process
import socket
import time

def multiprocess_sr(csocket, addr):
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
        proc = Process(target=multiprocess_sr, args=(csocket, addr,))
        req_clients.append(proc)
        proc.start()

    except:
        proc.join()

if __name__ == '__main__':
    port = 8890
    main(port)
