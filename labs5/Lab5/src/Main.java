public class Main {
    public static void main(String[] args) {
        System.out.println("=== Задание 1 ===");
        Task1 task1 = new Task1();
        task1.setSymbol('A');
        System.out.println("Код символа: " + task1.getCode());
        task1.printSymbolAndCode();

        System.out.println("\n=== Задание 2 ===");
        Task2 task2 = new Task2('A', 'D');
        System.out.print("Символы от A до D: ");
        task2.printCharactersBetween();

        // Дополнительный пример для обратного порядка
        Task2 task2Reverse = new Task2('D', 'A');
        System.out.print("Символы от D до A: ");
        task2Reverse.printCharactersBetween();

        System.out.println("\n=== Задание 3 ===");
        Task3 task3_1 = new Task3();
        System.out.print("Конструктор без аргументов: ");
        task3_1.displayValues();

        Task3 task3_2 = new Task3(10);
        System.out.print("Конструктор с одним аргументом: ");
        task3_2.displayValues();

        Task3 task3_3 = new Task3(20, 30);
        System.out.print("Конструктор с двумя аргументами: ");
        task3_3.displayValues();

        System.out.println("\n=== Задание 4 ===");
        Task4 task4_1 = new Task4(10, 'B');
        System.out.println("Конструктор с int и char:");
        task4_1.displayValues();

        Task4 task4_2 = new Task4(65.1267);
        System.out.println("\nКонструктор с double (65.1267):");
        task4_2.displayValues();

        Task4 task4_3 = new Task4(97.998);
        System.out.println("\nКонструктор с double (97.998):");
        task4_3.displayValues();

        System.out.println("\n=== Задание 5 ===");
        Task5 task5_1 = new Task5();
        System.out.print("Конструктор без аргументов: ");
        task5_1.displayValue();

        Task5 task5_2 = new Task5(50);
        System.out.print("Конструктор с аргументом 50: ");
        task5_2.displayValue();

        Task5 task5_3 = new Task5(150);
        System.out.print("Конструктор с аргументом 150 (ограничение до 100): ");
        task5_3.displayValue();

        // Демонстрация методов setValue
        task5_1.setValue(75);
        System.out.print("После setValue(75): ");
        task5_1.displayValue();

        task5_2.setValue();
        System.out.print("После setValue() без аргументов: ");
        task5_2.displayValue();

        System.out.println("\n=== Задание 6 ===");
        Task6 task6_1 = new Task6();
        System.out.print("Конструктор без аргументов: ");
        task6_1.displayValues();

        Task6 task6_2 = new Task6(10);
        System.out.print("Конструктор с одним аргументом (10): ");
        task6_2.displayValues();

        Task6 task6_3 = new Task6(20, 30);
        System.out.print("Конструктор с двумя аргументами (20, 30): ");
        task6_3.displayValues();

        // Демонстрация методов setValues
        task6_3.setValues(40, 50);
        System.out.print("После setValues(40, 50): ");
        task6_3.displayValues();

        task6_3.setValues(25, 15);
        System.out.print("После setValues(25, 15) (сохраняется max=50): ");
        task6_3.displayValues();

        task6_3.setValues();
        System.out.print("После setValues() без аргументов: ");
        task6_3.displayValues();

        System.out.println("\n=== Все задания выполнены! ===");
    }
}