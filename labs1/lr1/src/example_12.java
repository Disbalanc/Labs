import java.util.Calendar;
import java.util.Scanner;

public class example_12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите возраст: ");
        int age = in.nextInt();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = currentYear - age;
        System.out.println("Ваш год рождения: " + birthYear);
        in.close();
    }
}
