Scanner sc = new Scanner(System.in)
def n = sc.nextInt()
int[] c = new int[101]
(1..n).each { c[sc.nextInt()]++ }
print c.collect {it >> 1}.sum()
