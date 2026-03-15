public class Main {
    public static void main(String[] args) {
        System.out.println("=== LeetCode 1622: Fancy Sequence ===\n");

        Fancy fancy = new Fancy();

        fancy.append(2);
        System.out.println("append(2)   → последовательность: [2]");

        fancy.addAll(3);
        System.out.println("addAll(3)   → последовательность: [5]");

        fancy.append(7);
        System.out.println("append(7)   → последовательность: [5, 7]");

        fancy.multAll(2);
        System.out.println("multAll(2)  → последовательность: [10, 14]");

        int r0 = fancy.getIndex(0);
        System.out.println("getIndex(0) → " + r0 + " (ожидается 10)");
        assert r0 == 10 : "Ошибка!";

        fancy.addAll(3);
        System.out.println("addAll(3)   → последовательность: [13, 17]");

        fancy.append(10);
        System.out.println("append(10)  → последовательность: [13, 17, 10]");

        fancy.multAll(2);
        System.out.println("multAll(2)  → последовательность: [26, 34, 20]");

        int r1 = fancy.getIndex(0);
        int r2 = fancy.getIndex(1);
        int r3 = fancy.getIndex(2);
        System.out.println("getIndex(0) → " + r1 + " (ожидается 26)");
        System.out.println("getIndex(1) → " + r2 + " (ожидается 34)");
        System.out.println("getIndex(2) → " + r3 + " (ожидается 20)");

        assert r1 == 26 : "Ошибка!";
        assert r2 == 34 : "Ошибка!";
        assert r3 == 20 : "Ошибка!";

        int r4 = fancy.getIndex(10);
        System.out.println("getIndex(10)→ " + r4 + " (ожидается -1)");
        assert r4 == -1 : "Ошибка!";

        System.out.println("\n✓ Все тесты пройдены!");

        // Дополнительный тест с большими числами
        System.out.println("\n--- Дополнительный тест (большие числа) ---");
        Fancy fancy2 = new Fancy();
        fancy2.append(100);
        for (int i = 0; i < 40; i++) {
            fancy2.multAll(100);
        }
        System.out.println("100 * 100^40 mod 10^9+7 = " + fancy2.getIndex(0));
    }
}