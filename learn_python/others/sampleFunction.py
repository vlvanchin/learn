def fun(name, location,year=2016):
	print "%s/%s/%d" % (name,location,year)

fun("van","sg")

fun(location="L.A",year=2004,name="susi")

fun("bagsub", year=2000, location="singapore")

tuple1 = ("sample company", "bishan",2007)
fun(*tuple1)


dictionary1 = {
	'name': 'python',
	'location': 'orland',
	'year': 2003
	}
fun(**dictionary1)

def square (x):
	return x * x

squared = square(12)
print "squared:", squared
