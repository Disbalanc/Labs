import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== Задача 1: Прямоугольник ===");
        task1();

        System.out.println("\n=== Задача 2: Прямоугольный треугольник ===");
        task2();

        System.out.println("\n=== Задача 3: Прямоугольник из цифр 2 ===");
        task3();

        System.out.println("\n=== Задача 4: Треугольник из цифр 2 ===");
        task4();

        System.out.println("\n=== Задача 5: Транспонирование массива ===");
        task5();

        System.out.println("\n=== Задача 6: Удаление строки и столбца ===");
        task6();

        System.out.println("\n=== Задача 7: Заполнение змейкой ===");
        task7();

        System.out.println("\n=== Задача 8: Шифр Цезаря (стандартный) ===");
        caesarCipherStandard();

        System.out.println("\n=== Задача 10: Шифр Цезаря (собственный алфавит) ===");
        caesarCipherCustom();
    }

    // Задача 1: Прямоугольник 23x11
    public static void task1() {
        int width = 23;
        int height = 11;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // Задача 2: Прямоугольный треугольник
    public static void task2() {
        int size = 10; // размер треугольника

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // Задача 3: Прямоугольник из цифр 2
    public static void task3() {
        int rows = 11;
        int cols = 23;
        int[][] array = new int[rows][cols];

        // Заполнение массива
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = 2;
            }
        }

        // Вывод массива
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    // Задача 4: Треугольник из цифр 2
    public static void task4() {
        int size = 10;
        int[][] triangle = new int[size][];

        // Создание и заполнение треугольного массива
        for (int i = 0; i < size; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = 2;
            }
        }

        // Вывод треугольника
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(triangle[i][j]);
            }
            System.out.println();
        }
    }

    // Задача 5: Транспонирование массива
    public static void task5() {
        int rows = 3;
        int cols = 5;
        int[][] original = new int[rows][cols];

        // Заполнение случайными числами
        System.out.println("Исходный массив:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                original[i][j] = random.nextInt(100);
                System.out.print(original[i][j] + "\t");
            }
            System.out.println();
        }

        // Транспонирование
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = original[i][j];
            }
        }

        // Вывод транспонированного массива
        System.out.println("\nТранспонированный массив:");
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(transposed[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Задача 6: Удаление строки и столбца
    public static void task6() {
        int rows = 5;
        int cols = 6;
        int[][] array = new int[rows][cols];

        // Инициализация массива
        System.out.println("Исходный массив:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = i * cols + j + 1;
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

        // Выбор случайных строки и столбца для удаления
        int rowToRemove = random.nextInt(rows);
        int colToRemove = random.nextInt(cols);

        System.out.println("\nУдаляем строку " + rowToRemove + " и столбец " + colToRemove);

        // Создание нового массива
        int[][] newArray = new int[rows - 1][cols - 1];

        int newRow = 0;
        for (int i = 0; i < rows; i++) {
            if (i == rowToRemove) continue;

            int newCol = 0;
            for (int j = 0; j < cols; j++) {
                if (j == colToRemove) continue;

                newArray[newRow][newCol] = array[i][j];
                newCol++;
            }
            newRow++;
        }

        // Вывод нового массива
        System.out.println("Массив после удаления:");
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                System.out.print(newArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Задача 7: Заполнение змейкой
    public static void task7() {
        int n = 5; // размер квадратной матрицы
        int[][] array = new int[n][n];
        int value = 1;

        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            // Верхняя строка (слева направо)
            for (int i = layer; i < n - layer; i++) {
                array[layer][i] = value++;
            }

            // Правый столбец (сверху вниз, без первого элемента)
            for (int i = layer + 1; i < n - layer; i++) {
                array[i][n - 1 - layer] = value++;
            }

            // Нижняя строка (справа налево, без последнего элемента)
            for (int i = n - 2 - layer; i >= layer; i--) {
                array[n - 1 - layer][i] = value++;
            }

            // Левый столбец (снизу вверх, без первого и последнего элементов)
            for (int i = n - 2 - layer; i > layer; i--) {
                array[i][layer] = value++;
            }
        }

        // Вывод массива
        System.out.println("Массив, заполненный змейкой:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Задача 8: Шифр Цезаря (стандартный)
    public static void caesarCipherStandard() {
        scanner.nextLine(); // Очистка буфера

        System.out.print("Введите текст для шифрования: ");
        String text = scanner.nextLine();

        System.out.print("Введите ключ (сдвиг): ");
        int key = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        // Шифрование
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + key) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }

        System.out.println("Текст после преобразования: " + encrypted);

        // Запрос на обратное преобразование
        System.out.print("Выполнить обратное преобразование? (y/n): ");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("y")) {
            StringBuilder decrypted = new StringBuilder();
            for (char c : encrypted.toString().toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    decrypted.append((char) ((c - base - key + 26) % 26 + base));
                } else {
                    decrypted.append(c);
                }
            }
            System.out.println("Обратное преобразование: " + decrypted);
        } else if (answer.equals("n")) {
            System.out.println("До свидания!");
        } else {
            System.out.println("Введите корректный ответ");
        }
    }

    // Задача 10: Шифр Цезаря (собственный алфавит)
    public static void caesarCipherCustom() {
        scanner.nextLine(); // Очистка буфера

        // Собственный алфавит (можно изменить)
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        System.out.print("Введите текст для шифрования: ");
        String text = scanner.nextLine().toLowerCase();

        System.out.print("Введите ключ (сдвиг): ");
        int key = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        // Шифрование
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                encrypted.append(alphabet.charAt((index + key) % alphabet.length()));
            } else {
                encrypted.append(c);
            }
        }

        System.out.println("Текст после преобразования: " + encrypted);

        // Запрос на обратное преобразование
        System.out.print("Выполнить обратное преобразование? (y/n): ");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("y")) {
            StringBuilder decrypted = new StringBuilder();
            for (char c : encrypted.toString().toCharArray()) {
                int index = alphabet.indexOf(c);
                if (index != -1) {
                    decrypted.append(alphabet.charAt((index - key + alphabet.length()) % alphabet.length()));
                } else {
                    decrypted.append(c);
                }
            }
            System.out.println("Обратное преобразование: " + decrypted);
        } else if (answer.equals("n")) {
            System.out.println("До свидания!");
        } else {
            System.out.println("Введите корректный ответ");
        }
    }
}