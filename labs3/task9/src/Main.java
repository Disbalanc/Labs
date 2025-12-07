import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[15]; // Размер массива можно изменить

        // Заполнение массива случайными числами от 1 до 100
        System.out.print("Массив: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) + 1;
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // Поиск минимального значения
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        // Вывод минимального значения и всех индексов, где оно встречается
        System.out.println("Минимальное значение: " + min);
        System.out.print("Индексы минимальных элементов: ");

        boolean first = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == min) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(i);
                first = false;
            }
        }
        System.out.println();
    }
}