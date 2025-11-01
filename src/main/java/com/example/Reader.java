package com.example;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.example.graphs.Edge;
import com.example.graphs.Graph;
import com.example.graphs.GraphCont;
import com.example.graphs.GraphResult;
import com.google.gson.Gson;


public class Reader {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("data/tasks.json")){
            Gson gson = new Gson();
            GraphCont cont = gson.fromJson(reader,GraphCont.class);

            List<Graph> graphs = cont.getGraphs();
            List<GraphResult> results = new ArrayList<>();
            
            for(Graph g:graphs){

                Metrics metrics = new Metrics();
                List<List<Integer>> sccList = KosarajuAlgo.kosarajuAlgo(g.getN(), g, metrics);
                List<List<Edge>> condGraph = ToCondGraph.toCondGraphWeighted(sccList, g);

                List<Integer> topoOrder = KahnAlgo.kahnAlgo(condGraph,g,sccList, metrics);

                int source = g.getSource();
                dagSP.DAGResult spResult = dagSP.sp(topoOrder, condGraph, source, metrics);
                dagSP.DAGResult lpResult = dagSP.lp(topoOrder, condGraph, source, metrics);

                GraphResult result = new GraphResult();
                result.sccList = sccList;
                result.condGraph = condGraph;
                result.topoOrder = topoOrder;
                result.shortestPath = spResult;
                result.longestPath = lpResult;

                results.add(result);

            }
            Writer.writeGraphResult("data/results.json", results);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }     
}
