package ru.p11505.edu35.practic2024.course3.view;

import java.util.Scanner;
import ru.p11505.edu35.practic2024.course3.model.AgroCalculator;

public class ConsoleTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод обязательных параметров
        double area = getDoubleInput(scanner, "Введите площадь поля в гектарах: ");
        double yieldPotential = getDoubleInput(scanner, "Введите урожайность (ц/га): ");
        double nitrogenNorm = getDoubleInput(scanner, "Введите норматив затрат азота (кг/ц): ");
        double phosphorusNorm = getDoubleInput(scanner, "Введите норматив затрат фосфора (кг/ц): ");
        double potassiumNorm = getDoubleInput(scanner, "Введите норматив затрат калия (кг/ц): ");
        double sulphurNorm = getDoubleInput(scanner, "Введите норматив затрат серы (кг/ц): "); // Новый ввод для серы
        double siliconNorm = getDoubleInput(scanner, "Введите норматив затрат кремния (кг/ц): "); // Новый ввод для кремния

        // Ввод необязательных параметров с базовыми значениями
        double humusCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на содержание гумуса (по умолчанию 1.2): ", 1.2);
        double soilGranulometryCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на гранулометрический состав (по умолчанию 1.1): ", 1.1);
        double predecessorCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на предшественника (по умолчанию 1.0): ", 1.0);
        double erosionCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на эродированность (по умолчанию 1.05): ", 1.05);
        double nutritionElementCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на содержание элемента питания (по умолчанию 1.1): ", 1.1);
        double soilPhCoefficient = getOptionalDoubleInput(scanner, "Введите коэффициент на рН почвы (по умолчанию 1.0): ", 1.0);

        // Ввод флага для бобовых культур
        boolean isLeguminous = getBooleanInput(scanner);

        // Создаем объект калькулятора с параметрами
        AgroCalculator calculator = new AgroCalculator(area, 
                                                        yieldPotential, 
                                                        nitrogenNorm, 
                                                        phosphorusNorm, 
                                                        potassiumNorm,
                                                        sulphurNorm, // Передаем значение серы
                                                        siliconNorm, // Передаем значение кремния
                                                        humusCoefficient, 
                                                        soilGranulometryCoefficient, 
                                                        predecessorCoefficient,
                                                        erosionCoefficient, 
                                                        nutritionElementCoefficient, 
                                                        soilPhCoefficient, 
                                                        isLeguminous);

        // Рассчитываем дозы удобрений
        double nitrogenDose = calculator.calculateNitrogenDose();
        double phosphorusDose = calculator.calculatePhosphorusDose();
        double potassiumDose = calculator.calculatePotassiumDose();
        double sulphurDose = calculator.calculateSulfurDose(); // Рассчитываем дозу серы
        double siliconDose = calculator.calculateSiliconDose(); // Рассчитываем дозу кремния

        // Выводим дозы удобрений в килограммах на гектар
        System.out.println("Доза азотных удобрений: " + nitrogenDose + " кг д.в./га");
        System.out.println("Доза фосфорных удобрений: " + phosphorusDose + " кг д.в./га");
        System.out.println("Доза калийных удобрений: " + potassiumDose + " кг д.в./га");
        System.out.println("Доза серы: " + sulphurDose + " кг д.в./га"); // Вывод дозы серы
        System.out.println("Доза кремния: " + siliconDose + " кг д.в./га"); // Вывод дозы кремния

        // Переводим дозы в фунты на акр и килограммы на му
        System.out.println("Доза азотных удобрений (США): " + calculator.convertToPoundsPerAcre(nitrogenDose) + " фунтов/акр");
        System.out.println("Доза фосфорных удобрений (США): " + calculator.convertToPoundsPerAcre(phosphorusDose) + " фунтов/акр");
        System.out.println("Доза калийных удобрений (США): " + calculator.convertToPoundsPerAcre(potassiumDose) + " фунтов/акр");
        System.out.println("Доза серы (США): " + calculator.convertToPoundsPerAcre(sulphurDose) + " фунтов/акр"); // Перевод серы
        System.out.println("Доза кремния (США): " + calculator.convertToPoundsPerAcre(siliconDose) + " фунтов/акр"); // Перевод кремния

        System.out.println("Доза азотных удобрений (Китай): " + calculator.convertToKgPerMu(nitrogenDose) + " кг/му");
        System.out.println("Доза фосфорных удобрений (Китай): " + calculator.convertToKgPerMu(phosphorusDose) + " кг/му");
        System.out.println("Доза калийных удобрений (Китай): " + calculator.convertToKgPerMu(potassiumDose) + " кг/му");
        System.out.println("Доза серы (Китай): " + calculator.convertToKgPerMu(sulphurDose) + " кг/му"); // Перевод серы
        System.out.println("Доза кремния (Китай): " + calculator.convertToKgPerMu(siliconDose) + " кг/му"); // Перевод кремния
    }

    // Метод для получения корректного числового ввода
    private static double getDoubleInput(Scanner scanner, String prompt) {
        double input = -1;
        while (input <= 0) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input <= 0) {
                    System.out.println("Ошибка: значение должно быть положительным.");
                }
            } else {
                System.out.println("Ошибка: введено некорректное число.");
                scanner.next(); // очищаем неверный ввод
            }
        }
        return input;
    }

    // Метод для получения необязательного числового ввода с базовым значением
    private static double getOptionalDoubleInput(Scanner scanner, String prompt, double defaultValue) {
        double input = defaultValue;
        System.out.print(prompt);
        String inputLine = scanner.nextLine().trim(); // убираем лишние пробелы

        // Если введена пустая строка или "-", используем значение по умолчанию
        if (inputLine.isEmpty() || inputLine.equals("-")) {
            return input; // Используем дефолтное значение
        }

        // Пробуем распарсить введенное значение
        try {
            input = Double.parseDouble(inputLine);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное число. Используется значение по умолчанию: " + defaultValue);
        }
        return input;
    }

    // Метод для получения булевого ввода (true/false)
    private static boolean getBooleanInput(Scanner scanner) {
        boolean input = false;
        while (true) {
            System.out.print("Культура бобовая (true/false): ");
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("true") || line.equals("false")) {
                input = Boolean.parseBoolean(line);
                break;
            } else {
                System.out.println("Ошибка: введено некорректное значение. Пожалуйста, введите true или false.");
            }
        }
        return input;
    }
}






