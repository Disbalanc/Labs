import java.util.Scanner;

/// Цикл for
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел для проверки: ");
        int n = scanner.nextInt();

        int sum = 0;
        System.out.print("Числа, которые суммируются: ");

        for (int i = 1; i <= n; i++) {
            if (i % 5 == 2 || i % 3 == 1) {
                System.out.print(i + " ");
                sum += i;
            }
        }

        System.out.println("\nСумма: " + sum);
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
//        System.out.print("Введите количество чисел для проверки: ");
//        int n = scanner.nextInt();
//
//        int sum = 0;
//        int i = 1;
//
//        System.out.print("Числа, которые суммируются: ");
//        while (i <= n) {
//            if (i % 5 == 2 || i % 3 == 1) {
//                System.out.print(i + " ");
//                sum += i;
//            }
//            i++;
//        }
//
//        System.out.println("\nСумма: " + sum);
//        scanner.close();
//    }
//}