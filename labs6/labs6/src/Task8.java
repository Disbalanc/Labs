// Задача 8: Среднее значение массива
public class Task8 {
    public static class ArrayAverage {
        public static double calculate(int[] array) {
            if (array.length == 0) return 0;

            double sum = 0;
            for (int num : array) sum += num;
            return sum / array.length;
        }
    }
}