public class Main {

    /**
     * Метод для проверки, является ли число палиндромом.
     * Реализовано без перевода в строку.
     */
    public static boolean isPalindrome(int x) {
        // Случай 1: Число отрицательное — не палиндром.
        // Случай 2: Число оканчивается на 0, но само не является 0 — не палиндром.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;

        // Разворачиваем вторую половину числа
        while (x > revertedNumber) {
            int lastDigit = x % 10;
            revertedNumber = revertedNumber * 10 + lastDigit;
            x /= 10;
        }

        // Если количество цифр было четным, то x == revertedNumber
        // Если нечетным, то мы убираем среднюю цифру: x == revertedNumber / 10
        // Например, для 121: в конце цикла x = 1, revertedNumber = 12.
        // 1 == 12/10 (то есть 1 == 1) -> true.
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестирование задачи Palindrome Number ===");

        int[] testCases = {121, -121, 10, 0, 12321, 1221};

        for (int x : testCases) {
            boolean result = isPalindrome(x);
            System.out.println("Число: " + x + " | Палиндром: " + result);
        }

        // Дополнительная проверка больших чисел
        int largePalindrome = 1234565432;
        System.out.println("Число: " + largePalindrome + " | Палиндром: " + isPalindrome(largePalindrome));
    }
}