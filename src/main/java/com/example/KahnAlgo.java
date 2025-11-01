package com.example;

import com.example.graphs.Edge;
import com.example.graphs.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnAlgo {
   
    public static List<Integer> kahnAlgo(List<List<Edge>> condGraphWeighted,Graph g,List<List<Integer>> sccList,Metrics metrics){

        metrics.startTimer();

        int n = condGraphWeighted.size();
        int[] indeg = new int[n];

        for(int i=0;i<n;i++){
            for(Edge e : condGraphWeighted.get(i)){
                indeg[e.getV()]++;
                metrics.incDfsEdges();
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indeg[i]==0){
                q.add(i);
                metrics.incPushes();
            }
        }

        List<Integer> l = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            metrics.incPops();
            l.add(node);

            for(Edge e:condGraphWeighted.get(node)){
                int v = e.getV();
                indeg[v]--;
                if(indeg[v]==0){
                    q.add(v);
                    metrics.incPushes();
                }
            }
        }

        if(l.size()<n){
            System.out.println("Error, has a cycle");
        }
        return l;
    }

}