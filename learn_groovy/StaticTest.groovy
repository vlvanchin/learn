static {
    def num = 1
    def num2 = 2
    def total = sum (num, num2)
    
    def int sum (n1, n2) {
        return n1 + n2;
    }
    
    println "total = ${total}";
}