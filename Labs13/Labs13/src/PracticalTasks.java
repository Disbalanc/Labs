import java.util.*;

public class PracticalTasks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- ЗАДАНИЕ 1: Среднее положительных ---");
        task1(sc);

        System.out.println("\n--- ЗАДАНИЕ 2: Столбец матрицы ---");
        task2(sc);

        System.out.println("\n--- ЗАДАНИЕ 3: Сумма byte ---");
        task3(sc);
    }

    // Задание 1
    public static void task1(Scanner sc) {
        try {
            System.out.print("Введите количество элементов массива: ");
            int n = Integer.parseInt(sc.nextLine());
            int[] arr = new int[n];

            System.out.println("Введите элементы (int):");
            int sum = 0, count = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(sc.nextLine());
                if (arr[i] > 0) {
                    sum += arr[i];
                    count++;
                }
            }

            if (count == 0) throw new ArithmeticException("Положительные элементы отсутствуют");
            System.out.println("Среднее значение: " + (double)sum/count);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введена строка вместо числа или неверный тип данных.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e);
        }
    }

    // Задание 2
    public static void task2(Scanner sc) {
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        try {
            System.out.print("Введите номер столбца (0 или 1): ");
            int col = Integer.parseInt(sc.nextLine());

            System.out.println("Столбец " + col + ":");
            for (int i = 0; i < matrix.length; i++) {
                System.out.println(matrix[i][col]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Нужно ввести целое число.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Столбца с таким номером не существует.");
        }
    }

    // Задание 3
    public static void task3(Scanner sc) {
        try {
            System.out.print("Сколько чисел типа byte сложить? ");
            int n = Integer.parseInt(sc.nextLine());
            int totalSum = 0;

            for (int i = 0; i < n; i++) {
                System.out.print("Введите byte-число: ");
                totalSum += Byte.parseByte(sc.nextLine()); // Ошибка если не в диапазоне -128...127
            }

            if (totalSum > Byte.MAX_VALUE || totalSum < Byte.MIN_VALUE) {
                throw new Exception("Результат вычисления за границами типа byte");
            }
            System.out.println("Итоговая сумма (byte): " + (byte)totalSum);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введено не число или значение вне диапазона byte.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}