# 기계학습 01반
# 201702052 이동헌
# 1 주차 과제(1) - 1부터 입력 받은 n까지 짝수의 합 출력

def main():
	print("숫자를 입력하세요: ")
	input_x = int(input())
	count = 0
	sum = 0
	while(count <= input_x):
		if (count%2 == 0):
			sum += count
		count += 1

	print("sum:", sum)



if __name__ == '__main__':
	main()
	
