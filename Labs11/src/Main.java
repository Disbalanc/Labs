import java.util.Arrays;
import java.util.List;

public class Main {

    // Вспомогательный метод для красивого вывода разделителя
    private static void printSeparator(int taskNumber, String description) {
        System.out.println("\n" + "=".repeat(55));
        System.out.printf("  Задача %2d: %s%n", taskNumber, description);
        System.out.println("=".repeat(55));
    }

    public static void main(String[] args) {

        // --------------------------------------------------------
        // Задача 1: Фильтрация чётных чисел
        // --------------------------------------------------------
        printSeparator(1, "Фильтрация чётных чисел");
        int[] numbers1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] evenNumbers = FunctionalTasks.filterEvenNumbers(numbers1);
        System.out.println("Исходный массив : " + Arrays.toString(numbers1));
        System.out.println("Чётные числа    : " + Arrays.toString(evenNumbers));

        // --------------------------------------------------------
        // Задача 2: Общие элементы двух массивов
        // --------------------------------------------------------
        printSeparator(2, "Общие элементы двух массивов");
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {4, 5, 6, 7, 8, 9};
        int[] common = FunctionalTasks.findCommonElements(arr1, arr2);
        System.out.println("Массив 1        : " + Arrays.toString(arr1));
        System.out.println("Массив 2        : " + Arrays.toString(arr2));
        System.out.println("Общие элементы  : " + Arrays.toString(common));

        // --------------------------------------------------------
        // Задача 3: Строки с заглавной буквы
        // --------------------------------------------------------
        printSeparator(3, "Строки, начинающиеся с заглавной буквы");
        List<String> strings3 = Arrays.asList(
                "Hello", "world", "Java", "stream", "API", "functional"
        );
        List<String> capitalized = FunctionalTasks.filterCapitalizedStrings(strings3);
        System.out.println("Исходный список : " + strings3);
        System.out.println("С заглавной     : " + capitalized);

        // --------------------------------------------------------
        // Задача 4: Квадраты чисел
        // --------------------------------------------------------
        printSeparator(4, "Квадраты чисел");
        List<Number> numbers4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Double> squares = FunctionalTasks.squareNumbers(numbers4);
        System.out.println("Исходный список : " + numbers4);
        System.out.println("Квадраты        : " + squares);

        // --------------------------------------------------------
        // Задача 5: Строки, содержащие подстроку
        // --------------------------------------------------------
        printSeparator(5, "Строки, содержащие заданную подстроку");
        List<String> strings5 = Arrays.asList(
                "apple", "application", "apply", "banana", "appetite", "orange"
        );
        String substring = "app";
        List<String> withSubstring = FunctionalTasks.filterBySubstring(strings5, substring);
        System.out.println("Исходный список : " + strings5);
        System.out.println("Подстрока       : \"" + substring + "\"");
        System.out.println("Результат       : " + withSubstring);

        // --------------------------------------------------------
        // Задача 6: Числа, делящиеся на заданное
        // --------------------------------------------------------
        printSeparator(6, "Числа, делящиеся без остатка на заданное");
        List<Integer> numbers6 = Arrays.asList(
                1, 2, 3, 4, 5, 6, 9, 12, 15, 18, 21, 25
        );
        int divisor = 3;
        List<Integer> divisible = FunctionalTasks.filterDivisibleBy(numbers6, divisor);
        System.out.println("Исходный список : " + numbers6);
        System.out.println("Делитель        : " + divisor);
        System.out.println("Результат       : " + divisible);

        // --------------------------------------------------------
        // Задача 7: Строки с длиной больше заданного значения
        // --------------------------------------------------------
        printSeparator(7, "Строки длиннее заданного значения");
        List<String> strings7 = Arrays.asList(
                "hi", "hello", "java", "stream", "functional", "api", "programming"
        );
        int minLength = 4;
        List<String> longStrings = FunctionalTasks.filterByLength(strings7, minLength);
        System.out.println("Исходный список : " + strings7);
        System.out.println("Мин. длина      : " + minLength);
        System.out.println("Результат       : " + longStrings);

        // --------------------------------------------------------
        // Задача 8: Числа, большие заданного значения
        // --------------------------------------------------------
        printSeparator(8, "Числа, большие заданного значения");
        List<Integer> numbers8 = Arrays.asList(
                -5, 0, 3, 7, 10, 15, 20, 25, 50
        );
        int thresholdGreater = 10;
        List<Integer> greaterThan = FunctionalTasks.filterGreaterThan(
                numbers8, thresholdGreater
        );
        System.out.println("Исходный список : " + numbers8);
        System.out.println("Порог           : " + thresholdGreater);
        System.out.println("Результат       : " + greaterThan);

        // --------------------------------------------------------
        // Задача 9: Строки, содержащие только буквы
        // --------------------------------------------------------
        printSeparator(9, "Строки только из букв (без цифр и символов)");
        List<String> strings9 = Arrays.asList(
                "hello", "world123", "Java", "stream!", "API", "func2tion", "test"
        );
        List<String> onlyLetters = FunctionalTasks.filterOnlyLetters(strings9);
        System.out.println("Исходный список : " + strings9);
        System.out.println("Только буквы    : " + onlyLetters);

        // --------------------------------------------------------
        // Задача 10: Числа, меньшие заданного значения
        // --------------------------------------------------------
        printSeparator(10, "Числа, меньшие заданного значения");
        List<Integer> numbers10 = Arrays.asList(
                -10, -5, 0, 3, 7, 10, 15, 20
        );
        int thresholdLess = 7;
        List<Integer> lessThan = FunctionalTasks.filterLessThan(numbers10, thresholdLess);
        System.out.println("Исходный список : " + numbers10);
        System.out.println("Порог           : " + thresholdLess);
        System.out.println("Результат       : " + lessThan);

        System.out.println("\n" + "=".repeat(55));
        System.out.println("  Все задачи выполнены успешно!");
        System.out.println("=".repeat(55));
    }
}