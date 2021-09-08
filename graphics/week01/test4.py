import cv2
import numpy as np

img = np.zeros((4, 4, 3), dtype=np.uint8)

img[:, :, 2] = 255

print(img[:, : 0])
print(img[:, : 1])
print(img[:, : 2])

cv2.imshow("img", img)

cv2.waitKey()
cv2.destroyAllWindows()