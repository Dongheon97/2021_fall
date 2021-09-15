import cv2
import numpy as np

def my_first_filtering(src):
    kernel = np.ones((3,3), np.float32)/9

    return cv2.filter2D(src, -1, kernel, borderType=cv2.BORDER_CONSTANT)

src = np.array([[0,0,0,0,0],
                [15, 16, 0, 0, 0],
                [10, 11, 12, 13, 14],
                [5, 6, 7, 8, 9],
                [0, 1, 2, 3, 4]], dtype=np.uint8)

dst = my_first_filtering(src)

print("Input:\n {}".format(src))
print("Output:\n {}".format(dst))