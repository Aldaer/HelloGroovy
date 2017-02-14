package algorithms.graph.theory

class Node {
    def ties = new ArrayList<Node>()
    def marked = false
}

Scanner sc = new Scanner(System.in)
def (n, nPairs) = sc.nextLine().tokenize(' ').collect { it as int }

def graph = new Node[n]
for (p in 0..nPairs - 1) {
    def (p0, p1) = [sc.nextInt(), sc.nextInt()]
    def gp0 = graph[p0] ?: (graph[p0] = new Node())
    def gp1 = graph[p1] ?: (graph[p1] = new Node())
    gp0.ties << gp1
    gp1.ties << gp0
}

long singulars = 0
def clusterSizes = []
long combinations = 0

def q = new ArrayDeque<Node>()
for (int i = 0; i < n; i++) {
    def node = graph[i]
    if (node) {
        if (!node.marked) {
            int clusterSize = 0
            while (node) {
                if (!node.marked) {
                    node.marked = true
                    clusterSize++
                    q.addAll(node.ties)
                }
                node = q.poll()
            }
            if (clusterSizes) combinations += clusterSizes.sum() * clusterSize
            clusterSizes << clusterSize
        }
    } else
        singulars++
}

combinations += clusterSizes.sum() * singulars + singulars * (singulars - 1) / 2
println combinations