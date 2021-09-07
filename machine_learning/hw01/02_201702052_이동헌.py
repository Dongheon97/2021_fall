# 기계학습 02반
# 201702052 이동헌
# 1 주차 과제(2) - 딕셔너리 자료형을 이용

def main():
	# Dictionary 자료형을 이용하여 student 변수에 (과목:성적)쌍으로 저장
	student = {"국어": 85, "영어": 70, "수학": 63, "과학": 59, "사회": 100}
	
	# 각각의 성적을 키값(과목)으로 얻어 출력
	print("국어: %d, 영어: %d, 수학: %d,  과학: %d, 사회: %d" \
			%(student["국어"], student["영어"], student["수학"], student["과학"], student["사회"]))
	
	max_lecture = ""	# 최대 점수 과목명
	max_score = 0.0		# 점수의 최대값
	sum = 0				# 점수 합계

	for key, value in student.items():
		sum += value
		if (max_score < value):
			max_lecture = key
			max_score = value

	# 가장 점수가 높은 과목의 과목명과 점수를 출력
	print("가장 점수가 높은 과목: (%s, %d)" %(max_lecture, max_score))

	# 모든 과목의 평균 점수
	print("평균 점수: %d" %(sum/len(student)))


		

if __name__ == '__main__':
	main()
