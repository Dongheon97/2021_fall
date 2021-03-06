import socket
import select
import time

def select_sr(csocket, addr):
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
    #req_clients = list()
    while True:
        try:
            input_list = [server_socket]
            input_ready, write_ready, except_ready = select.select(input_list, [], []) 
            for sock in input_ready:
                    if sock == server_socket: 
                        csocket, addr = server_socket.accept()
                        input_list.append(csocket)
                    for sock_in_list in input_list:
                        if sock_in_list != server_socket and sock_in_list != sock:
                            try:
                                select_sr(sock_in_list, addr)    
                            except:
                                input_list.remove(sockt_in_list)
        except:
            server_socket.close()

if __name__ == '__main__':
    port = 8894
    main(port)
