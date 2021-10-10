import select
import socket
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
    # 소켓 리스트
    input_list = [server_socket]
    req_clients = list()
    try:
        while True:
            # select : 클라이언트의 요청을 서버를 이용하기 전 중간 관리자 역할을 진행 
            input_ready, write_ready, except_ready = select.select(input_list, [], [])
            for socket in input_ready:
                csocket, addr = server_socket.accept()
                input_list.append(csocket)
                select_sr(csocket)
                socket.close()

    except:
        input_list.remove(socket)

if __name__ == '__main__':
    port = 8892
    main(port)
