package algorithms.implementations

static int allBut1(int x) {
    x ? x - 1 : 0
}

Scanner sc = new Scanner(System.in)
sc.class.metaClass.readInts = { int n -> (1..n).collect({ owner.nextInt() }) }

def (n, k) = sc.readInts(2)
def a = sc.readInts(n)

int[] rems = new int[k]
a.each { x -> rems[x % k]++ }
int excluded = allBut1(rems[0])
if (k % 2 == 0) {
    int mid = k / 2
    excluded += allBut1(rems[mid])
}

int lastRem = (k - 1) >> 1
for (int i = 1; i <= lastRem; i++) excluded += Math.min(rems[i], rems[k - i])
println a.size() - excluded