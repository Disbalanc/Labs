import java.util.Arrays;

// Задача 6: Первые N элементов массива
public class Task6 {
    public static class ArrayUtils {
        public static int[] getFirstElements(int[] array, int n) {
            if (n <= 0) return new int[0];
            if (n >= array.length) return Arrays.copyOf(array, array.length);
            return Arrays.copyOfRange(array, 0, n);
        }
    }
}