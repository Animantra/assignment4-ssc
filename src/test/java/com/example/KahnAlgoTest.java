package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.graphs.Edge;
import com.example.graphs.Graph;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class KahnAlgoTest {

    private List<List<Edge>> buildCondGraph(int n, int[][] edges) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(new Edge(e[0], e[1], 1));
        }
        return graph;
    }

    @Test
    public void testSmallDAG() {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        List<List<Edge>> condGraph = buildCondGraph(n, edges);

        Graph g = new Graph();
        g.n = n;
        g.edges = new ArrayList<>();
        Metrics metrics = new Metrics();

        List<Integer> topo = KahnAlgo.kahnAlgo(condGraph, g, null, metrics);

        assertEquals(n, topo.size());
        assertTrue(topo.indexOf(0) < topo.indexOf(1));
        assertTrue(topo.indexOf(0) < topo.indexOf(2));
        assertTrue(topo.indexOf(1) < topo.indexOf(2));
    }

    @Test
    public void testSingleNode() {
        int n = 1;
        int[][] edges = {};
        List<List<Edge>> condGraph = buildCondGraph(n, edges);

        Graph g = new Graph();
        g.n = n;
        g.edges = new ArrayList<>();
        Metrics metrics = new Metrics();

        List<Integer> topo = KahnAlgo.kahnAlgo(condGraph, g, null, metrics);

        assertEquals(1, topo.size());
        assertEquals(0, (int) topo.get(0));
    }

    @Test
    public void testCycleDetection() {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        List<List<Edge>> condGraph = buildCondGraph(n, edges);

        Graph g = new Graph();
        g.n = n;
        g.edges = new ArrayList<>();
        Metrics metrics = new Metrics();

        List<Integer> topo = KahnAlgo.kahnAlgo(condGraph, g, null, metrics);

        assertTrue(topo.size() < n);
    }
}
