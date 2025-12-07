import java.util.Scanner;

/// Цикл for
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();

        int start = Math.min(num1, num2);
        int end = Math.max(num1, num2);

        System.out.print("Числа в диапазоне: ");
        for (int i = start; i <= end; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

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
//        System.out.print("Введите первое число: ");
//        int num1 = scanner.nextInt();
//        System.out.print("Введите второе число: ");
//        int num2 = scanner.nextInt();
//
//        int start = Math.min(num1, num2);
//        int end = Math.max(num1, num2);
//
//        System.out.print("Числа в диапазоне: ");
//        int i = start;
//        while (i <= end) {
//            System.out.print(i + " ");
//            i++;
//        }
//        System.out.println();
//
//        scanner.close();
//    }
//}
