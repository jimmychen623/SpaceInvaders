import serial
import sys
import time
ser = serial.Serial("COM4", 115200)
print(ser.is_open)
sys.stdout.flush()
time.sleep(0.3)
curr = "Z"
prev = "X"
i = 500
current_milli_time = lambda: int(round(time.time() * 1000))
while( i > 0):
	print(current_milli_time())
	time.sleep(0.07)
	raw_value = ser.readline()
	string_value = str(raw_value).rstrip("\r\n")
	#raw_value.decode("utf-8").rstrip("\r\n")

	if(prev == string_value or string_value == "" or string_value == "True" or string_value is None):
		print(string_value + "\n")
		sys.stdout.flush()
	else:
		prev = string_value
		print(prev + "\n")
		sys.stdout.flush()
	i = i - 1

print("CLOSING SERIAL CONNECTION")
ser.close()
