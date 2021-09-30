import socket
from random import *

TCP_IP = '127.0.0.1'
TCP_PORT = 5001

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((TCP_IP, TCP_PORT))

sock.listen()

while True:
	conn_1, addr_1 = sock.accept()
	print('Connection address : ' + str(addr_1))
	conn_2, addr_2 = sock.accept()
	print('Connection address : ' + str(addr_2))
	conn_3, addr_3 = sock.accept()
	print('Connection address : ' + str(addr_3))

	# Send Start message
	start_msg = 'Okay... All players have gathered. Start the game.\n'
	conn_1.send(start_msg.encode())
	conn_2.send(start_msg.encode())
	conn_3.send(start_msg.encode())

	# Checked 3 of Clients
	input_msg = 'Please select 1 number from 1 to 10.\n'
	
	conn_1.send(input_msg.encode())
	num_1 = conn_1.recv(1024).decode()
	
	conn_2.send(input_msg.encode())
	num_2 = conn_2.recv(1024).decode()
	
	conn_3.send(input_msg.encode())
	num_3 = conn_3.recv(1024).decode()

	# Checked all input 
	chose_msg = 'Do you want multiply or add...?'
	
	conn_1.send(chose_msg.encode())
	str_1 = conn_1.recv(1024).decode()
	
	conn_2.send(chose_msg.encode())
	str_2 = conn_2.recv(1024).decode()
	
	conn_3.send(chose_msg.encode())
	str_3 = conn_3.recv(1024).decode()


	# Create random int
	rand_1 = randint(-1, 4)
	rand_2 = randint(-1, 4)
	rand_3 = randint(-1, 4)
	
	# Result message
	won_msg = 'Congratulations. You won!'
	lose_msg = 'Unfortunately, you have been defeated.'

	# Calculate
	if(str_1 == 'multiply'):
		result_1 = int(num_1) * rand_1
	elif(str_1 == 'add'):
		result_1 = int(num_1) + rand_1
	else:
		print('Wrong input')

	if(str_2 == 'multiply'):
		result_2 = int(num_2) * rand_2
	elif(str_2 == 'add'):
		result_2 = int(num_2) + rand_2
	else:
		print('Wrong input')
	
	if(str_3 == 'multiply'):
		result_3 = int(num_3) * rand_3
	elif(str_3 == 'add'):
		result_3 = int(num_3) + rand_3
	else:
		print('Wrong input')

	max_num = result_1

	if(result_2 > max_num):
		conn_2.send(won_msg.encode())
		conn_1.send(lose_msg.encode())
		conn_3.send(lose_msg.encode())
	elif(result_3 > max_num):
		conn_3.send(won_msg.encode())
		conn_1.send(lose_msg.encode())
		conn_2.send(lose_msg.encode())
	else:
		conn_1.send(won_msg.encode())
		conn_2.send(lose_msg.encode())
		conn_3.send(lose_msg.encode())


conn_1.close()
conn_2.close()
conn_3.close()
