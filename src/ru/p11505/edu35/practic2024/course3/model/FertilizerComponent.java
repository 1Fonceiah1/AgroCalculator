package ru.p11505.edu35.practic2024.course3.model;

public class FertilizerComponent {
    private String nutrientName; // Название элемента питания (Азот, Фосфор, Калий, Сера, Кремний)
    private double amount;       // Количество этого элемента в удобрении (в процентах или в кг)

    public FertilizerComponent(String nutrientName, double amount) {
        this.nutrientName = nutrientName;
        this.amount = amount;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public double getAmount() {
        return amount;
    }

    // Методы для получения процента для разных элементов
    public double getNitrogenPercentage() {
        if ("Nitrogen".equalsIgnoreCase(nutrientName)) {
            return amount;
        }
        return 0;
    }

    public double getPhosphorusPercentage() {
        if ("Phosphorus".equalsIgnoreCase(nutrientName)) {
            return amount;
        }
        return 0;
    }

    public double getPotassiumPercentage() {
        if ("Potassium".equalsIgnoreCase(nutrientName)) {
            return amount;
        }
        return 0;
    }

    public double getSulfurPercentage() {
        if ("Sulfur".equalsIgnoreCase(nutrientName)) {
            return amount;
        }
        return 0;
    }

    public double getSiliconPercentage() {
        if ("Silicon".equalsIgnoreCase(nutrientName)) {
            return amount;
        }
        return 0;
    }
}


