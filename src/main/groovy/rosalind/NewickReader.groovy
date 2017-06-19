package rosalind

import java.util.regex.Matcher
import java.util.regex.Pattern

class NewickReader {
    private static final Pattern TOKEN = ~/[(),;]/
    private final String s
    private final Matcher m
    private final List<NNode> entireGraphRef

    static NNode parse(String graph) {
        def nr = new NewickReader(graph.replaceAll(' ', ''))
        nr.parseSub(0)
    }

    private parseSub(int pos) {
        List<NNode> siblings = []
        List<NNode> children = null
        NNode current = null
        while (m.find()) {
            switch (m.group()) {
                case '(':
                    children = parseSub(m.start() + 1)
                    break
                default:
                    def name = s.substring(pos, m.start())
                    current = new NNode(name)
                    for (c in children) { current << c }
                    siblings << current
                    if (m.group() == ')')
                        return siblings
                    else
                        children = null
            }
            pos = m.start() + 1
        }
        return current
    }


    private NewickReader(String s) {
        this.s = s
        m = s =~ TOKEN
        entireGraphRef = []
    }

    class NNode {
        final String name
        final List<NNode> children = []
        NNode parent
        final List<NNode> entireGraph
        int distance

        NNode(String name) {
            this.name = name
            entireGraph = entireGraphRef
            entireGraph << this
        }

        List<NNode> getNeighbors() {
            def neighbors = new ArrayList<NNode>(children.size() + 1)
            neighbors += children
            parent?.with { neighbors << it }
            neighbors
        }

        NNode leftShift(NNode child) {
            children << child
            child.parent = this
        }
    }
}
