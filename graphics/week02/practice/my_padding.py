import cv2
import numpy as np

def my_padding(src, pad_shape, pad_type = 'zero'):
    (h, w) = src.shape
    (p_h, p_w) = pad_shape
    pad_img = np.zeros((h + 2 * p_h, w + 2 * p_w))
    pad_img[p_h:h+p_h, p_w:w+p_w] = src

    if pad_type == 'repetition':
        print('repetition padding')
        # up
        pad_img[:p_h, p_w:p_w+w] = src[0, :]
        # down
        pad_img[p_h+h:, p_w:p_w+w] = src[h-1, :]
        # left
        pad_img[:, :p_w] = pad_img[:, p_w:p_w+1]
        # right
        pad_img[:, p_w+w:] = pad_img[:, p_w+w-1:p_w+w]

    else:
        print('zero padding')

    return pad_img

def my_filtering(src, kernel):
    (h, w) = src.shape
    (k_h, k_w) = kernel.shape
    pad_img = my_padding(src, kernel.shape, 'repetition')
    dst = np.zeros((h, w)) # output

    for m in range(h):
        for n in range(w):
            sum = 0
            for k in range(k_h):
                for l in range(k_w):
                    sum += kernel[k, l] * pad_img[m+k, n+l]
            dst[m, n] = sum
    dst = (dst+0.5).astype(np.uint8)
    return dst

if __name__ == '__main__':
    src = cv2.imread("Lena.png", cv2.IMREAD_GRAYSCALE)

    kernel = np.ones((3, 3), np.float32)/9
    my_filtered_img = my_filtering(src, kernel)

    cv2.imshow('original', src)
    cv2.imshow('my filtering img', my_filtered_img)
    cv2.waitKey()
    cv2.destroyAllWindows()