Scanner sc = new Scanner(System.in)
def readIntArray = { sc.nextLine().tokenize(" ").collect { it as int } }
def (n, k, q) = readIntArray()
int[] array = readIntArray()
def queries = []
for (int i in 0..q - 1) {
    queries << sc.nextInt()
}
queries.each {
    int index = (it - k) % n        // Works for negative index because a[-1] == last element of the array
    println array[index]
}
