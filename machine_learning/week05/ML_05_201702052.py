import pandas as pd
import numpy as np

from sklearn.ensemble import BaggingClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import KFold
from sklearn.metrics import accuracy_score

# Check & Divide Data
# 바로 읽은 wdbc.data
wdbc = pd.read_csv('./wdbc.data', header=None)
#print(wdbc)

dataset = np.array(wdbc)

# col 0 : ID
# col 1 : Target (M: 악성, B: 양성)
# col 2~31 : Data

patient_id = dataset[:, 0]
target = dataset[:, 1]
data = dataset[:, 2:]

# Split Data
train_input, test_input, train_target, test_target = train_test_split(data, target, random_state=0)

# 5-Cross Validation
kfold = KFold(n_splits=5, shuffle=True, random_state=0)

# Decision Tree
tree = DecisionTreeClassifier(random_state=0)

# Random Forest
forest_1 = RandomForestClassifier(n_estimators=1, random_state=0)
forest_100 = RandomForestClassifier(n_estimators=100, random_state=0)

#bagging = BaggingClassifier(DecisionTreeClassifier(), n_estimators=5, random_state=0)

score_tree = cross_val_score(tree, train_input, train_target, cv=kfold)
score_forest_1 = cross_val_score(forest_1, train_input, train_target, cv=kfold)
score_forest_100 = cross_val_score(forest_100, train_input, train_target, cv=kfold)

print("Decision Tree 교차 검증 점수: ", score_tree)
print("Random Forest 1 교차 검증 점수: ", score_forest_1)
print("Random Forest 100 교차 검증 점수: ", score_forest_100)

# Validate Test Data Using Best Model
# Train
forest_100.fit(train_input, train_target)

# Accuracy
predict = forest_100.predict(test_input)
accuracy = accuracy_score(test_target, predict)

print(f"Test Data에 대한 성능: {accuracy:.8}")

#[Plus] List Compare : 1 on 1 (predicted : test data)
'''print(predict)
print(test_target,'\n')'''
count = 0

for i in range(len(test_target)):
    if (predict[i] != test_target[i]):
        count += 1

valid_err = (1 - (count / len(test_target)))
print(f"검증 성능: {valid_err:.8}", )





