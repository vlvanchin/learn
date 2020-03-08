try:
	f = open ("sample.py")
	print(f.read())
	print(1/0)
finally:
	f.close()
	