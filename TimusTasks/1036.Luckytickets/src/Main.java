import java.math.BigInteger;
import java.util.Scanner;

//1036. Счастливые билеты
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//Необходимо посчитать количество «счастливых» билетов с заданной суммой цифр, среди тех, номер которых состоит из 2N разрядов. «Счастливым» является билет, у которого сумма первых N цифр равна сумме N последних цифр.
//Исходные данные
//В единственной строке через пробел даны два числа: первое — N (1 ≤ N ≤ 50); второе — сумма цифр интересующих нас билетов (неотрицательное число, не превосходящее 1000).
//Результат
//В качестве ответа необходимо вывести найденное число «счастливых» билетов.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();

        if (S % 2 != 0) {
            System.out.println(0);
            return;
        }

        int target = S / 2;
        int maxSum = 9 * N;
        if (target > maxSum) {
            System.out.println(0);
            return;
        }

        // dp[n][s] будем хранить только предыдущую и текущую строку
        BigInteger[] prev = new BigInteger[target + 1];
        BigInteger[] curr = new BigInteger[target + 1];

        // Инициализация для n = 0
        for (int s = 0; s <= target; s++) {
            prev[s] = BigInteger.ZERO;
        }
        prev[0] = BigInteger.ONE; // одна пустая последовательность с суммой 0

        // Заполнение DP для n от 1 до N
        for (int n = 1; n <= N; n++) {
            for (int s = 0; s <= target; s++) {
                curr[s] = BigInteger.ZERO;
                // Перебираем последнюю цифру d от 0 до 9
                for (int d = 0; d <= 9; d++) {
                    if (s - d >= 0) {
                        curr[s] = curr[s].add(prev[s - d]);
                    }
                }
            }
            // Копируем curr в prev для следующей итерации
            BigInteger[] temp = prev;
            prev = curr;
            curr = temp;
        }

        BigInteger count = prev[target]; // D(N, target)
        BigInteger result = count.multiply(count); // квадрат
        System.out.println(result);
    }
}