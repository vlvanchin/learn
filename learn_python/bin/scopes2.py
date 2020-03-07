# local versus  global

def local():
    print (m,'print from the local scope')

m = 5
print (m, 'print from the global scope')

# calling local
local()

