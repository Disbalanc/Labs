import java.io.*;
import java.util.*;

//2060. Подпалиндромные пары
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Ваша задача — вычислить количество троек (i, j, k) таких, что i ≤ j < k и s[i..j] — палиндром и s[j+1 .. k] — палиндром.
//        Исходные данные
//Вход содержит строку из n строчных латинских букв (1 ≤ n ≤ 3 · 105).
//Результат
//Выведите количество требуемых троек.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        char[] ch = s.toCharArray();

        int[] d1 = new int[n];
        int[] d2 = new int[n];

        // Manacher for odd-length palindromes
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && ch[i - k] == ch[i + k]) k++;
            d1[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        // Manacher for even-length palindromes
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d2[l + r - i + 1], r - i + 1);
            while (i - k - 1 >= 0 && i + k < n && ch[i - k - 1] == ch[i + k]) k++;
            d2[i] = k--;
            if (i + k > r) {
                l = i - k - 1;
                r = i + k;
            }
        }

        int[] diffEnd = new int[n + 2];
        int[] diffStart = new int[n + 2];

        // Process odd palindromes
        for (int i = 0; i < n; i++) {
            if (d1[i] > 0) {
                int L_end = i;
                int R_end = i + d1[i] - 1;
                diffEnd[L_end]++;
                diffEnd[R_end + 1]--;

                int L_start = i - d1[i] + 1;
                int R_start = i;
                diffStart[L_start]++;
                diffStart[R_start + 1]--;
            }
        }

        // Process even palindromes
        for (int i = 0; i < n; i++) {
            if (d2[i] > 0) {
                int L_end = i;
                int R_end = i + d2[i] - 1;
                diffEnd[L_end]++;
                diffEnd[R_end + 1]--;

                int L_start = i - d2[i];
                int R_start = i - 1;
                diffStart[L_start]++;
                diffStart[R_start + 1]--;
            }
        }

        int[] endPal = new int[n];
        int[] startPal = new int[n];
        endPal[0] = diffEnd[0];
        for (int i = 1; i < n; i++) {
            endPal[i] = endPal[i - 1] + diffEnd[i];
        }
        startPal[0] = diffStart[0];
        for (int i = 1; i < n; i++) {
            startPal[i] = startPal[i - 1] + diffStart[i];
        }

        long ans = 0;
        for (int j = 0; j < n - 1; j++) {
            ans += (long) endPal[j] * startPal[j + 1];
        }
        System.out.println(ans);
    }
}