def a(s):
	print s
	

def switch (ch):
	try:
		{
			'1': lambda : a('one'),
			'2': lambda : a('two'),
			'a': lambda : a("letter a")
		}[ch]()
	except KeyError:
		a("key not found")
	
switch('1')

switch('a')

switch('b')