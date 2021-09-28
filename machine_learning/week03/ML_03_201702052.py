import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

# HW01 : train & test of KNeighborclassifier Accuracy
iris = pd.read_csv("./iris.csv", header=None)
dataset = np.array(iris)

# split data, target
data = dataset[:, 0:4]
target = dataset[:, 4]

# tarin_test_split
train_input, test_input, train_target, test_target = train_test_split(data, target, random_state=0)

# k = 1
kn_1 = KNeighborsClassifier()
kn_1.n_neighbors = 1
# train
kn_1.fit(train_input, train_target)

# k = 5
kn_5 = KNeighborsClassifier()
kn_5.n_neighbors = 5
# train
kn_5.fit(train_input, train_target)

# k = 10
kn_10 = KNeighborsClassifier()
kn_10.n_neighbors = 10
# train
kn_10.fit(train_input, train_target)

print("k=1일 때 테스트 셑의 정확도: {:.2f}".format(kn_1.score(test_input, test_target)))
print("k=5일 때 테스트 셑의 정확도: {:.2f}".format(kn_5.score(test_input, test_target)))
print("k=10일 때 테스트 셑의 정확도: {:.2f}".format(kn_10.score(test_input, test_target)))

# HW02 : Normalization
iris_mod = pd.read_csv("./iris_mod.csv", header=None)
dataset_mod = np.array(iris_mod)

data_mod = dataset_mod[:, 0:4]
target_mod = dataset_mod[:, 4]

# get : min, MAX
data_min = data_mod.min(axis = 0)
data_max = data_mod.max(axis = 0)

# normalization
data_norm = (data_mod - data_min) / (data_max - data_min)

# tarin_test_split
mod_train_input, mod_test_input, mod_train_target, mod_test_target = train_test_split(data_mod, target_mod, random_state=0)
norm_train_input, norm_test_input, norm_train_target, norm_test_target = train_test_split(data_norm, target_mod, random_state=0)

# before normaliation
mod_kn = KNeighborsClassifier()
mod_kn.n_neighbors = 1
mod_kn.fit(mod_train_input, mod_train_target)

# after normalization
norm_kn = KNeighborsClassifier()
norm_kn.n_neighbors = 1
norm_kn.fit(norm_train_input, norm_train_target)

print("k=1일 때 normalization 수행 전 테스트 셑의 정확도: {:.2f}".format(mod_kn.score(mod_test_input, mod_test_target)))
print("k=1일 때 normalization 수행 후 테스트 셑의 정확도: {:.2f}".format(norm_kn.score(norm_test_input, norm_test_target)))