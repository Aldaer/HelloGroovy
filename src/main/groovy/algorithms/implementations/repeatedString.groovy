package algorithms.implementations

Scanner sc = new Scanner(System.in)
String s = sc.nextLine()
long n = sc.nextLong()
int l = s.length()
int nA = s.findAll('a').size()
long nAs = n.intdiv(l) * nA
nAs += s.substring(0, n % l as int).findAll('a').size()
println nAs


