import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();

        // Предвычисляем биномиальные коэффициенты C[n][k] для n <= 100, k <= 9
        long[][] C = new long[100][10];
        for (int n = 0; n < 100; n++) {
            C[n][0] = 1;
            if (n < 10) C[n][n] = 1;
            for (int k = 1; k <= Math.min(n, 9); k++) {
                C[n][k] = C[n-1][k-1] + C[n-1][k];
            }
        }

        long result = 0;
        int maxJ = Math.min(9, S / 10);
        for (int j = 0; j <= maxJ; j++) {
            long sign = (j % 2 == 0) ? 1 : -1;
            int n = S - 10 * j + 8;
            if (n < 8) continue; // C(n,8)=0
            result += sign * C[9][j] * C[n][8];
        }

        if (S == 1) {
            result++; // число 1 000 000 000
        }

        System.out.println(result);
    }
}