package ru.p11505.edu35.practic2024.course3.model;
import java.util.ArrayList;
import java.util.List;

public class FertilizerCatalog {

    private List<Fertilizer> fertilizers;

    public FertilizerCatalog() {
        this.fertilizers = new ArrayList<>();
        // Пример добавления удобрений в каталог
        fertilizers.add(new Fertilizer("Удобрение A", List.of(
                new FertilizerComponent("Nitrogen", 15),
                new FertilizerComponent("Phosphorus", 5),
                new FertilizerComponent("Potassium", 10),
                new FertilizerComponent("Sulfur", 2),
                new FertilizerComponent("Silicon", 1)
        )));
        fertilizers.add(new Fertilizer("Удобрение B", List.of(
                new FertilizerComponent("Nitrogen", 20),
                new FertilizerComponent("Phosphorus", 10),
                new FertilizerComponent("Potassium", 5),
                new FertilizerComponent("Sulfur", 3),
                new FertilizerComponent("Silicon", 0.5)
        )));
        fertilizers.add(new Fertilizer("Удобрение C", List.of(
                new FertilizerComponent("Nitrogen", 10),
                new FertilizerComponent("Phosphorus", 5),
                new FertilizerComponent("Potassium", 15),
                new FertilizerComponent("Sulfur", 1),
                new FertilizerComponent("Silicon", 2)
        )));
    }

    public Fertilizer getBestFertilizer(double nitrogen, double phosphorus, double potassium, double sulfur, double silicon) {
        Fertilizer bestFertilizer = null;
        double bestMatch = Double.MAX_VALUE;

        for (Fertilizer fertilizer : fertilizers) {
            double difference = fertilizer.calculateDifference(nitrogen, phosphorus, potassium, sulfur, silicon);
            if (difference < bestMatch) {
                bestMatch = difference;
                bestFertilizer = fertilizer;
            }
        }

        return bestFertilizer;
    }
}






