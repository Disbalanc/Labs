import java.util.Scanner;

/// Цикл for
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел Фибоначчи: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Количество должно быть положительным");
        } else if (n == 1) {
            System.out.println("1");
        } else {
            long a = 1, b = 1;
            System.out.print("1 1 ");

            for (int i = 3; i <= n; i++) {
                long next = a + b;
                System.out.print(next + " ");
                a = b;
                b = next;
            }
            System.out.println();
        }

        scanner.close();
    }
}

/// Цикл while
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите количество чисел Фибоначчи: ");
//        int n = scanner.nextInt();
//
//        if (n <= 0) {
//            System.out.println("Количество должно быть положительным");
//        } else if (n == 1) {
//            System.out.println("1");
//        } else {
//            long a = 1, b = 1;
//            System.out.print("1 1 ");
//
//            int count = 2;
//            while (count < n) {
//                long next = a + b;
//                System.out.print(next + " ");
//                a = b;
//                b = next;
//                count++;
//            }
//            System.out.println();
//        }
//
//        scanner.close();
//    }
//}

/// Цикл do-while
//import java.util.Scanner;
//
//public class Task3_DoWhile {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите количество чисел Фибоначчи: ");
//        int n = scanner.nextInt();
//
//        if (n <= 0) {
//            System.out.println("Количество должно быть положительным");
//        } else {
//            long a = 1, b = 1;
//            int count = 0;
//
//            do {
//                count++;
//                if (count == 1) {
//                    System.out.print("1 ");
//                } else if (count == 2) {
//                    System.out.print("1 ");
//                } else {
//                    long next = a + b;
//                    System.out.print(next + " ");
//                    a = b;
//                    b = next;
//                }
//            } while (count < n);
//
//            System.out.println();
//        }
//
//        scanner.close();
//    }
//}