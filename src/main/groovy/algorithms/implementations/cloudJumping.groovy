package algorithms.implementations

Scanner sc = new Scanner(System.in)
def (n, k) = [sc.nextInt(), sc.nextInt()]
def clouds = (0..n - 1).collect({ sc.nextInt() })
int E = 100
int pos = 0
while (true) {
    pos = (pos + k) % n
    E -= 1 + clouds[pos] * 2
    if (pos == 0) break
}
println E

