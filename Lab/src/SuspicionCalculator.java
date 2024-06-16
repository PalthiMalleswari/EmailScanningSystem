// package com.example;
import java.util.*;
import java.util.Map;

public class SuspicionCalculator {
    private Map<String, Double> keywordWeights;
    private double maxPossibleScore;

    public SuspicionCalculator(KeywordDAO keywordDAO) {
        this.keywordWeights = keywordDAO.getKeywordWeights();
        if (this.keywordWeights == null || this.keywordWeights.isEmpty()) {
            ExceptionHandling.showError("Keyword weights are empty or null!");
        }
        this.maxPossibleScore = keywordWeights.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateSuspicionPercent(String emailContent) {
        if (emailContent == null || emailContent.isEmpty()) {
            ExceptionHandling.showError("Email can't be null or empty!");
        }
        if (!isValidEmailContent(emailContent)) {
            ExceptionHandling.showError("Email is not valid!");
        }
        try {
            double totalScore = analyzeEmailContent(emailContent);
            return (totalScore / maxPossibleScore) * 100;
        } catch (Exception e) {
            System.out.println("Exception!");
            return -1;
        }
    }

    private double analyzeEmailContent(String emailContent) {
        double totalScore = 0;
        String[] words = emailContent.split("\\W+"); // Split by non-word characters
        // Debug: Print the words array
        for (String word : words) {
            String lowerWord = word.toLowerCase();
            if (keywordWeights.containsKey(lowerWord)) {
                totalScore += keywordWeights.get(lowerWord);
            }
        }
        return totalScore;
    }


    private double matchKeyword(String word) {
        return keywordWeights.getOrDefault(word, 0.0);
    }

    public boolean isValidEmailContent(String content) {
        return !content.trim().isEmpty();
    }
}
