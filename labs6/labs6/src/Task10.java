// Задача 10: Максимум и минимум
public class Task10 {
    public static class MinMaxFinder {
        public static int[] findMinMax(int... numbers) {
            if (numbers.length == 0) return new int[]{0, 0};

            int min = numbers[0];
            int max = numbers[0];

            for (int num : numbers) {
                if (num < min) min = num;
                if (num > max) max = num;
            }

            return new int[]{max, min}; // [максимум, минимум]
        }
    }
}