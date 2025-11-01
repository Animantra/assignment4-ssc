package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.graphs.Edge;

public class dagP {
    public static class DAGResult {
        public int[] dist;
        public int[] prev;

        public DAGResult(int[] dist, int[] prev) {
            this.dist = dist;
            this.prev = prev;
        }

        public List<Integer> reconstructPath(int target) {
            List<Integer> path = new ArrayList<>();
            for (int at = target; at != -1; at = prev[at]) {
                path.add(0, at); 
            }
            return path;
        }
        public List<List<Integer>> reconstructAllPaths() {
            List<List<Integer>> paths = new ArrayList<>();
            for (int i = 0; i < dist.length; i++) {
                paths.add(reconstructPath(i));
            }
            return paths;
        }
    }
    public static DAGResult sp(List<Integer> l, List<List<Edge>> condGraphWeighted, int sourceSCC,Metrics metrics) {
        int n = condGraphWeighted.size();  
        int[] dist = new int[n];
        int[] prev = new int[n]; 

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        dist[sourceSCC] = 0;

        for (int u : l) {
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge e:condGraphWeighted.get(u)) {
                    int v = e.getV();
                    int w = e.getW();
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        prev[v] = u;
                        metrics.incDagRel();
                    }
                }
            }
        }
        return new DAGResult(dist, prev);
    }

    public static DAGResult lp(List<Integer> l, List<List<Edge>> condGraphWeighted, int sourceSCC,Metrics metrics) {
        int n = condGraphWeighted.size();  
        int[] dist = new int[n];
        int[] prev = new int[n]; 

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MIN_VALUE;
            prev[i] = -1;
        }

        dist[sourceSCC] = 0;

        for (int u : l) {
            if (dist[u] != Integer.MIN_VALUE) {
                for (Edge e:condGraphWeighted.get(u)) {
                    int v = e.getV();
                    int w = e.getW();
                    if (dist[v] < dist[u] + w) {
                        dist[v] = dist[u] + w;
                        prev[v] = u;
                        metrics.incDagRel();
                    }
                }
            }
        }
        return new DAGResult(dist, prev);
    }

    public static int sourceSCC(List<List<Integer>> sccList, int source){
        for(int i=0;i<sccList.size();i++){
            if(sccList.get(i).contains(source)){
            return i;
            }
        }        
        return -1;

    }

}
