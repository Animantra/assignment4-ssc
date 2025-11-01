package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import com.example.graphs.Edge;
import com.example.graphs.Graph;
import org.junit.jupiter.api.Test;

public class KosarajuAlgoTest {

    @Test
    public void testSmallGraph() {
        Graph g = new Graph();
        g.n = 4;
        g.directed = true;
        g.edges = new ArrayList<>(); 
        g.edges.add(new Edge(0, 1, 1));
        g.edges.add(new Edge(1, 2, 1));
        g.edges.add(new Edge(2, 0, 1));
        g.edges.add(new Edge(1, 3, 1));

        Metrics metrics = new Metrics();

        List<List<Integer>> sccList = KosarajuAlgo.kosarajuAlgo(g.getN(), g, metrics);

        assertEquals(2, sccList.size());

        boolean found012 = false;
        boolean found3 = false;
        for (List<Integer> scc : sccList) {
            if (scc.contains(0) && scc.contains(1) && scc.contains(2)) {
                found012 = true;
            } else if (scc.contains(3)) {
                found3 = true;
            }
        }

        assertTrue(found012, "SCC {0,1,2} should exist");
        assertTrue(found3, "SCC {3} should exist");
    }

    @Test
    public void testDisconnectedGraph() {
        Graph g = new Graph();
        g.n = 3;
        g.directed = true;
        g.edges = new ArrayList<>(); 

        Metrics metrics = new Metrics();
        List<List<Integer>> sccList = KosarajuAlgo.kosarajuAlgo(g.getN(), g, metrics);

        assertEquals(3, sccList.size());
        for (List<Integer> scc : sccList) {
            assertEquals(1, scc.size());
        }

    }
}
