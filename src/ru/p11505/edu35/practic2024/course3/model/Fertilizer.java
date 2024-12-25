package ru.p11505.edu35.practic2024.course3.model;
import java.util.List;

public class Fertilizer {
    private String name;                          // Название удобрения
    private List<FertilizerComponent> components;  // Состав удобрения (например, азот, фосфор, калий, сера, кремний)

    public Fertilizer(String name, List<FertilizerComponent> components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public List<FertilizerComponent> getComponents() {
        return components;
    }

    // Метод для вычисления "похожести" удобрения с нужными дозами
    public double calculateDifference(double nitrogen, double phosphorus, double potassium, double sulfur, double silicon) {
        double nitrogenDifference = 0;
        double phosphorusDifference = 0;
        double potassiumDifference = 0;
        double sulfurDifference = 0;
        double siliconDifference = 0;

        for (FertilizerComponent component : components) {
            switch (component.getNutrientName().toLowerCase()) {
                case "nitrogen":
                    nitrogenDifference = Math.abs(nitrogen - component.getAmount());
                    break;
                case "phosphorus":
                    phosphorusDifference = Math.abs(phosphorus - component.getAmount());
                    break;
                case "potassium":
                    potassiumDifference = Math.abs(potassium - component.getAmount());
                    break;
                case "sulfur":
                    sulfurDifference = Math.abs(sulfur - component.getAmount());
                    break;
                case "silicon":
                    siliconDifference = Math.abs(silicon - component.getAmount());
                    break;
            }
        }

        // Суммируем различия по всем элементам питания
        return nitrogenDifference + phosphorusDifference + potassiumDifference + sulfurDifference + siliconDifference;
    }

    // Перевод в фунты на акр для США
    public double convertToPoundsPerAcre(double amountInKg) {
        return amountInKg * 2.20462; // 1 кг = 2.20462 фунта
    }

    // Перевод в кг на му для Китая
    public double convertToKgPerMu(double amountInKg) {
        return amountInKg / 1.5; // 1 му = 1.5 га
    }

    public double getAmount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}