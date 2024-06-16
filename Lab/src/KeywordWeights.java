// package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class KeywordWeights {

    private Map<String,Double> weights;

    public KeywordWeights(){

        weights = new HashMap<>();
        // Load predefined weights
        loadPredefinedWeights();

    }

    // Method to get the weights

    public Map<String, Double> getWeights() {
        return weights;
    }

    // Method to load predefined weights
    private void loadPredefinedWeights() {
        weights.put("attack", 80.0);
        weights.put("hack", 90.0);
        weights.put("virus", 70.0);
        weights.put("malware", 85.0);
        weights.put("phishing", 95.0);
    }

    // Method to load weights from a file
    public void loadWeightsFromFile(String filePath) throws IOException {

        System.out.println("Current working directory: " + Paths.get(".").toAbsolutePath().normalize().toString());
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String keyword = parts[0].trim().toLowerCase();
                    Double weight = Double.parseDouble(parts[1].trim());
                    weights.put(keyword, weight);
                }
            }
        }
    }

    // Method to adding a weight for a specific keyword
    public void addKeywordWeight(String keyword, double weight){

        if(keyword == null&& keyword.isEmpty()){
            ExceptionHandling.showError("KeyWord should not be null!");

        }
        weights.put(keyword.toLowerCase(), weight);
    }

    // Method to retrieve a weight for a specific keyword
    public double getWeight(String keyword) {
        return weights.getOrDefault(keyword.toLowerCase(), 0.0);
    }

    // Method to remove keywordweights
    public void removeKeywordWeight(String keyword){
        if (keyword == null || keyword.isEmpty()) {
            ExceptionHandling.showError("KeyWordWeights are not loaded");
        }
        if (!weights.containsKey(keyword.toLowerCase())) {
            throw new IllegalArgumentException("Keyword not found: " + keyword);
        }
        weights.remove(keyword.toLowerCase());
    }

    // to update exisiting keyweights
    public void updateKeywordWeight(String keyword, double weight){
        if (keyword == null || keyword.isEmpty()) {
            ExceptionHandling.showError("KeyWordWeights are not loaded");
        }
        weights.put(keyword.toLowerCase(), weight);
    }


}
