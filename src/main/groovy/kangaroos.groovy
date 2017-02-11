Scanner sc = new Scanner(System.in)
def (x1, v1, x2, v2) = (0..3).collect({ sc.nextInt() })
boolean canDo
if (v1 == v2) canDo = (x1 == x2)
else canDo = ((x1 - x2) * (v1 - v2) < 0) && ((x1 - x2) % (v2 - v1) == 0)
println canDo ? 'YES' : 'NO'
