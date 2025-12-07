import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] array = new Integer[10]; // Используем Integer вместо int для Collections.reverseOrder()

        // Заполнение массива случайными числами от 1 до 100
        System.out.print("Исходный массив: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) + 1;
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // Сортировка по убыванию
        Arrays.sort(array, Collections.reverseOrder());

        // Вывод отсортированного массива
        System.out.print("Отсортированный массив (по убыванию): ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}