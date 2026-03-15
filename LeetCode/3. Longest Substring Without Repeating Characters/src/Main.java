public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("=== LeetCode 3: Longest Substring Without Repeating Characters ===\n");

        // Тест 1
        String s1 = "abcabcbb";
        int r1 = sol.lengthOfLongestSubstring(s1);
        System.out.println("Тест 1:");
        System.out.println("  Вход:   \"" + s1 + "\"");
        System.out.println("  Ответ:  " + r1 + " (ожидается 3, подстрока \"abc\")");
        assert r1 == 3;

        // Тест 2
        String s2 = "bbbbb";
        int r2 = sol.lengthOfLongestSubstring(s2);
        System.out.println("\nТест 2:");
        System.out.println("  Вход:   \"" + s2 + "\"");
        System.out.println("  Ответ:  " + r2 + " (ожидается 1, подстрока \"b\")");
        assert r2 == 1;

        // Тест 3
        String s3 = "pwwkew";
        int r3 = sol.lengthOfLongestSubstring(s3);
        System.out.println("\nТест 3:");
        System.out.println("  Вход:   \"" + s3 + "\"");
        System.out.println("  Ответ:  " + r3 + " (ожидается 3, подстрока \"wke\")");
        assert r3 == 3;

        // Тест 4: пустая строка
        String s4 = "";
        int r4 = sol.lengthOfLongestSubstring(s4);
        System.out.println("\nТест 4:");
        System.out.println("  Вход:   \"" + s4 + "\"");
        System.out.println("  Ответ:  " + r4 + " (ожидается 0)");
        assert r4 == 0;

        // Тест 5: один символ
        String s5 = "a";
        int r5 = sol.lengthOfLongestSubstring(s5);
        System.out.println("\nТест 5:");
        System.out.println("  Вход:   \"" + s5 + "\"");
        System.out.println("  Ответ:  " + r5 + " (ожидается 1)");
        assert r5 == 1;

        // Тест 6: все уникальные
        String s6 = "abcdefg";
        int r6 = sol.lengthOfLongestSubstring(s6);
        System.out.println("\nТест 6:");
        System.out.println("  Вход:   \"" + s6 + "\"");
        System.out.println("  Ответ:  " + r6 + " (ожидается 7)");
        assert r6 == 7;

        // Тест 7: с пробелами и символами
        String s7 = "ab cd!ab";
        int r7 = sol.lengthOfLongestSubstring(s7);
        System.out.println("\nТест 7:");
        System.out.println("  Вход:   \"" + s7 + "\"");
        System.out.println("  Ответ:  " + r7 + " (ожидается 6, подстрока \"cd!ab\" или \"d!ab \")");

        // Пошаговая демонстрация
        System.out.println("\n=== Пошаговая демонстрация для \"abcabcbb\" ===");
        demonstrateStep("abcabcbb");

        System.out.println("\n✓ Все тесты выполнены!");
    }

    private static void demonstrateStep(String s) {
        java.util.Map<Character, Integer> lastSeen = new java.util.HashMap<>();
        int maxLen = 0;
        int left = 0;

        System.out.printf("%-6s %-6s %-6s %-12s %-8s %-10s%n",
                "right", "char", "left", "window", "len", "maxLen");
        System.out.println("------------------------------------------------------");

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }

            lastSeen.put(c, right);
            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);

            String window = "\"" + s.substring(left, right + 1) + "\"";
            System.out.printf("%-6d %-6c %-6d %-12s %-8d %-10d%n",
                    right, c, left, window, len, maxLen);
        }
    }
}