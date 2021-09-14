import numpy as np
import pandas as pd

iris = pd.read_csv("./small_iris.csv", header=None)
data = np.array(iris)

setosa = iris[4] == 1
versicolor = iris[4] == 2
virginia = iris[4] == 3

avg_seto = np.average(data[setosa, 0:4], axis=0)
avg_vers = np.average(data[versicolor, 0:4], axis=0)
avg_virg = np.average(data[virginia, 0:4], axis=0)

print('클래스 1의 평균 벡터는', avg_seto)
print('클래스 2의 평균 벡터는', avg_vers)
print('클래스 3의 평균 벡터는', avg_virg)
