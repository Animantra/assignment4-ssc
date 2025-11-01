package com.example;

public class Metrics {
    private double startTime;
    private double endTime;
    private int dfsVisits = 0;
    private int dfsEdges = 0;
    private int kahnPushes = 0;
    private int kahnPops = 0;
    private int dagRelaxations = 0;

    public void startTimer(){
        startTime = System.nanoTime();
    }

    public void stopTimer(){
        endTime = System.nanoTime();
    }

    public double time(){
        return (endTime - startTime) / 100000.0;
    }

    public void incDfsVisits() {
         dfsVisits++;
    }

    public void incDfsEdges() {
        dfsEdges++; 
    }

    public void incPushes() {
        kahnPushes++; 
    }

    public void incPops() {
        kahnPops++;
    }
    public void incDagRel() {
        dagRelaxations++; 
    }

    public int getDfsVisits() {
        return dfsVisits;
    }
    public int getDfsEdges() {
        return dfsEdges;
    }
    public int getKahnPushes() {
        return kahnPushes;
    }
    public int getKahnPops() {
        return kahnPops;
    }
    public int getDagRelaxations() {
        return dagRelaxations;
    }
}
