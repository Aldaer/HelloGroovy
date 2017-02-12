package algorithms.warmup

Scanner sc = new Scanner(System.in)
int n = sc.nextInt()
sc.nextLine()
a = sc.nextLine().tokenize(" ")*.toInteger()
res = [0,0,0]
a.each { int x-> res[x <=> 0]++}
println res

String s
s.su