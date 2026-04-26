import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalTasks {

    // ============================================================
    // Задача 1: Вернуть только чётные числа из массива
    // ============================================================
    public static int[] filterEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .toArray();
    }

    // ============================================================
    // Задача 2: Вернуть элементы, присутствующие в обоих массивах
    // ============================================================
    public static int[] findCommonElements(int[] arr1, int[] arr2) {
        // Преобразуем второй массив в множество для O(1) поиска
        java.util.Set<Integer> set = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toSet());

        return Arrays.stream(arr1)
                .filter(set::contains)
                .distinct()
                .toArray();
    }

    // ============================================================
    // Задача 3: Вернуть строки, начинающиеся с заглавной буквы
    // ============================================================
    public static List<String> filterCapitalizedStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> s != null
                        && !s.isEmpty()
                        && Character.isUpperCase(s.charAt(0)))
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 4: Вернуть список квадратов чисел
    // ============================================================
    public static List<Double> squareNumbers(List<Number> numbers) {
        return numbers.stream()
                .map(n -> Math.pow(n.doubleValue(), 2))
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 5: Вернуть строки, содержащие заданную подстроку
    // ============================================================
    public static List<String> filterBySubstring(List<String> strings,
                                                 String substring) {
        return strings.stream()
                .filter(s -> s != null && s.contains(substring))
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 6: Вернуть числа, делящиеся на заданное без остатка
    // ============================================================
    public static List<Integer> filterDivisibleBy(List<Integer> numbers,
                                                  int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Делитель не может быть равен нулю!");
        }
        return numbers.stream()
                .filter(n -> n % divisor == 0)
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 7: Вернуть строки с длиной больше заданного значения
    // ============================================================
    public static List<String> filterByLength(List<String> strings,
                                              int minLength) {
        return strings.stream()
                .filter(s -> s != null && s.length() > minLength)
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 8: Вернуть числа, которые больше заданного значения
    // ============================================================
    public static List<Integer> filterGreaterThan(List<Integer> numbers,
                                                  int threshold) {
        return numbers.stream()
                .filter(n -> n > threshold)
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 9: Вернуть строки, содержащие только буквы
    // ============================================================
    public static List<String> filterOnlyLetters(List<String> strings) {
        return strings.stream()
                .filter(s -> s != null && s.matches("[a-zA-Zа-яА-ЯёЁ]+"))
                .collect(Collectors.toList());
    }

    // ============================================================
    // Задача 10: Вернуть числа, которые меньше заданного значения
    // ============================================================
    public static List<Integer> filterLessThan(List<Integer> numbers,
                                               int threshold) {
        return numbers.stream()
                .filter(n -> n < threshold)
                .collect(Collectors.toList());
    }
}