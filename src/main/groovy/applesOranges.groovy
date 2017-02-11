Scanner sc = new Scanner(System.in)
def (s, t, a, b, m, n) = (0..5).collect({ sc.nextInt() })
def apples = (1..m).collect({ sc.nextInt() + a })
def oranges = (1..n).collect({ sc.nextInt() + b })
def onHouse = { it >= s && it <= t }
println apples.findAll(onHouse).size()
println oranges.findAll(onHouse).size()
