from phue import Bridge
import random
import time, threading

b = Bridge('192.168.1.18') 

lights_list = b.get_light_objects('list')

def blink():
  for light in lights_list:
    light.on = True
    light.bri = 254
    light.xy = [random.random(), random.random()]

def off():
  for light in lights_list:
    light.on = False
    

while(True):
  blink()
  time.sleep(2)
  off()
  time.sleep(1)
