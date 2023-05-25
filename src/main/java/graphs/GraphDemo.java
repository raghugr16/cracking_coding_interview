package graphs;

import java.util.UUID;

public class GraphDemo {

    public static void main(String[] args) {
        Graph<String> graph =new Graph<>();
        Graph.Node nodeA = graph.createNode("A", "A", UUID.randomUUID().toString());
        Graph.Node nodeB = graph.createNode("B", "B", UUID.randomUUID().toString());
        Graph.Node nodeC = graph.createNode("C", "C", UUID.randomUUID().toString());
        Graph.Node nodeD = graph.createNode("D", "D", UUID.randomUUID().toString());
        Graph.Node nodeE = graph.createNode("E", "E", UUID.randomUUID().toString());
        Graph.Node nodeF = graph.createNode("F", "F", UUID.randomUUID().toString());



    }
}
