import java.util.Scanner;

//1150. Номера страниц
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Петя Васечкин решил пронумеровать страницы в своей тетради числами от 1 до N. Определите количество нулей, единиц, …, девяток, которые ему потребуются.
//        Исходные данные
//Целое число N (1 ≤ N < 109).
//Результат
//Выведите 10 строк, первая из которых содержит необходимое количество нулей, вторая — единиц, …, десятая — девяток.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int d = 0; d < 10; d++) {
            long count = 0;
            long factor = 1;
            while (factor <= n) {
                long left = n / (factor * 10);
                long curr = (n / factor) % 10;
                long right = n % factor;

                if (d != 0) {
                    count += left * factor;
                } else {
                    if (left > 0) {
                        count += (left - 1) * factor;
                    }
                }

                if (curr > d) {
                    if (d != 0 || left > 0) {
                        count += factor;
                    }
                } else if (curr == d) {
                    count += right + 1;
                }

                factor *= 10;
            }
            System.out.println(count);
        }
    }
}