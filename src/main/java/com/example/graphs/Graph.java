package com.example.graphs;

import java.util.List;

public class Graph {
    public boolean directed;
    public int n;
    public List<Edge> edges;
    public int source;
    public String weight_model;

    public boolean isDirected(){
        return directed;
    }

    public int getN(){
        return n;
    }

    public List<Edge> getEdges(){
        return edges;
    }

    public int getSource(){
        return source;
    }


    public String getWeight_model() {
        return weight_model;
    }

    public String toString() {
        return "Graph{n=" + n + ", directed=" + directed + ", edges=" + (edges != null ? edges.size() : 0) + "}";
    }
     
}
