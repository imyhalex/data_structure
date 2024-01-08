package edu.nyu.cs.Graphs;

public class Main {

    public static void main(String[] args) {
        Graphs myGraphs = new Graphs();

        myGraphs.addVertex("A");
        myGraphs.addVertex("B");

        myGraphs.addEdge("A", "B");

        myGraphs.addVertex("D");
        myGraphs.addEdge("B", "D");

        myGraphs.printGraph();
    }
    
}
