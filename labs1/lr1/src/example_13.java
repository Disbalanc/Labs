import java.util.Scanner;

public class example_13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите первое число: ");
        double num1 = in.nextDouble();
        System.out.println("Введите второе число: ");
        double num2 = in.nextDouble();
        double sum = num1 + num2;
        System.out.println("Сумма: " + sum);
        in.close();
    }
}
