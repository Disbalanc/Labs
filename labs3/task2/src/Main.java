import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название дня недели: ");
        String dayName = scanner.next().toLowerCase();

        switch (dayName) {
            case "понедельник" -> System.out.println("Порядковый номер: 1");
            case "вторник" -> System.out.println("Порядковый номер: 2");
            case "среда" -> System.out.println("Порядковый номер: 3");
            case "четверг" -> System.out.println("Порядковый номер: 4");
            case "пятница" -> System.out.println("Порядковый номер: 5");
            case "суббота" -> System.out.println("Порядковый номер: 6");
            case "воскресенье" -> System.out.println("Порядковый номер: 7");
            default -> System.out.println("Такого дня нет");
        }

        scanner.close();
    }
}