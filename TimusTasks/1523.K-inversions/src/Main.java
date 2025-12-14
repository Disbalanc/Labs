import java.io.*;
import java.util.*;

//1523. K-инверсии
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Рассмотрим перестановку a1, a2, …, an (все ai — различные целые числа от 1 до n). Назовем k-инверсией последовательность индексов i1, i2, …, ik такую, что 1 ≤ i1 < i2 < … < ik ≤ n и ai1 > ai2 > … > aik. Вам нужно посчитать количество различных k-инверсий в заданной перестановке.
//Исходные данные
//В первой строке записаны целые числа n и k (1 ≤ n ≤ 20000; 2 ≤ k ≤ 10). Во второй строке записаны целые числа a1, …, an.
//        Результат
//Выведите количество различных k-инверсий в заданной перестановке, вычисленное по модулю 109.

public class Main {
    private static final int MOD = 1_000_000_000;

    static class Fenwick {
        int n;
        int[] tree;
        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }
        void add(int idx, int val) {
            while (idx <= n) {
                tree[idx] = (tree[idx] + val) % MOD;
                idx += idx & -idx;
            }
        }
        int sum(int idx) {
            long s = 0;
            while (idx > 0) {
                s += tree[idx];
                idx -= idx & -idx;
            }
            return (int) (s % MOD);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // dp для длины 1
        int[] dpPrev = new int[n + 1];
        Arrays.fill(dpPrev, 1);

        for (int len = 2; len <= k; len++) {
            int[] dpCur = new int[n + 1];
            Fenwick ft = new Fenwick(n);
            for (int i = 1; i <= n; i++) {
                int total = ft.sum(n);
                int lessEq = ft.sum(a[i]);
                int greater = (total - lessEq) % MOD;
                if (greater < 0) greater += MOD;
                dpCur[i] = greater;
                ft.add(a[i], dpPrev[i]);
            }
            dpPrev = dpCur;
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dpPrev[i];
            if (ans >= MOD) ans %= MOD;
        }
        System.out.println(ans % MOD);
    }
}