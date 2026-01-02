// Задача 9: Обмен элементов местами
public class Task9 {
    public static class ArraySwapper {
        public static void swapPairs(char[] array) {
            for (int i = 0; i < array.length / 2; i++) {
                char temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        }
    }
}