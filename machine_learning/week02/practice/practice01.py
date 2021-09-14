import numpy as np
from matplotlib import pyplot as plt
import pandas as pd

# pandas
data1 = [1, 3, 5, 7, 9]
s = pd.Series(data1)
print(s)

data2 = {
    'year': [2016, 2017, 2018],
    'GDP rate': [2.8, 3.1, 3.0],
    'GDP': ['1.637M', '1.73M', '1.83M']
}
df = pd.DataFrame(data2)
print(df)
print(df['year'])


# matplotlib
plt.plot([1, 2, 3], [110, 130, 120])
#plt.show()

# 산점도
plt.scatter([1, 2, 3], [1, 1, 1], color='r')
plt.scatter([1, 2, 3], [2, 2, 2], color='b')
#plt.show()

# numpy
list1 = [1, 2, 3, 4]
a = np.array(list1)
#print(a.shape)

b = np.array([[1, 2, 3], [4, 5, 6]])
#print(b.shape)
#print(b[0, 0])

c = np.eye(3)
#print(c)

d = np.array(range(20)).reshape((4, 5))
#print(d)
#np.savetxt("./test.csv", d, delimiter=',')

axis0 = np.sum(d, axis=0)
axis1 = np.sum(d, axis=1)
print(axis0)
print(axis1)

