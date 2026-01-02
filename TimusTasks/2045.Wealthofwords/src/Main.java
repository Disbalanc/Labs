import java.io.PrintWriter;
import java.util.Scanner;

//2045. Богатство слов
//Ограничение времени: 0.5 секунды
//Ограничение памяти: 64 МБ
//Для каждого целого положительного числа i от 1 до n выдайте строку длины n из строчных латинских букв, содержащую ровно i различных подстрок-палиндромов. Две подстроки считаются различными, если они различаются как строки.
//Исходные данные
//На вход подаётся целое число n (1 ≤ n ≤ 2000).
//Результат
//На выходе ожидается n строк. Каждая строка должна иметь вид: «i : si», где i — номер строки. Если для некоторого i не существует такой строки, вместо si следует выводить «NO».

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.print(i + " : ");
            if (i == 1) {
                if (n == 1) out.println("a");
                else out.println("NO");
            } else if (i == 2) {
                if (n == 2) out.println("ab");
                else out.println("NO");
            } else {
                int k = i - 2;
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < k; j++) {
                    s.append((char)('a' + (j % 26)));
                }
                s.append("bc");
                while (s.length() < n) {
                    s.append(s.charAt(0));
                }
                out.println(s.toString());
            }
        }
        out.flush();
    }
}