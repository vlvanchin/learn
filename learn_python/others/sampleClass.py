class testClass (object):

    print "creating a new class\n---------------------"

    number = 5

    def __init__(self, string):
        self.string = string

    def printClass(self):
        print "number = %d"% self.number
        print "string = %s"% self.string

tc = testClass("five")
tc.printClass()
tc.number =10
tc.string = "ten"
tc.printClass()
