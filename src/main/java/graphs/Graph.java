package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public class Graph<T> {

    private Map<Node<T>,Node<T>> allVertex;
    private Map<Node<T>, ArrayList<Edge<T>>> edgeTable;

    public class Node<T>{
        private String name;
        private T data;
        private String uuid;

        public Node(String name, T data, String uuid){
            this.data = data;
            this.name = name;
            this.uuid = uuid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            if (name != null ? !name.equals(node.name) : node.name != null) return false;
            if (data != null ? !data.equals(node.data) : node.data != null) return false;
            return uuid != null ? uuid.equals(node.uuid) : node.uuid == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (data != null ? data.hashCode() : 0);
            result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
            return result;
        }
    }

    public Node<T> createNode(String name, T data, String uuid){
        Node<T> node = new Node<>(name,data,uuid);
        return node;
    }

    public class Edge<T>{
        private Node<T> source;
        private Node<T> dest;
        private Double weight;

        public Edge(Node<T> source, Node<T> dest, Double weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private class EdgeMinHeap<T>{
        private Map<Node<T>, Double> hashTable;
        private ArrayList<Edge<T>> list;


    }

    private class EdgeComparator implements Comparator<Edge<T>>{

        @Override
        public int compare(Edge<T> e1, Edge<T> e2) {
            if(e1.weight < e2.weight){
                return -1;
            }else if(e1.weight > e2.weight){
                return 1;
            }
            return 0;
        }
    }
    public void displayGraph(){

    }

    public Map<Node<T>, Node<T>> getAllVertex() {
        return allVertex;
    }

    public void setAllVertex(Map<Node<T>, Node<T>> allVertex) {
        this.allVertex = allVertex;
    }

    public Map<Node<T>, ArrayList<Edge<T>>> getEdgeTable() {
        return edgeTable;
    }

    public void setEdgeTable(Map<Node<T>, ArrayList<Edge<T>>> edgeTable) {
        this.edgeTable = edgeTable;
    }
}
