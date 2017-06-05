package rosalind

int n = new Scanner(System.in).nextInt()
int nSignedPerms = CommonMath.factor(n) * 2**n

def perms = (1..n).permutations()
int p = perms.size()
signVar = { int k -> k == 1 ? [[1], [-1]] : signVar(k - 1).collectMany { subSign -> [1, -1].collect { subSign + it } } }
def signVars = signVar(n)
int v = signVars.size()
assert nSignedPerms == p * v
def result = []
for (int i in 0..<p) {
    for (int j in 0..<v) {
        def pij = new int[n]
        for (int k in 0..<n) {
            pij[k] = perms[i][k] * signVars[j][k]
        }
        result << pij.join(' ')
    }
}
println nSignedPerms
result.each { println it }


