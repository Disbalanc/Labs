public class Solution {

    /**
     * Алгоритм:
     *  1. Создаём numRows строк-«рядов» (StringBuilder).
     *  2. Проходим по каждому символу строки s.
     *  3. Добавляем символ в текущий ряд.
     *  4. Меняем направление движения по рядам:
     *       - идём вниз (0 → numRows-1), затем
     *       - идём вверх (numRows-1 → 0), и так по кругу.
     *  5. Склеиваем все ряды в итоговую строку.
     *
     * Сложность: O(n) по времени и памяти.
     */
    public String convert(String s, int numRows) {
        // Граничный случай: один ряд или строка короче numRows
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        // Создаём StringBuilder для каждого ряда
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;    // текущий ряд
        boolean goingDown = false; // направление движения

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Меняем направление на краях
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Переходим на следующий ряд
            currentRow += goingDown ? 1 : -1;
        }

        // Склеиваем все ряды
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    // ----------------------------------------------------------------
    // Точка входа для тестирования
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Пример 1 → "PAHNAPLSIIGYIR"
        System.out.println("Пример 1: " + sol.convert("PAYPALISHIRING", 3));

        // Пример 2 → "PINALSIGYAHRPI"
        System.out.println("Пример 2: " + sol.convert("PAYPALISHIRING", 4));

        // Пример 3 → "A"
        System.out.println("Пример 3: " + sol.convert("A", 1));

        // Визуализация для примера 1 (numRows = 3):
        // P   A   H   N
        // A P L S I I G
        // Y   I   R
        // → PAHNAPLSIIGYIR ✓

        // Визуализация для примера 2 (numRows = 4):
        // P     I    N
        // A   L S  I G
        // Y A   H R
        // P     I
        // → PINALSIGYAHRPI ✓
    }
}