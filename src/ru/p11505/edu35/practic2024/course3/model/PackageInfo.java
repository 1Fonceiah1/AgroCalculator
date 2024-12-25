package ru.p11505.edu35.practic2024.course3.model;

public class PackageInfo {
    private double packageSize; // Размер упаковки
    private int packageCount;   // Количество упаковок

    public PackageInfo(double packageSize, int packageCount) {
        this.packageSize = packageSize;
        this.packageCount = packageCount;
    }

    public double getPackageSize() {
        return packageSize;
    }

    public int getPackageCount() {
        return packageCount;
    }

    @Override
    public String toString() {
        return "Размер упаковки: " + packageSize + " кг, Количество упаковок: " + packageCount;
    }
}


