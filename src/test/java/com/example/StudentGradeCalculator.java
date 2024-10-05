package com.example;

import java.util.List;

public class StudentGradeCalculator {
    public double calculateAverage(List<Integer> grades) {
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("No grades provided");
        }
        
        double sum = 0;
        for (int grade : grades) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Invalid grade: " + grade);
            }
            sum += grade;
        }
        
        return sum / grades.size();
    }
}
