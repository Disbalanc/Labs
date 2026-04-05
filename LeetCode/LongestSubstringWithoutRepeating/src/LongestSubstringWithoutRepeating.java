import java.util.*;

public class LongestSubstringWithoutRepeating {

    /**
     * Решение 1: Sliding Window с HashSet (Оптимальное)
     * Время: O(n), Память: O(min(n, m)) где m - размер алфавита
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Set<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Если символ уже есть в окне, сдвигаем левую границу
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Решение 2: Sliding Window с HashMap (Оптимизированное)
     * Более эффективное - можно сразу прыгнуть к нужной позиции
     * Время: O(n), Память: O(min(n, m))
     */
    public static int lengthOfLongestSubstringOptimized(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Если символ уже встречался, обновляем left
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            charIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Решение 3: С визуализацией процесса
     */
    public static int lengthOfLongestSubstringWithVisualization(String s) {
        if (s == null || s.length() == 0) return 0;

        System.out.println("\n=== Визуализация процесса ===");
        System.out.println("Строка: \"" + s + "\"\n");

        Set<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        String longestSubstring = "";

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Удаляем символы слева, пока не уберём дубликат
            while (charSet.contains(currentChar)) {
                System.out.println("  Дубликат '" + currentChar + "' найден!");
                System.out.println("  Удаляем: '" + s.charAt(left) + "'");
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(currentChar);
            int currentLength = right - left + 1;

            String currentSubstring = s.substring(left, right + 1);
            System.out.printf("Шаг %2d: left=%d, right=%d | Окно: \"%s\" | Длина: %d%n",
                    right + 1, left, right, currentSubstring, currentLength);

            if (currentLength > maxLength) {
                maxLength = currentLength;
                longestSubstring = currentSubstring;
            }
        }

        System.out.println("\nСамая длинная подстрока: \"" + longestSubstring + "\"");
        System.out.println("Длина: " + maxLength);

        return maxLength;
    }

    /**
     * Решение 4: Brute Force (для сравнения)
     * Время: O(n³), Память: O(min(n, m))
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    /**
     * Запуск тестовых примеров
     */
    public static void runTests() {
        System.out.println("\n=== Автоматические тесты ===\n");

        String[] testCases = {
                "abcabcbb",
                "bbbbb",
                "pwwkew",
                "",
                "au",
                "dvdf",
                "tmmzuxt",
                "abcdefghijklmnopqrstuvwxyz",
                "aab",
                "cdd"
        };

        int[] expected = {3, 1, 3, 0, 2, 3, 5, 26, 2, 2};

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int result = lengthOfLongestSubstring(testCases[i]);
            boolean isCorrect = result == expected[i];

            if (isCorrect) passed++;

            System.out.printf("Тест %2d: s = %-30s", i + 1, "\"" + testCases[i] + "\"");
            System.out.printf(" → Результат: %2d | Ожидалось: %2d | %s%n",
                    result, expected[i], isCorrect ? "✓" : "✗");
        }

        System.out.printf("%nПройдено: %d/%d тестов%n", passed, testCases.length);
    }

    /**
     * Сравнение производительности разных методов
     */
    public static void performanceComparison() {
        System.out.println("\n=== Сравнение производительности ===\n");

        String[] testStrings = {
                "abcabcbb",
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
                generateRandomString(1000),
                generateRandomString(5000)
        };

        String[] descriptions = {
                "Короткая строка (8 символов)",
                "Средняя строка (62 символа)",
                "Длинная строка (1000 символов)",
                "Очень длинная строка (5000 символов)"
        };

        for (int i = 0; i < testStrings.length; i++) {
            System.out.println(descriptions[i] + ":");

            // HashSet метод
            long start = System.nanoTime();
            int result1 = lengthOfLongestSubstring(testStrings[i]);
            long time1 = System.nanoTime() - start;

            // HashMap метод
            start = System.nanoTime();
            int result2 = lengthOfLongestSubstringOptimized(testStrings[i]);
            long time2 = System.nanoTime() - start;

            System.out.printf("  HashSet:  %6d нс | Результат: %d%n", time1, result1);
            System.out.printf("  HashMap:  %6d нс | Результат: %d%n", time2, result2);

            // Brute Force только для коротких строк
            if (testStrings[i].length() <= 100) {
                start = System.nanoTime();
                int result3 = lengthOfLongestSubstringBruteForce(testStrings[i]);
                long time3 = System.nanoTime() - start;
                System.out.printf("  BruteForce: %6d нс | Результат: %d%n", time3, result3);
            }

            System.out.println();
        }
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    /**
     * Интерактивный режим
     */
    public static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Интерактивный режим ===");
        System.out.println("Введите строку для поиска самой длинной подстроки без повторов");
        System.out.println("В��едите 'exit' для выхода\n");

        while (true) {
            System.out.print("Введите строку: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.println("\nВыберите метод:");
            System.out.println("1. Быстрый расчёт (HashSet)");
            System.out.println("2. Оптимизированный (HashMap)");
            System.out.println("3. С визуализацией");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();

            int result = 0;
            long startTime = System.nanoTime();

            switch (choice) {
                case "1":
                    result = lengthOfLongestSubstring(input);
                    break;
                case "2":
                    result = lengthOfLongestSubstringOptimized(input);
                    break;
                case "3":
                    result = lengthOfLongestSubstringWithVisualization(input);
                    break;
                default:
                    System.out.println("Неверный выбор!\n");
                    continue;
            }

            long endTime = System.nanoTime();

            if (!choice.equals("3")) {
                System.out.println("\nРезультат: " + result);
            }
            System.out.println("Время выполнения: " + (endTime - startTime) + " нс\n");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Примеры из условия
        System.out.println("\n=== Примеры из задачи ===");

        String s1 = "abcabcbb";
        System.out.println("\nExample 1:");
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + lengthOfLongestSubstring(s1));
        System.out.println("Explanation: Подстрока \"abc\" имеет длину 3");

        String s2 = "bbbbb";
        System.out.println("\nExample 2:");
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + lengthOfLongestSubstring(s2));
        System.out.println("Explanation: Подстрока \"b\" имеет длину 1");

        String s3 = "pwwkew";
        System.out.println("\nExample 3:");
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: " + lengthOfLongestSubstring(s3));
        System.out.println("Explanation: Подстрока \"wke\" имеет длину 3");

        // Демонстрация с визуализацией
        System.out.println("\n" + "=".repeat(50));
        lengthOfLongestSubstringWithVisualization("abcabcbb");

        // Запуск тестов
        runTests();

        // Сравнение производительности
        performanceComparison();

        // Интерактивный режим
        interactiveMode();
    }
}