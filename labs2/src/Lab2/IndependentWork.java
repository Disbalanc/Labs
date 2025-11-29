package Lab2;

import java.util.Scanner;

public class IndependentWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Задание 1: Проверка деления на 3
        System.out.print("Введите число для проверки деления на 3: ");
        int num1 = scanner.nextInt();
        System.out.println("Число " + num1 + (num1 % 3 == 0 ? " делится" : " не делится") + " на 3\n");
        
        // Задание 2: Проверка остатков при делении
        System.out.print("Введите число для проверки критериев (остаток 2 при /5 и 1 при /7): ");
        int num2 = scanner.nextInt();
        boolean condition2 = (num2 % 5 == 2) && (num2 % 7 == 1);
        System.out.println("Число " + num2 + (condition2 ? " удовлетворяет" : " не удовлетворяет") + " критериям\n");
        
        // Задание 3: Проверка деления на 4 и не меньше 10
        System.out.print("Введите число для проверки деления на 4 и не меньше 10: ");
        int num3 = scanner.nextInt();
        boolean condition3 = (num3 % 4 == 0) && (num3 >= 10);
        System.out.println("Число " + num3 + (condition3 ? " удовлетворяет" : " не удовлетворяет") + " критериям\n");
        
        // Задание 4: Проверка диапазона [5, 10]
        System.out.print("Введите число для проверки диапазона [5, 10]: ");
        int num4 = scanner.nextInt();
        boolean condition4 = (num4 >= 5) && (num4 <= 10);
        System.out.println("Число " + num4 + (condition4 ? " попадает" : " не попадает") + " в диапазон\n");
        
        // Задание 5: Определение тысяч в числе
        System.out.print("Введите число для определения тысяч: ");
        int num5 = scanner.nextInt();
        int thousands = (num5 / 1000) % 10;
        System.out.println("В числе " + num5 + " " + thousands + " тысяч\n");
        
        scanner.close();
    }
}