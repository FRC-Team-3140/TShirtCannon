'''
    MUST RUN A SROOT (sudo)
'''

# Resolve potentially missing, non-default python packages.
import time
import sys
import subprocess
import importlib

# Install system-level dependencies
try:
    subprocess.check_call(["dpkg", "-s", "libportaudio2"])
except subprocess.CalledProcessError:
    print("Installing libportaudio2...")
    subprocess.check_call(["sudo", "apt-get", "install", "-y", "libportaudio2"])

# Resolve potentially missing, non-default python packages.
# Resolve potentially missing, non-default python packages.
required = {'rpi_ws281x', 'adafruit-blinka', 'adafruit-circuitpython-neopixel', 'pynetworktables', 'noise'}
for package in required:
    try:
        importlib.import_module(package)
    except ImportError:
        print(f"Installing missing package: {package}")
        subprocess.check_call([sys.executable, "-m", "pip", "install", package])


from networktables import NetworkTables
import board
import neopixel
import colorsys
import math
import random
from noise import pnoise1


# Initialize NetworkTables
NetworkTables.initialize(server='roborio-3140-frc.local')
table = NetworkTables.getTable('LEDControl')

# LED strip configuration:
LED_COUNT = 83            # Number of LED pixels.
LED_PIN = board.D12       # GPIO pin connected to the pixels (PWM capable pin, here GPIO 16).
LED_BRIGHTNESS = 255      # Set to 0 for darkest and 255 for brightest
LED_ORDER = neopixel.GRB  # Strip color ordering (RGB, GRB, etc.)

# Create the NeoPixel object with appropriate configuration.
#strip = Adafruit_NeoPixel(LED_PIN, LED_COUNT, brightness=LED_BRIGHTNESS, auto_write=False, pixel_order=LED_ORDER)
strip = neopixel.NeoPixel(LED_PIN, LED_COUNT, brightness=LED_BRIGHTNESS, auto_write=False, pixel_order=LED_ORDER)

# LED Strip sections 
TOP = [ 69, 83 ]     # Last 12 LEDs | 42 TOP
MID = [ 37, 69 ]     # Middle 32
BOTTOM = [ 0, 37 ]   # First 38

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''
* None of the water methods actually show the changes   *
* they just calculate the new values for the led's!     *
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''

scale = 0.1  # Adjust this for the smoothness of the effect
offset = 0   # To animate, this offset will be incremented over time

def water_noise(): 
    global scale
    global offset
    
    # Must add one because the end is exclusive
    for i in range(BOTTOM[0], (BOTTOM[1] + 1)):
        blue_value = int((pnoise1(i * scale + offset) + 1) * 127.5)  # Perlin noise output [-1, 1] scaled to [0, 255]
        strip[i] = (0, 0, blue_value)

speed = 0.1  # Speed of the wave
wavelength = 5  # How many LEDs per cycle of the wave
offset = 0

def water_sign(): 
    global speed 
    global wavelength
    global offset
    
    # Must add one because the end is exclusive
    for i in range(BOTTOM[0], (BOTTOM[1] + 0)):
        blue_value = int((math.sin((i / wavelength) + offset) + 1) * 127.5)  # Sin output [-1, 1] scaled to [0, 255]
        strip[i] = (0, 0, blue_value)

def water_rand(): 
    # Must add one because the end is exclusive
    for i in range(BOTTOM[0], (BOTTOM[1] + 0)):
        strip[i] = (0, 0, random.randint(0, 255))

# Default LED mode
# For "default" rainbow cycle mode
hue = 0
rainbowFirstPixelHue = 0
ledRainbowSpan = 5

def default(): 
    global hue
    global rainbowFirstPixelHue
    global ledRainbowSpan
    
    # Loop through the specified LED section and set colors
    # Must add one because the end is exclusive
    for i in range(MID[0], (TOP[1] + 0)):
        hue = (rainbowFirstPixelHue + i * ledRainbowSpan) % 180  # Hue calculation

        # Convert hue (in the range 0-180) to RGB using colorsys
        r, g, b = colorsys.hsv_to_rgb(hue / 180, 1, 0.5)  # Saturation=1, Value=0.5
        r, g, b = int(r * 255), int(g * 255), int(b * 255)  # Convert to 0-255 range

        strip[i] = (r, g, b)  # Set the color on the strip

    # Increment the hue for the next iteration
    rainbowFirstPixelHue = (rainbowFirstPixelHue + 3) % 180

    water_noise()

    # Send the data to the LED strip
    strip.show()

# LED Control Methods
def set_Color_Solid(): 
    strip.fill(table.getInt("R", 0), table.getInt("G", 0), table.getInt("B", 0))
    strip.show()

def get_mode_and_timestamp():
    mode = table.getNumber('mode', 0)
    timestamp = table.getNumber('timestamp', 0)
    return mode, timestamp

def led_task(mode):
    if mode == 1: 
        default()
    elif mode == 2: 
        set_Color_Solid()
    else: 
        pass

while True:
    mode, timestamp = get_mode_and_timestamp()
    current_time = time.time() * 1000  # Convert to milliseconds
    if current_time >= timestamp:
        led_task(mode)
    default()
    #print('running')
    #strip.fill((255, 0, 0))
    strip.show()
    time.sleep(0.1)  # Adjust the sleep time as needed
