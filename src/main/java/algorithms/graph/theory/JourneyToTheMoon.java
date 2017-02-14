package algorithms.graph.theory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class JourneyToTheMoon {
    private static class Node {
        List<Node> ties = new ArrayList<>();
        boolean unmarked = true;

        int walk() {
            if (unmarked) {
                unmarked = false;
                int sum = 1;
                for (Node nd : ties) sum += nd.walk();
                return sum;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nPairs = sc.nextInt();

        Node[] graph = new Node[n];

        for (int p = 0; p < nPairs; p++) {
            int p0 = sc.nextInt();
            int p1 = sc.nextInt();
            if (graph[p0] == null) graph[p0] = new Node();
            if (graph[p1] == null) graph[p1] = new Node();
            graph[p0].ties.add(graph[p1]);
            graph[p1].ties.add(graph[p0]);
        }

        List<Integer> clusterSizes = new ArrayList<>();

        long combinations = 0;
        long singulars = 0;

        for (int i = 0; i < n; i++) {
            Node nd = graph[i];
            if (nd == null) {
                singulars++;
                continue;
            }
            int clusterSize = nd.walk();
            if (clusterSize > 0) {
                for (Integer m : clusterSizes) combinations += clusterSize * m;
                clusterSizes.add(clusterSize);
            }
        }
        combinations += clusterSizes.parallelStream().mapToInt(Integer::intValue).sum() * singulars;
        combinations += (singulars * (singulars - 1)) >> 1;
        System.out.println(combinations);
    }
}
