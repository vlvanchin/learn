# local, enclosing and global

def enclosing_func():
    m = 13
    def local():
        # m does not belong to the scope defined by the local
        # function so py will look for the enclosing scope
        print(m, 'printing from the local scope')

    # calling local function
    local()

m = 5
print (m, 'printin from the global scope')

enclosing_func()

