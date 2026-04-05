import java.util.Scanner;

public class RobotReturnToOrigin {

    /**
     * Основной метод решения - подсчёт координат
     * Время: O(n), Память: O(1)
     */
    public static boolean judgeCircle(String moves) {
        int x = 0, y = 0;

        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
            }
        }

        return x == 0 && y == 0;
    }

    /**
     * Альтернативное решение - подсчёт символов
     */
    public static boolean judgeCircleCount(String moves) {
        int horizontal = 0;
        int vertical = 0;

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'U') vertical++;
            else if (c == 'D') vertical--;
            else if (c == 'L') horizontal--;
            else if (c == 'R') horizontal++;
        }

        return horizontal == 0 && vertical == 0;
    }

    /**
     * Решение с визуализацией пути
     */
    public static boolean judgeCircleWithVisualization(String moves) {
        int x = 0, y = 0;

        System.out.println("\n=== Визуализация движения ===");
        System.out.println("Старт: (" + x + ", " + y + ")");

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);

            switch (move) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
            }

            System.out.println("Шаг " + (i + 1) + ": " + move + " → (" + x + ", " + y + ")");
        }

        System.out.println("Финиш: (" + x + ", " + y + ")");

        return x == 0 && y == 0;
    }

    /**
     * Запуск тестовых примеров
     */
    public static void runTests() {
        System.out.println("=== Тестирование ===\n");

        String[] testCases = {"UD", "LL", "UDLR", "UUDDLLRR", "UDRL", "LLRR", "UUDD", "ULDR"};
        boolean[] expected = {true, false, true, true, true, true, true, true};

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            boolean result = judgeCircle(testCases[i]);
            boolean isCorrect = result == expected[i];

            if (isCorrect) passed++;

            System.out.printf("Тест %d: moves = \"%s\"%n", i + 1, testCases[i]);
            System.out.printf("   Результат: %s | Ожидалось: %s | %s%n",
                    result, expected[i], isCorrect ? "✓ PASSED" : "✗ FAILED");
        }

        System.out.printf("%nПройдено тестов: %d/%d%n", passed, testCases.length);
    }

    /**
     * Интерактивный режим
     */
    public static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Интерактивный режим ===");
        System.out.println("Введите последовательность команд (U/D/L/R)");
        System.out.println("Введите 'exit' для выхода\n");

        while (true) {
            System.out.print("Введите moves: ");
            String input = scanner.nextLine().toUpperCase().trim();

            if (input.equals("EXIT")) {
                System.out.println("Выход из программы.");
                break;
            }

            // Проверка корректности ввода
            if (!input.matches("[UDLR]*")) {
                System.out.println("Ошибка: допустимы только символы U, D, L, R\n");
                continue;
            }

            if (input.isEmpty()) {
                System.out.println("Робот не двигался - остался в начале: true\n");
                continue;
            }

            boolean result = judgeCircleWithVisualization(input);
            System.out.println("Робот вернулся в начало: " + result + "\n");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Примеры из условия задачи
        System.out.println("\n=== Примеры из задачи ===");

        String moves1 = "UD";
        System.out.println("Input: moves = \"" + moves1 + "\"");
        System.out.println("Output: " + judgeCircle(moves1));
        System.out.println("Explanation: Робот идёт вверх, затем вниз. Возвращается в (0,0).\n");

        String moves2 = "LL";
        System.out.println("Input: moves = \"" + moves2 + "\"");
        System.out.println("Output: " + judgeCircle(moves2));
        System.out.println("Explanation: Робот идёт влево дважды. Заканчивает в (-2,0).\n");

        // Запуск тестов
        runTests();

        // Интерактивный режим
        interactiveMode();
    }
}