import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();

            if (size <= 0) {
                throw new IllegalArgumentException("Размер массива должен быть положительным");
            }

            int[] array = new int[size];
            int number = 2; // Первое число, дающее остаток 2 при делении на 5

            System.out.print("Массив: ");
            for (int i = 0; i < size; i++) {
                array[i] = number;
                System.out.print(number + " ");
                number += 5; // Следующее число с таким же свойством
            }
            System.out.println();

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введено некорректное значение (требуется целое число)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}