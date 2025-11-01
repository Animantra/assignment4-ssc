package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.graphs.GraphResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Writer {

    public static void writeGraphResult(String filename, List<GraphResult> result) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(result, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
