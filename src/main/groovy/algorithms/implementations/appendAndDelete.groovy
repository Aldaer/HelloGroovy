package algorithms.implementations

Scanner sc = new Scanner(System.in)
String s = sc.nextLine()
String t = sc.nextLine()
int k = sc.nextInt()
int minL = Math.min(s.length(), t.length())
int m  = 0
while (m < minL && s.charAt(m) == t.charAt(m)) m++

boolean can = (k >= s.length() + t.length()) || {
    int delta = s.length() + t.length() - 2 * m
    (k >= delta) && (((k - delta) & 1) == 0)
}.call()
println can ? 'Yes' : 'No'