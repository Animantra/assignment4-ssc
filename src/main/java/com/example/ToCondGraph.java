package com.example;

import java.util.ArrayList;
import java.util.List;
import com.example.graphs.Edge;
import com.example.graphs.Graph;

public class ToCondGraph {

    public static List<List<Edge>> toCondGraphWeighted(List<List<Integer>> sccList, Graph g) {
        int[] sccInd = new int[g.getN()];

        for (int i = 0; i < sccList.size(); i++) {
            for (int v : sccList.get(i)) {
                sccInd[v] = i;
            }
        }

        int n = sccList.size();
        List<List<Edge>> condGraphWeighted = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            condGraphWeighted.add(new ArrayList<>());
        }

        for (Edge e : g.getEdges()) {
            int u = sccInd[e.getU()];
            int v = sccInd[e.getV()];

            if (u != v) { 
                boolean exists = false;
                for (Edge eg : condGraphWeighted.get(u)) {
                    if (eg.getV() == v) { 
                        if (e.getW() < eg.getW()) {
                            eg.setW(e.getW());
                        }
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    condGraphWeighted.get(u).add(new Edge(u, v, e.getW()));
                }
            }
        }

        return condGraphWeighted;
    }
}
