package ru.p11505.edu35.practic2024.course3.model;

import java.util.HashMap;
import java.util.Map;

public class AgroCalculator {

    public static final double DEFAULT_HUMUS_COEFFICIENT = 1.2;
    public static final double DEFAULT_SOIL_GRANULOMETRY_COEFFICIENT = 1.1;
    public static final double DEFAULT_PREDECESSOR_COEFFICIENT = 1.0;
    public static final double DEFAULT_EROSION_COEFFICIENT = 1.05;
    public static final double DEFAULT_NUTRITION_ELEMENT_COEFFICIENT = 1.1;
    public static final double DEFAULT_SOIL_PH_COEFFICIENT = 1.0;
    
    // Добавлены новые коэффициенты для серы и кремния
    public static final double DEFAULT_SULFUR_COEFFICIENT = 1.0;
    public static final double DEFAULT_SILICON_COEFFICIENT = 1.0;

    private double area; 
    private double yieldPotential; 
    private double nitrogenNorm; 
    private double phosphorusNorm; 
    private double potassiumNorm;
    private double sulfurNorm; // Норматив для серы
    private double siliconNorm; // Норматив для кремния
    private double humusCoefficient;
    private double soilGranulometryCoefficient;
    private double predecessorCoefficient;
    private double erosionCoefficient;
    private double nutritionElementCoefficient;
    private double soilPhCoefficient;
    private boolean isLeguminous;

    public class FertilizerKey {
        private String name;
    
        public FertilizerKey(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            FertilizerKey that = (FertilizerKey) obj;
            return name.equals(that.name);
        }
    
        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
    

    // Используем кастомный класс для ключей
    private Map<FertilizerKey, Double> fertilizerComponentList = new HashMap<>();
    private Map<FertilizerKey, Double> siliconCoefficients;
    private Map<FertilizerKey, Double> sulfurCoefficients;

    public AgroCalculator(double area, 
                        double yieldPotential, 
                        double nitrogenNorm, 
                        double phosphorusNorm, 
                        double potassiumNorm,
                        double sulfurNorm, // Добавлен параметр для серы
                        double siliconNorm, // Добавлен параметр для кремния
                        Double humusCoefficient, 
                        Double soilGranulometryCoefficient, 
                        Double predecessorCoefficient,
                        Double erosionCoefficient, 
                        Double nutritionElementCoefficient, 
                        Double soilPhCoefficient, 
                        boolean isLeguminous) {
                            

        fertilizerComponentList.put(new FertilizerKey("area"), area);
        fertilizerComponentList.put(new FertilizerKey("yield"), yieldPotential);
        fertilizerComponentList.put(new FertilizerKey("nitrogen"), nitrogenNorm);
        fertilizerComponentList.put(new FertilizerKey("phosphorus"), phosphorusNorm);
        fertilizerComponentList.put(new FertilizerKey("potassium"), potassiumNorm);
        fertilizerComponentList.put(new FertilizerKey("sulfur"), sulfurNorm); // Инициализация серы
        fertilizerComponentList.put(new FertilizerKey("silicon"), siliconNorm); // Инициализация кремния

        this.humusCoefficient = (humusCoefficient != null && humusCoefficient >= 0) ? humusCoefficient : DEFAULT_HUMUS_COEFFICIENT;
        this.soilGranulometryCoefficient = (soilGranulometryCoefficient != null && soilGranulometryCoefficient >= 0) ? soilGranulometryCoefficient : DEFAULT_SOIL_GRANULOMETRY_COEFFICIENT;
        this.predecessorCoefficient = (predecessorCoefficient != null && predecessorCoefficient >= 0) ? predecessorCoefficient : DEFAULT_PREDECESSOR_COEFFICIENT;
        this.erosionCoefficient = (erosionCoefficient != null && erosionCoefficient >= 0) ? erosionCoefficient : DEFAULT_EROSION_COEFFICIENT;
        this.nutritionElementCoefficient = (nutritionElementCoefficient != null && nutritionElementCoefficient >= 0) ? nutritionElementCoefficient : DEFAULT_NUTRITION_ELEMENT_COEFFICIENT;
        this.soilPhCoefficient = (soilPhCoefficient != null && soilPhCoefficient >= 0) ? soilPhCoefficient : DEFAULT_SOIL_PH_COEFFICIENT;

        this.isLeguminous = isLeguminous;
        this.sulfurCoefficients = sulfurCoefficients;
        this.siliconCoefficients = siliconCoefficients;
    }

    // Рассчет дозы удобрений для серы
    public double calculateSulfurDose() {
        double dose = fertilizerComponentList.get(new FertilizerKey("yield")) 
                * fertilizerComponentList.get(new FertilizerKey("sulfur")) 
                * nutritionElementCoefficient 
                * soilPhCoefficient 
                * soilGranulometryCoefficient 
                * predecessorCoefficient 
                * erosionCoefficient;

        return Math.max(0, dose);
    }

    // Рассчет дозы удобрений для кремния
    public double calculateSiliconDose() {
        double dose = fertilizerComponentList.get(new FertilizerKey("yield")) 
                * fertilizerComponentList.get(new FertilizerKey("silicon")) 
                * nutritionElementCoefficient 
                * soilGranulometryCoefficient 
                * predecessorCoefficient 
                * erosionCoefficient;
                
        return Math.max(0, dose);
    }

    public double calculateNitrogenDose() {
        double dose = fertilizerComponentList.get(new FertilizerKey("yield"))
                * fertilizerComponentList.get(new FertilizerKey("nitrogen"))
                * humusCoefficient
                * soilGranulometryCoefficient
                * predecessorCoefficient
                * erosionCoefficient;
        return Math.max(0, isLeguminous ? dose * 0.4 : dose); 
    }

    public double calculatePhosphorusDose() {
        double dose = fertilizerComponentList.get(new FertilizerKey("yield")) 
                * fertilizerComponentList.get(new FertilizerKey("phosphorus")) 
                * nutritionElementCoefficient 
                * soilPhCoefficient 
                * soilGranulometryCoefficient 
                * predecessorCoefficient 
                * erosionCoefficient;
        return Math.max(0, dose);
    }

    public double calculatePotassiumDose() {
        double dose = fertilizerComponentList.get(new FertilizerKey("yield")) 
                * fertilizerComponentList.get(new FertilizerKey("potassium")) 
                * nutritionElementCoefficient 
                * soilGranulometryCoefficient 
                * predecessorCoefficient 
                * erosionCoefficient;
        return Math.max(0, dose);
    }

    @Deprecated
    public double convertToPoundsPerAcre(double doseKgPerHa) {
        return doseKgPerHa * 0.89218; 
    }
    
    @Deprecated
    public double convertToKgPerMu(double doseKgPerHa) {
        return doseKgPerHa * 0.0667; 
    }

    public double converTo(double unitsPerKg, double valueInKg) {
        return valueInKg * unitsPerKg;
    }
}
