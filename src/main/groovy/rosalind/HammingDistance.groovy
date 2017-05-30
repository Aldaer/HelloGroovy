package rosalind

Scanner sc = new Scanner(System.in)
String s1 = sc.nextLine()
String s2 = sc.nextLine()
def dist = 0
(0..s1.length() - 1).each { i -> if (s1[i] != s2[i]) dist++ }
println dist
