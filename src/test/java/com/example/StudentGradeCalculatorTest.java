package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StudentGradeCalculatorTest {

    private final StudentGradeCalculator calculator = new StudentGradeCalculator();

    @Test
    void testCalculateAverage_EmptyList() {
        // Перевіряється, чи кидається IllegalArgumentException, якщо список оцінок порожній.
        // Очікується повідомлення "No grades provided".
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(Collections.emptyList());
        });
        assertEquals("No grades provided", exception.getMessage());
    }

    @Test
    void testCalculateAverageMultipleGrades() {
        // Перевіряється правильність обчислення середнього значення для списку з кількох оцінок.
        // Вхідні оцінки: 70, 80, 90. Очікуваний результат: 80.0.
        assertEquals(80.0, calculator.calculateAverage(Arrays.asList(70, 80, 90)), 0.01);
    }

    @Test
    void testCalculateAverageSingleGrade() {
        // Перевіряється правильність обчислення середнього значення, коли передано лише одну оцінку.
        // Вхідна оцінка: 85. Очікуваний результат: 85.0.
        assertEquals(85.0, calculator.calculateAverage(Arrays.asList(85)), 0.01);
    }

    @Test
    void testCalculateAverageNoGrades() {
        // Перевіряється поведінка методу, якщо передано порожній список (без оцінок).
        // Очікується виняток IllegalArgumentException з повідомленням "No grades provided".
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(Arrays.asList());
        });
        assertEquals("No grades provided", exception.getMessage());
    }

    @Test
    void testCalculateAverageNegativeGrade() {
        // Перевіряється обробка випадку, коли одна з оцінок у списку є від'ємною.
        // Вхідні оцінки: 90, -5, 85. Очікується виняток IllegalArgumentException з повідомленням "Invalid grade: -5".
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(Arrays.asList(90, -5, 85));
        });
        assertEquals("Invalid grade: -5", exception.getMessage());
    }

    @Test
    void testCalculateAverageGradeAbove100() {
        // Перевіряється обробка випадку, коли одна з оцінок у списку перевищує 100.
        // Вхідні оцінки: 110, 85. Очікується виняток IllegalArgumentException з повідомленням "Invalid grade: 110".
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(Arrays.asList(110, 85));
        });
        assertEquals("Invalid grade: 110", exception.getMessage());
    }

    @Test
    void testCalculateAverageLargeNumberOfGrades() {
        // Перевіряється коректність роботи методу з великим обсягом даних (1000 оцінок).
        // Усі оцінки мають значення 100. Очікуваний результат: середнє значення 100.0.
        List<Integer> largeGrades = Arrays.asList(new Integer[1000]);
        for (int i = 0; i < 1000; i++) {
            largeGrades.set(i, 100);
        }
        assertEquals(100.0, calculator.calculateAverage(largeGrades), 0.01);
    }

    // @Test
    // void testCalculateAverage_NotImplemented() {
    //     // Тест без реалізації. Він навмисно завершується помилкою, щоб вказати на відсутність реалізації.
    //     fail("This test is not implemented yet");
    // }
}
