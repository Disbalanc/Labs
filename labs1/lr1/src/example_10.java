import java.util.Calendar;
import java.util.Scanner;

public class example_10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите год рождения: ");
        int birthYear = in.nextInt();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - birthYear;
        System.out.println("Ваш возраст: " + age);
        in.close();
    }
}
