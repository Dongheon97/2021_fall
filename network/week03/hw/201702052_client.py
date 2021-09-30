import socket

TCP_IP = '127.0.0.1'
TCP_PORT = 5001

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((TCP_IP, TCP_PORT))

# start message 
start_msg = sock.recv(1024)
print(start_msg.decode())

# input int 1 ~ 10
input_msg = sock.recv(1024)
print(input_msg.decode())

input_num = input('Number : ')
sock.send(input_num.encode())

print('You chose the number %s. Please wait.' %input_num)

# receive choosing message
chose_msg = sock.recv(1024)
print(chose_msg.decode())

# choosing multi / add
input_str = input('multiply or add : ')
sock.send(input_str.encode())
print('Okay... please wait.')

# receive game result message
result_msg = sock.recv(1024)
print(result_msg.decode())

sock.close()
