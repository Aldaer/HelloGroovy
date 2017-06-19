package rosalind

import rosalind.NewickReader.NNode

def sc = new Scanner(System.in)
def result = []
while (true) {
    def graphString = sc.nextLine()
    if (!graphString) break
    while (!graphString.endsWith(';')) graphString += sc.nextLine()
    def (a, b) = sc.nextLine().tokenize(' ')
    sc.nextLine()
    def graph = NewickReader.parse(graphString).entireGraph
    def startingNode = graph.find { it.name == a }
    def endingNode = graph.find { it.name == b }
    def queue = new ArrayDeque<NNode>()
    graph.each { it.distance = Integer.MAX_VALUE }
    startingNode.distance = 0
    queue.push(startingNode)
    while (!queue.empty) {
        def node = queue.poll()
        if (node == endingNode) break
        int neighborDistance = node.distance + 1
        node.neighbors.findAll { it.distance > neighborDistance }.each {
            it.distance = neighborDistance
            queue << it
        }
    }
    result << endingNode.distance
}
println result.join(' ')