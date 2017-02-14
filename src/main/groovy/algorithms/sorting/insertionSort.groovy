package algorithms.sorting

static def aprint(List a) {
    a.inject('', { String s, x -> s + ' ' + x })
}

def sc = new Scanner(System.in)
def n = sc.nextInt()
def a = (0..n - 1).collect { sc.nextInt() }
def v = a[-1]
int i = a.size() - 2
while (a[i] > v && i >= 0) {
    a[i + 1] = a[i]
    i--
    println aprint(a)
}
a[i+1] = v
println aprint(a)
