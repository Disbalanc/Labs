// Задача 3: Математические вычисления
public class Task3 {
    public static class MathUtils {
        public static int max(int... numbers) {
            int max = numbers[0];
            for (int num : numbers) if (num > max) max = num;
            return max;
        }

        public static int min(int... numbers) {
            int min = numbers[0];
            for (int num : numbers) if (num < min) min = num;
            return min;
        }

        public static double average(int... numbers) {
            int sum = 0;
            for (int num : numbers) sum += num;
            return (double) sum / numbers.length;
        }
    }
}