# <h1>Machine Learning Week06 : K-Means Clustering</h1>
import numpy as np
import pandas as pd
from sklearn.cluster import KMeans
from sklearn.metrics.cluster import adjusted_rand_score
from matplotlib import pyplot as plt

iris = pd.read_csv('./iris.csv', header=None)
#print(iris)


# <h2>HW1. Original Iris Data</h2>
red = iris[4] == 1
green = iris[4] == 2
blue = iris[4] == 3

# x = iris[0], y = iris[1]
plt.scatter(iris[0], iris[1], red, c='r')
plt.scatter(iris[0], iris[1], green, c='g')
plt.scatter(iris[0], iris[1], blue, c='b')
plt.show()


# <h2>HW2. K-Means Clustering</h2>
dataset = np.array(iris)
training = dataset[:, 0:4]
#print(training)

model = KMeans(n_clusters=3, random_state=0)

# iris에 담긴 각 훈련데이터 포인트에 클러스터 레이블이 할당된다.
model.fit(training)

# 학습된 모델의 target 정보는 KMeans 클래스 객체의 labels_ 속성에 저장
#print(model.labels_)

# x = iris[0], y = iris[1]
plt.scatter(iris[0], iris[1], model.labels_==0, c='g')
plt.scatter(iris[0], iris[1], model.labels_==1, c='r')
plt.scatter(iris[0], iris[1], model.labels_==2, c='b')
plt.show()

# 학습된 모델의 알고리즘이 반복한 횟수는 KMeans 클래스 객체의 n_iter_ 속성에 저장
print(model.n_iter_)

print(adjusted_rand_score(iris[4], model.labels_))


# <h2>HW3. Compare k values</h2>
# 비지도 학습의 결과를 실제 정답 cluster와 비교하여 평가할 수 있는 지표
# 2개의 클러스터링 결과를 비교할 때도 사용

model_2 = KMeans(n_clusters=2, random_state=0)
model_2.fit(training)
print("n clusters가 2일 때: {:.2f}".format(adjusted_rand_score(iris[4], model_2.labels_)))

model_3 = KMeans(n_clusters=3, random_state=0)
model_3.fit(training)
print("n clusters가 3일 때: {:.2f}".format(adjusted_rand_score(iris[4], model_3.labels_)))

model_4 = KMeans(n_clusters=4, random_state=0)
model_4.fit(training)
print("n clusters가 4일 때: {:.2f}".format(adjusted_rand_score(iris[4], model_4.labels_)))

model_5 = KMeans(n_clusters=5, random_state=0)
model_5.fit(training)
print("n clusters가 5일 때: {:.2f}".format(adjusted_rand_score(iris[4], model_5.labels_)))

model_6 = KMeans(n_clusters=6, random_state=0)
model_6.fit(training)
print("n clusters가 6일 때: {:.2f}".format(adjusted_rand_score(iris[4], model_6.labels_)))

