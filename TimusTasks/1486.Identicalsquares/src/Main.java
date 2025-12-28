import java.util.*;
import java.io.*;

//1486. Одинаковые квадраты
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 128 МБ
//На разборе задач одного из контестов петрозаводских сборов Вова и Саша поспорили, кто из них сможет найти за 300 минут в матрице размера N × M, состоящей из строчных латинских букв, пару одинаковых квадратов наибольшего размера. Квадраты могут накладываться друг на друга, но не могут совпадать. Кто нашёл пару большего размера, тот и выиграл. Мимо проходил Петя, посмотрел на матрицу, сказал, что оптимальная пара квадратов имеет сторону K, и пошёл дальше. Вова и Саша до сих пор пытаются найти этот ответ. Может быть, вы скажете, какую пару квадратов имел в виду Петя?
//Исходные данные
//В первой строке через пробел даны два целых числа N и M. 1 ≤ N, M ≤ 500. В следующих N строках по M символов в каждой строке приведена матрица из строчных латинских букв.
//Результат
//В первой строке выведите целое число K, которое сказал Петя. В следующих двух строках выведите координаты левой верхней клетки каждого из квадратов. Если существует более одной пары одинаковых квадратов наибольшего размера, то можно вывести любую из них. Вы можете считать, что левая верхняя клетка матрицы имеет координаты (1, 1), правая нижняя — координаты (N, M). Если Петя сказал, что в матрице не существует пары одинаковых квадратов, выведите единственное число 0.

public class Main {
    static class Pair {
        int i1, j1, i2, j2;
        Pair(int i1, int j1, int i2, int j2) {
            this.i1 = i1;
            this.j1 = j1;
            this.i2 = i2;
            this.j2 = j2;
        }
    }

    static final int MOD1 = 1_000_000_007;
    static final int MOD2 = 1_000_000_009;
    static final int BASE1 = 31;
    static final int BASE2 = 37;

    static int n, m;
    static char[][] a;
    static long[][] h1, h2; // префиксные хэши строк для двух модулей
    static long[] pow1_1, pow1_2; // степени BASE1 по модулям MOD1 и MOD2
    static long[] pow2_1, pow2_2; // степени BASE2 по модулям MOD1 и MOD2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        a = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        // Предпосчет степеней BASE1 и BASE2
        int maxDim = Math.max(n, m) + 1;
        pow1_1 = new long[maxDim];
        pow1_2 = new long[maxDim];
        pow2_1 = new long[maxDim];
        pow2_2 = new long[maxDim];
        pow1_1[0] = pow1_2[0] = pow2_1[0] = pow2_2[0] = 1;
        for (int i = 1; i < maxDim; i++) {
            pow1_1[i] = (pow1_1[i-1] * BASE1) % MOD1;
            pow1_2[i] = (pow1_2[i-1] * BASE1) % MOD2;
            pow2_1[i] = (pow2_1[i-1] * BASE2) % MOD1;
            pow2_2[i] = (pow2_2[i-1] * BASE2) % MOD2;
        }

        // Предпосчет префиксных хэшей для каждой строки
        h1 = new long[n][m+1];
        h2 = new long[n][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = a[i][j] - 'a' + 1;
                h1[i][j+1] = (h1[i][j] * BASE1 + val) % MOD1;
                h2[i][j+1] = (h2[i][j] * BASE1 + val) % MOD2;
            }
        }

        // Бинарный поиск по K
        int left = 1, right = Math.min(n, m);
        int ans = 0;
        Pair bestPair = null;

        while (left <= right) {
            int mid = (left + right) / 2;
            Pair res = check(mid);
            if (res != null) {
                ans = mid;
                bestPair = res;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (ans == 0) {
            System.out.println(0);
        } else {
            System.out.println(ans);
            // Координаты выводятся в 1-индексации
            System.out.println((bestPair.i1 + 1) + " " + (bestPair.j1 + 1));
            System.out.println((bestPair.i2 + 1) + " " + (bestPair.j2 + 1));
        }
    }

    // Проверяем, существует ли два одинаковых квадрата размера K
    // Возвращает null, если нет, иначе пару координат
    static Pair check(int K) {
        if (K == 0) return null;

        int rows = n - K + 1;
        int cols = m - K + 1;
        if (rows <= 0 || cols <= 0) return null;

        // pref1 и pref2 - префиксные хэши по вертикали для каждого столбца j
        // размеры: (n+1) x cols
        long[][] pref1 = new long[n+1][cols];
        long[][] pref2 = new long[n+1][cols];

        // Заполняем pref для каждого столбца j
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < n; i++) {
                // Хэш горизонтального отрезка в строке i от j до j+K-1
                long hash1 = (h1[i][j+K] - h1[i][j] * pow1_1[K]) % MOD1;
                if (hash1 < 0) hash1 += MOD1;
                long hash2 = (h2[i][j+K] - h2[i][j] * pow1_2[K]) % MOD2;
                if (hash2 < 0) hash2 += MOD2;

                pref1[i+1][j] = (pref1[i][j] * BASE2 + hash1) % MOD1;
                pref2[i+1][j] = (pref2[i][j] * BASE2 + hash2) % MOD2;
            }
        }

        Map<Long, Integer> map = new HashMap<>();

        // Для каждого квадрата KxK
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Хэш квадрата с верхним левым углом (i,j)
                long hash1 = (pref1[i+K][j] - pref1[i][j] * pow2_1[K]) % MOD1;
                if (hash1 < 0) hash1 += MOD1;
                long hash2 = (pref2[i+K][j] - pref2[i][j] * pow2_2[K]) % MOD2;
                if (hash2 < 0) hash2 += MOD2;

                long key = hash1 * MOD2 + hash2;

                if (map.containsKey(key)) {
                    int pos = map.get(key);
                    int i2 = pos / m;
                    int j2 = pos % m;

                    // Квадраты должны быть разными
                    if (i != i2 || j != j2) {
                        // Проверяем, действительно ли квадраты одинаковые (на случай коллизии)
                        if (squaresEqual(i, j, i2, j2, K)) {
                            return new Pair(i, j, i2, j2);
                        }
                    }
                } else {
                    map.put(key, i * m + j);
                }
            }
        }

        return null;
    }

    // Проверка, что два квадрата размера K с координатами (i1,j1) и (i2,j2) действительно одинаковые
    static boolean squaresEqual(int i1, int j1, int i2, int j2, int K) {
        for (int di = 0; di < K; di++) {
            for (int dj = 0; dj < K; dj++) {
                if (a[i1+di][j1+dj] != a[i2+di][j2+dj]) {
                    return false;
                }
            }
        }
        return true;
    }
}