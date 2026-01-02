// Задача 5: Сумма квадратов
public class Task5 {
    public static class SquareSum {
        public static long calculate(int n) {
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i * i;
            }
            return sum;
        }
    }
}