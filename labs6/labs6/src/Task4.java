// Задача 4: Двойной факториал
public class Task4 {
    public static class DoubleFactorial {
        public static long calculate(int n) {
            if (n < 0) throw new IllegalArgumentException("Число должно быть неотрицательным");
            if (n == 0 || n == 1) return 1;

            long result = 1;
            for (int i = n; i > 0; i -= 2) {
                result *= i;
            }
            return result;
        }
    }
}