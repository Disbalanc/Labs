public class Main {

    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0;
        int n = s.length();

        // Шаг 1: Пропускаем ведущие пробелы
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n) return 0;

        // Шаг 2: Определяем знак
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        // Шаг 3: Считываем цифры
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Проверка переполнения
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }

    public static void main(String[] args) {
        Main sol = new Main();

        System.out.println(sol.myAtoi("42"));           // 42
        System.out.println(sol.myAtoi("   -042"));      // -42
        System.out.println(sol.myAtoi("1337c0d3"));     // 1337
        System.out.println(sol.myAtoi("0-1"));          // 0
        System.out.println(sol.myAtoi("words and 987"));// 0
        System.out.println(sol.myAtoi("2147483648"));   // 2147483647 (MAX_VALUE)
        System.out.println(sol.myAtoi("-2147483649"));  // -2147483648 (MIN_VALUE)
        System.out.println(sol.myAtoi("  +  413"));     // 0
    }
}