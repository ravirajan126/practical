import java.util.*;

public class AstarSearchAlgo {

    public static void main(String[] args) {
        Node n1 = new Node("Arad", 366);
        Node n2 = new Node("Zerind", 374);
        Node n3 = new Node("Oradea", 380);
        Node n4 = new Node("Sibiu", 253);
        Node n5 = new Node("Fagaras", 178);
        Node n6 = new Node("Rimnicu Vilcea", 193);
        Node n7 = new Node("Pitesti", 98);
        Node n8 = new Node("Timisoara", 329);
        Node n9 = new Node("Lugoj", 244);
        Node n10 = new Node("Mehadia", 241);
        Node n11 = new Node("Drobeta", 242);
        Node n12 = new Node("Craiova", 160);
        Node n13 = new Node("Bucharest", 0);
        Node n14 = new Node("Giurgiu", 77);

        n1.adjacencies = new Edge[]{
                new Edge(n2, 75),
                new Edge(n4, 140),
                new Edge(n8, 118)
        };

        n2.adjacencies = new Edge[]{
                new Edge(n1, 75),
                new Edge(n3, 71)
        };

        n3.adjacencies = new Edge[]{
                new Edge(n2, 71),
                new Edge(n4, 151)
        };

        n4.adjacencies = new Edge[]{
                new Edge(n1, 140),
                new Edge(n5, 99),
                new Edge(n3, 151),
                new Edge(n6, 80),
        };

        n5.adjacencies = new Edge[]{
                new Edge(n4, 99),
                new Edge(n13, 211)
        };

        n6.adjacencies = new Edge[]{
                new Edge(n4, 80),
                new Edge(n7, 97),
                new Edge(n12, 146)
        };

        n7.adjacencies = new Edge[]{
                new Edge(n6, 97),
                new Edge(n13, 101),
                new Edge(n12, 138)
        };

        n8.adjacencies = new Edge[]{
                new Edge(n1, 118),
                new Edge(n9, 111)
        };

        n9.adjacencies = new Edge[]{
                new Edge(n8, 111),
                new Edge(n10, 70)
        };

        n10.adjacencies = new Edge[]{
                new Edge(n9, 70),
                new Edge(n11, 75)
        };

        n11.adjacencies = new Edge[]{
                new Edge(n10, 75),
                new Edge(n12, 120)
        };

        n12.adjacencies = new Edge[]{
                new Edge(n11, 120),
                new Edge(n6, 146),
                new Edge(n7, 138)
        };

        n13.adjacencies = new Edge[]{
                new Edge(n7, 101),
                new Edge(n14, 90),
                new Edge(n5, 211)
        };

        n14.adjacencies = new Edge[]{
                new Edge(n13, 90)
        };

        AstarSearch(n1, n13);
        List<Node> path = printPath(n13);
        System.out.println("Path: " + path);
    }

    public static List<Node> printPath(Node target) {
        List<Node> path = new ArrayList<>();

        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }

    public static void AstarSearch(Node source, Node goal) {

        Set<Node> explored = new HashSet<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(20,
                Comparator.comparingDouble(i -> i.f_scores)
        );

        source.g_scores = 0;
        queue.add(source);
        boolean found = false;
        while ((!queue.isEmpty()) && (!found)) {
            Node current = queue.poll();
            explored.add(current);
            if (current.value.equals(goal.value)) {
                found = true;
            }

            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double cost = e.cost;
                double temp_g_scores = current.g_scores + cost;
                double temp_f_scores = temp_g_scores + child.h_scores;

                if ((explored.contains(child)) &&
                        (temp_f_scores >= child.f_scores)) {
                } else if ((!queue.contains(child)) ||
                        (temp_f_scores < child.f_scores)) {

                    child.parent = current;
                    child.g_scores = temp_g_scores;
                    child.f_scores = temp_f_scores;
                    queue.remove(child);
                    queue.add(child);

                }

            }

        }

    }

}

class Node {

    public final String value;
    public final double h_scores;
    public double g_scores;
    public double f_scores = 0;
    public Edge[] adjacencies;
    public Node parent;

    public Node(String val, double hVal) {
        value = val;
        h_scores = hVal;
    }

    public String toString() {
        return value;
    }

}

class Edge {
    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal) {
        target = targetNode;
        cost = costVal;
    }
}