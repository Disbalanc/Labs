public class Main {

    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Проверка переполнения ПЕРЕД умножением
            // Integer.MAX_VALUE = 2147483647
            // Integer.MIN_VALUE = -2147483648
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 ||
                    (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }

        return result;
    }

    public static void main(String[] args) {
        Main sol = new Main();

        System.out.println(sol.reverse(123));         // 321
        System.out.println(sol.reverse(-123));        // -321
        System.out.println(sol.reverse(120));         // 21
        System.out.println(sol.reverse(0));           // 0
        System.out.println(sol.reverse(1534236469));  // 0 (переполнение)
    }
}