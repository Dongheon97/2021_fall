import matplotlib.pyplot as plt
import numpy as np
a = range(0, 100, 10)
b = range(0, 100, 10)

c = np.random.randint(100, size=20)
d = np.random.randint(100, size=20)

plt.plot(a, b)
plt.scatter(c, d)
plt.show()