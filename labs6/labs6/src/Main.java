import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ 10 ЗАДАЧ ===");
        System.out.println();

        // Задача 1
        System.out.println("ЗАДАЧА 1: Класс с перегруженным методом присваивания");
        Task1.TextCharContainer container = new Task1.TextCharContainer();
        container.assignValue('A');
        container.assignValue("Привет, мир!");
        container.assignValue(new char[]{'X'});
        container.assignValue(new char[]{'J', 'a', 'v', 'a'});
        System.out.println();

        // Задача 2
        System.out.println("ЗАДАЧА 2: Класс со статическим счетчиком");
        for (int i = 0; i < 3; i++) {
            Task2.StaticCounter.displayAndIncrement();
        }
        System.out.println();

        // Задача 3
        System.out.println("ЗАДАЧА 3: Математические вычисления");
        int[] numbers = {7, 3, 9, 1, 5};
        System.out.println("Массив: " + Arrays.toString(numbers));
        System.out.println("Максимум: " + Task3.MathUtils.max(numbers));
        System.out.println("Минимум: " + Task3.MathUtils.min(numbers));
        System.out.println("Среднее: " + Task3.MathUtils.average(numbers));
        System.out.println();

        // Задача 4
        System.out.println("ЗАДАЧА 4: Двойной факториал");
        System.out.println("5!! = " + Task4.DoubleFactorial.calculate(5));
        System.out.println("6!! = " + Task4.DoubleFactorial.calculate(6));
        System.out.println("7!! = " + Task4.DoubleFactorial.calculate(7));
        System.out.println();

        // Задача 5
        System.out.println("ЗАДАЧА 5: Сумма квадратов");
        int n = 5;
        System.out.println("Сумма квадратов 1²+2²+...+5² = " + Task5.SquareSum.calculate(n));
        System.out.println("Проверка по формуле: n(n+1)(2n+1)/6 = " + (n * (n + 1) * (2 * n + 1) / 6));
        System.out.println();

        // Задача 6
        System.out.println("ЗАДАЧА 6: Первые N элементов массива");
        int[] original = {10, 20, 30, 40, 50, 60, 70};
        System.out.println("Исходный массив: " + Arrays.toString(original));
        System.out.println("Первые 3 элемента: " + Arrays.toString(Task6.ArrayUtils.getFirstElements(original, 3)));
        System.out.println("Первые 10 элементов (больше длины): " + Arrays.toString(Task6.ArrayUtils.getFirstElements(original, 10)));
        System.out.println();

        // Задача 7
        System.out.println("ЗАДАЧА 7: Коды символов");
        char[] chars = {'A', 'Б', '1', ' ', '!'};
        int[] codes = Task7.CharConverter.convertToCodes(chars);
        System.out.println("Символы: " + Arrays.toString(chars));
        System.out.println("Коды: " + Arrays.toString(codes));
        for (int i = 0; i < chars.length; i++) {
            System.out.println("  '" + chars[i] + "' -> " + codes[i]);
        }
        System.out.println();

        // Задача 8
        System.out.println("ЗАДАЧА 8: Среднее значение массива");
        int[] nums = {15, 25, 35, 45, 55};
        System.out.println("Массив: " + Arrays.toString(nums));
        System.out.println("Среднее арифметическое: " + Task8.ArrayAverage.calculate(nums));
        System.out.println();

        // Задача 9
        System.out.println("ЗАДАЧА 9: Обмен элементов местами");
        char[] array1 = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] array2 = {'1', '2', '3', '4', '5'};
        System.out.println("До обмена (четное кол-во): " + Arrays.toString(array1));
        Task9.ArraySwapper.swapPairs(array1);
        System.out.println("После обмена: " + Arrays.toString(array1));
        System.out.println("До обмена (нечетное кол-во): " + Arrays.toString(array2));
        Task9.ArraySwapper.swapPairs(array2);
        System.out.println("После обмена: " + Arrays.toString(array2));
        System.out.println();

        // Задача 10
        System.out.println("ЗАДАЧА 10: Максимум и минимум");
        int[] minMax1 = Task10.MinMaxFinder.findMinMax(3, 8, 1, 9, 4, 6);
        int[] minMax2 = Task10.MinMaxFinder.findMinMax(42);
        int[] minMax3 = Task10.MinMaxFinder.findMinMax(-5, -1, -8, -3);
        System.out.println("Макс/Мин для [3,8,1,9,4,6]: [" + minMax1[0] + ", " + minMax1[1] + "]");
        System.out.println("Макс/Мин для [42]: [" + minMax2[0] + ", " + minMax2[1] + "]");
        System.out.println("Макс/Мин для [-5,-1,-8,-3]: [" + minMax3[0] + ", " + minMax3[1] + "]");
        System.out.println();

        System.out.println("=== ВСЕ ЗАДАЧИ ВЫПОЛНЕНЫ ===");
    }
}