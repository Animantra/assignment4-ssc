package com.example.graphs;


import java.util.List;
import com.example.dagP;

public class GraphResult {
    public List<List<Integer>> sccList;         
    public List<List<Edge>> condGraph;          
    public List<Integer> topoOrder;             
    public dagSP.DAGResult shortestPath;        
    public dagSP.DAGResult longestPath;    
}

