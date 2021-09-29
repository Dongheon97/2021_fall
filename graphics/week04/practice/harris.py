import cv2
import numpy as np

def main():
    src = cv2.imread('../hw/zebra.png')
    gray = cv2.cvtColor(src, cv2.COLOR_BGR2GRAY).astype(np.float32)

    dst = cv2.cornerHarris(gray, 3, 3, 0.04)

    cv2.imshow('original', src)
    har = ((dst - np.min(dst))/np.max(dst - np.min(dst))*255 + 0.5).astype(np.uint8)
    cv2.imshow('har', har)

    dst[dst < 0.01 * dst.max()] = 0

    interest_points = np.zeros((dst.shape[0], dst.shape[1], 3))
    interest_points[dst != 0] = [0, 0, 255]
    cv2.imshow('interest_points', interest_points)

    src[dst != 0] = [0, 0, 255]
    cv2.imshow('harris', src)

    cv2.waitKey()
    cv2.destroyAllWindows()

if __name__ == '__main__':
    main()