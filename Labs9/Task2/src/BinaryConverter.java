import java.util.Scanner;

public class BinaryConverter {

    public static String toBinary(int n) {
        if (n == 0) return "0";
        if (n < 0) return "-" + toBinaryRec(Math.abs(n));
        return toBinaryRec(n);
    }

    private static String toBinaryRec(int n) {
        if (n == 0) return "";
        return toBinaryRec(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        int number = sc.nextInt();

        System.out.println("Десятичное:  " + number);
        System.out.println("Двоичное:    " + toBinary(number));
        System.out.println("Проверка:    " + Integer.toBinaryString(Math.abs(number)));
        sc.close();
    }
}