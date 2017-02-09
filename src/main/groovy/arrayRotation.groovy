Scanner sc = new Scanner(System.in)
def (n, k, q) = sc.nextLine().tokenize(" ")*.toInteger()
int[] array = sc.nextLine().tokenize(" ")*.toInteger()
def queries = []
for (int i in 0..q - 1) {
    queries << sc.nextInt()
}
for (int i in 0..q - 1) {
    int index = (queries[i] - k) % n        // Works for negative index because a[-1] == last element of the array
    println array[index]
}
