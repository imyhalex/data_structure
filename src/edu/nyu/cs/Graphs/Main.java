package edu.nyu.cs.Graphs;

public class Main {

    public static void main(String[] args) {
        Graphs myGraphs = new Graphs();

        myGraphs.addVertex("A");
        myGraphs.printGraph();
        myGraphs.addVertex("B");
        myGraphs.printGraph();

        myGraphs.addEdge("A", "B");
        myGraphs.printGraph();

        myGraphs.addVertex("D");
        myGraphs.printGraph();
        myGraphs.addEdge("B", "D");
        myGraphs.printGraph();
        
        myGraphs.removeVertex("D");
        myGraphs.printGraph();
    }
    
}
