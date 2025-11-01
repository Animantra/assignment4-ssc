package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.example.graphs.Edge;
import com.example.graphs.Graph;


public class KosarajuAlgo {
    private static List<List<Integer>> graph;

    public static List<List<Integer>> kosarajuAlgo(int n,Graph g,Metrics metrics){
        graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(Edge e : g.getEdges()){
            graph.get(e.getU()).add(e.getV());
        }
        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();

        metrics.startTimer();

        for(int i = 0;i < n;i++){
            if(!visited[i]){
                dfs1(i,graph,visited,st,metrics);
            }
        }

        List<List<Integer>> revGraph = reverseGraph(g, n);
 
        for(int i=0;i<n;i++){
            visited[i]=false;
        }

        List<List<Integer>> sccList = new ArrayList<>();

        while (!st.isEmpty()) {
            int v = st.pop();
            if(!visited[v]){
                List<Integer> sccElm = new ArrayList<>();
                dfs2(v, revGraph, visited, sccElm,metrics);
                sccList.add(sccElm);
            }
        }

        metrics.stopTimer();
        return sccList;
    } 
    public static void dfs1(int v,List<List<Integer>> graph,boolean[] visited, Stack<Integer> st, Metrics metrics){
        visited[v] = true;
        metrics.incDfsVisits();
        for(int e:graph.get(v)){
            metrics.incDfsEdges();
            if(!visited[e]){
                dfs1(e,graph,visited,st,metrics);
            }
        }
        st.push(v);

    }

     public static List<List<Integer>> reverseGraph(Graph g,int n){
        List<List<Integer>> rev = new ArrayList<>();

        for(int i=0;i<n;i++){
            rev.add(new ArrayList<>());
        }

        for(Edge e : g.getEdges()){
            rev.get(e.getV()).add(e.getU());
        }
        return rev;

    }
    public static void dfs2(int v,List<List<Integer>> graph,boolean[] visited, List<Integer> c,Metrics metrics){
            visited[v] = true;
            metrics.incDfsVisits();
            c.add(v);
            for(int e:graph.get(v)){
                if(!visited[e]){
                    metrics.incDfsEdges();
                    dfs2(e,graph,visited,c,metrics);
                }
            }
        }
    }