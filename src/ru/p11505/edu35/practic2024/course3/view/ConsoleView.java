package ru.p11505.edu35.practic2024.course3.view;

import java.util.Scanner;
import ru.p11505.edu35.practic2024.course3.model.*;

public class ConsoleView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Агрокалькулятор: расчет доз удобрений");

        // Ввод данных пользователя
        System.out.print("Введите площадь поля (га): ");
        double area = readDouble(scanner);

        System.out.print("Введите достижимую урожайность (ц/га): ");
        double yieldPotential = readDouble(scanner);

        System.out.print("Введите норматив затрат азота (кг/ц): ");
        double nitrogenNorm = readDouble(scanner);

        System.out.print("Введите норматив затрат фосфора (кг/ц): ");
        double phosphorusNorm = readDouble(scanner);

        System.out.print("Введите норматив затрат калия (кг/ц): ");
        double potassiumNorm = readDouble(scanner);

        System.out.print("Введите норматив затрат серы (кг/ц): ");
        double sulfurNorm = readDouble(scanner);

        System.out.print("Введите норматив затрат кремния (кг/ц): ");
        double siliconNorm = readDouble(scanner);

        // Ввод дополнительных коэффициентов с дефолтными значениями
        System.out.print("Введите коэффициент на содержание гумуса (по умолчанию 1.2): ");
        double humusCoefficient = readOptionalDouble(scanner, 1.2);

        System.out.print("Введите коэффициент на гранулометрический состав (по умолчанию 1.1): ");
        double soilGranulometryCoefficient = readOptionalDouble(scanner, 1.1);

        System.out.print("Введите коэффициент на предшественника (по умолчанию 1.0): ");
        double predecessorCoefficient = readOptionalDouble(scanner, 1.0);

        System.out.print("Введите коэффициент на эродированность (по умолчанию 1.05): ");
        double erosionCoefficient = readOptionalDouble(scanner, 1.05);

        System.out.print("Введите коэффициент на содержание элемента питания (по умолчанию 1.1): ");
        double nutritionElementCoefficient = readOptionalDouble(scanner, 1.1);

        System.out.print("Введите коэффициент на рН почвы (по умолчанию 1.0): ");
        double soilPhCoefficient = readOptionalDouble(scanner, 1.0);

        // Ввод флага, является ли культура бобовой
        System.out.print("Является ли культура бобовой? (true/false, по умолчанию false): ");
        boolean isLeguminous = readBoolean(scanner);

        // Создание калькулятора с параметрами пользователя
        AgroCalculator calculator = new AgroCalculator(area, yieldPotential, nitrogenNorm, phosphorusNorm, potassiumNorm, 
                                                       sulfurNorm, siliconNorm, humusCoefficient, soilGranulometryCoefficient, 
                                                       predecessorCoefficient, erosionCoefficient, nutritionElementCoefficient, 
                                                       soilPhCoefficient, isLeguminous);

        // Рассчитываем дозы удобрений
        double nitrogenDose = calculator.calculateNitrogenDose();
        double phosphorusDose = calculator.calculatePhosphorusDose();
        double potassiumDose = calculator.calculatePotassiumDose();
        double sulfurDose = calculator.calculateSulfurDose(); // Для серы
        double siliconDose = calculator.calculateSiliconDose(); // Для кремния

        // Вывод доз удобрений
        System.out.println("Доза азотных удобрений: " + nitrogenDose + " кг/га");
        System.out.println("Доза фосфорных удобрений: " + phosphorusDose + " кг/га");
        System.out.println("Доза калийных удобрений: " + potassiumDose + " кг/га");
        if (sulfurNorm > 0) {
            System.out.println("Доза серных удобрений: " + sulfurDose + " кг/га");
        }
        if (siliconNorm > 0) {
            System.out.println("Доза кремниевых удобрений: " + siliconDose + " кг/га");
        }

        // Перевод дозы в фунты на акр и кг на му
        System.out.println("Доза азотных удобрений (США): " + calculator.convertToPoundsPerAcre(nitrogenDose) + " фунтов/акр");
        System.out.println("Доза фосфорных удобрений (США): " + calculator.convertToPoundsPerAcre(phosphorusDose) + " фунтов/акр");
        System.out.println("Доза калийных удобрений (США): " + calculator.convertToPoundsPerAcre(potassiumDose) + " фунтов/акр");
        System.out.println("Доза серы (США): " + calculator.convertToPoundsPerAcre(sulfurDose) + " фунтов/акр");
        System.out.println("Доза кремния (США): " + calculator.convertToPoundsPerAcre(siliconDose) + " фунтов/акр");

        System.out.println("Доза азотных удобрений (Китай): " + calculator.convertToKgPerMu(nitrogenDose) + " кг/му");
        System.out.println("Доза фосфорных удобрений (Китай): " + calculator.convertToKgPerMu(phosphorusDose) + " кг/му");
        System.out.println("Доза калийных удобрений (Китай): " + calculator.convertToKgPerMu(potassiumDose) + " кг/му");
        System.out.println("Доза серы (Китай): " + calculator.convertToPoundsPerAcre(sulfurDose) + " кг/му");
        System.out.println("Доза кремния (Китай): " + calculator.convertToPoundsPerAcre(siliconDose) + " кг/му");

        // Предлагаем оптимальное удобрение
        FertilizerCatalog catalog = new FertilizerCatalog();
        Fertilizer optimalFertilizer = catalog.getBestFertilizer(nitrogenDose, phosphorusDose, potassiumDose, sulfurDose, siliconDose);

        if (optimalFertilizer != null) {
            System.out.println("Оптимальное удобрение для ваших расчетов: " + optimalFertilizer.getName());
            System.out.println("Нитроген: " + optimalFertilizer.getComponents().get(0).getAmount() + "%");
            System.out.println("Фосфор: " + optimalFertilizer.getComponents().get(1).getAmount() + "%");
            System.out.println("Калий: " + optimalFertilizer.getComponents().get(2).getAmount() + "%");
            System.out.println("Сера: " + optimalFertilizer.getComponents().get(3).getAmount() + "%");
            System.out.println("Кремний: " + optimalFertilizer.getComponents().get(4).getAmount() + "%");

            // Выводим требуемую граммовку
            System.out.println("\nНеобходимая граммовка для выбранного удобрения:");
            printOptimalPackaging(nitrogenDose, phosphorusDose, potassiumDose, sulfurDose, siliconDose);
        } else {
            System.out.println("Не найдено оптимальное удобрение.");
        }

        scanner.close();
    }

    // Метод для чтения числовых данных с проверкой
    private static double readDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Math.max(0, Double.parseDouble(input)); // Преобразуем и не допускаем отрицательных значений
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    // Метод для чтения опционального значения с возможностью установки значения по умолчанию
    private static double readOptionalDouble(Scanner scanner, double defaultValue) {
        String input = scanner.nextLine().trim();
        if (input.isEmpty() || input.equals("-")) {
            return defaultValue; // Возвращаем значение по умолчанию
        } else {
            try {
                return Math.max(0, Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено некорректное число. Используется значение по умолчанию: " + defaultValue);
                return defaultValue;
            }
        }
    }

    // Метод для чтения значения boolean с дефолтным значением
    private static boolean readBoolean(Scanner scanner) {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return false; // Значение по умолчанию: false
        }
        return Boolean.parseBoolean(input);
    }

    // Метод для вывода наилучшего варианта упаковок для доз удобрений
    private static void printOptimalPackaging(double nitrogenDose, double phosphorusDose, double potassiumDose, 
                                               double sulfurDose, double siliconDose) {
        double[] doses = {nitrogenDose, phosphorusDose, potassiumDose, sulfurDose, siliconDose};
        String[] nutrients = {"Азот", "Фосфор", "Калий", "Сера", "Кремний"};
        
        for (int i = 0; i < doses.length; i++) {
            if (doses[i] > 0) {
                System.out.println(nutrients[i] + ": " + doses[i] + " кг");
                printBestPackageOption(doses[i]);
            }
        }
    }

    // Метод для вывода наилучшей упаковки для дозы
    private static void printBestPackageOption(double dose) {
        double[] packageSizes = {1000, 100, 10, 1}; // 1 тонна (1000 кг), 100 кг, 10 кг, 1 кг
        StringBuilder bestOption = new StringBuilder();

        for (double size : packageSizes) {
            int numberOfPackages = (int) (dose / size); // Количество упаковок для текущего размера
            if (numberOfPackages > 0) {
                bestOption.append(numberOfPackages)
                          .append(" упаковка(и) по ")
                          .append((int) size)
                          .append(" кг ");
                dose -= numberOfPackages * size; // Вычитаем использованный вес
            }
            if (dose <= 0) break; // Если все дозы уже распределены, выходим
        }

        System.out.println("Наилучший вариант упаковок: " + bestOption.toString().trim());
    }
}






