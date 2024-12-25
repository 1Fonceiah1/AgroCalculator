package ru.p11505.edu35.practic2024.course3.model;

import java.util.ArrayList;
import java.util.List;

public class Packagizer {

    // Метод для получения упаковок для дозы удобрения
    public List<PackageInfo> getPackages(double dose, List<Double> packageValues) {
        List<PackageInfo> packageList = new ArrayList<>();
        double remainingDose = dose;
        
        for (double packageSize : packageValues) {
            if (remainingDose >= packageSize) {
                int count = (int) (remainingDose / packageSize); // Количество упаковок
                remainingDose -= count * packageSize;
                packageList.add(new PackageInfo(packageSize, count));
            }
        }
        
        return packageList;
    }
}




