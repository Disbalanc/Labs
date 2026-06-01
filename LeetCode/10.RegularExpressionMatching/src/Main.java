public class Main {

    /**
     * Метод для проверки соответствия строки паттерну.
     */
    public boolean isMatch(String s, String p) {
        // Используем вспомогательный массив для мемоизации (кэширования результатов)
        // Чтобы не вычислять одни и те же состояния по несколько раз
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        // Если результат уже вычислен, возвращаем его
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean result;
        // Если паттерн закончился, то строка тоже должна закончиться для успеха
        if (j == p.length()) {
            result = (i == s.length());
        } else {
            // Проверяем, совпадает ли первый символ
            boolean firstMatch = (i < s.length() &&
                    (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

            // Если следующий символ в паттерне — '*', у нас есть два пути
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 1. Пропускаем часть паттерна "символ*" (считаем за 0 повторений)
                // 2. Либо используем '*' для текущего символа (если был firstMatch)
                //    и остаемся на том же месте в паттерне для строки s+1
                result = (dp(i, j + 2, s, p, memo) ||
                        (firstMatch && dp(i + 1, j, s, p, memo)));
            } else {
                // Обычный символ или '.' без '*' — просто идем дальше
                result = firstMatch && dp(i + 1, j + 1, s, p, memo);
            }
        }

        memo[i][j] = result;
        return result;
    }

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println("=== Тестирование Regular Expression Matching ===");

        // Тест 1
        System.out.println("Тест 1 (s='aa', p='a'): " + solution.isMatch("aa", "a")); // false

        // Тест 2
        System.out.println("Тест 2 (s='aa', p='a*'): " + solution.isMatch("aa", "a*")); // true

        // Тест 3
        System.out.println("Тест 3 (s='ab', p='.*'): " + solution.isMatch("ab", ".*")); // true

        // Тест 4 (сложный случай)
        System.out.println("Тест 4 (s='aab', p='c*a*b'): " + solution.isMatch("aab", "c*a*b")); // true
        // Объяснение: c* означает ноль символов 'c', a* означает два 'a', b совпадает с b.

        // Тест 5
        System.out.println("Тест 5 (s='mississippi', p='mis*is*p*.'): " +
                solution.isMatch("mississippi", "mis*is*p*.")); // false
    }
}