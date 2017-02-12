package algorithms.implementations

Scanner sc = new Scanner(System.in)
def letterWidth = 1
List<Integer> letterHeights = sc.nextLine().tokenize(" ").collect { it as int }
def word = sc.nextLine()
int offset = (int) ('a' as char)
def maxH = word.chars.collect { c -> c - offset as int }.collect({ i -> letterHeights[i] }).max()

println maxH * word.size() * letterWidth
