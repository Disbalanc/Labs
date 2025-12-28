import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();

        // Решение задачи
        String result = makePalindrome(s1);
        System.out.println(result);
    }

    private static String makePalindrome(String s1) {
        int n = s1.length();
        String rev = new StringBuilder(s1).reverse().toString();

        // Строим строку T = rev + "#" + s1
        String t = rev + "#" + s1;

        // Вычисляем префикс-функцию для строки T
        int[] pi = computePrefixFunction(t);

        // k - длина наибольшего префикса rev, который совпадает с суффиксом s1
        int k = pi[t.length() - 1];

        // Если k равно n (s1 уже палиндром), находим меньшее k по цепочке
        if (k == n) {
            k = pi[k - 1]; // Используем предыдущее значение
        }

        // S2 = rev без первых k символов
        String s2 = rev.substring(k);

        return s1 + s2;
    }

    private static int[] computePrefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }

        return pi;
    }
}