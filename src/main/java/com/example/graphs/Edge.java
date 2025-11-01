package com.example.graphs;

public class Edge {
    public int u;
    public int v;
    public int w;
    
    public Edge(int u,int v,int w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
    public int getU(){
        return u;
    }

    public int getV(){
         return v;
    }

    public int getW(){
        return w;
    }
    public void setW(int w){
        this.w = w;
    }

    public String toString(){
        return u + " : " + v + " : " + w;
    }
}
