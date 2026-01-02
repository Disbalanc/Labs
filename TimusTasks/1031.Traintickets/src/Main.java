import java.util.*;
import java.io.*;

//1031. Железнодорожные билеты
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Построена железнодорожная линия “Екатеринбург-Свердловск” с несколькими станциями. Эта железная дорога может быть представлена в виде отрезка, на котором расположены железнодорожные станции. Железнодорожная линия начинается на станции Екатеринбург и заканчивается на станции Свердловск, поэтому станции пронумерованы от Екатеринбурга (у нее номер 1), а Свердловск – последняя станция.
//Problem illustration
//Стоимость билета между любыми двумя станциями зависит только от расстояния между ними. Цены на билеты указаны в следующей таблице.
//distance X between stations	стоимость билета
//0 < X ≤ L1	C1
//L1 < X ≤ L2	C2
//L2 < X ≤ L3	C3
//Прямые билеты с одной станции на другую можно купить, если и только если расстояние между этими станциями не превышает L3. Поэтому иногда приходится покупать несколько билетов на разные участки пути, чтобы добраться от одной станции до другой.
//Например, на железнодорожной линии, показанной на рисунке выше, имеется семь станций. Прямой билет со второй станции на шестую купить нельзя. Существует несколько способов оплаты проезда между этими станциями. Один из них заключается в покупке двух билетов: одного по цене C2 для проезда между второй и третьей станциями, а другого по цене C3 для проезда между третьей и шестой станциями. Обратите внимание, что хотя расстояние между второй и шестой станциями равно 2L2, для проезда не хватит двух билетов по цене C2, так как каждый билет действителен только на одну поездку и каждая поездка должна обязательно начинаться и заканчиваться на станциях.
//Ваша задача – написать программу, которая найдет минимальную стоимость проезда между двумя заданными станциями.
//Исходные данные
//Первая строка содержит целые числа L1, L2, L3, C1, C2 и C3 (1 ≤ L1 < L2 < L3 ≤ 109; 1 ≤ C1 < C2 < C3 ≤ 109). Вторая строка содержит целое число N – количество станций (2 ≤ N ≤ 10000). Третья строка содержит два различных целых числа. Они представляют собой порядковые номера станций, проезд между которыми должен быть оплачен. В следующих N − 1 строке содержатся расстояния от первой станции (Екатеринбург) на железнодорожной линии до других. Эти расстояния задаются в виде различных положительных целых чисел и следуют в порядке возрастания. Расстояние от Екатеринбурга до Свердловска не превышает 109. Расстояние между любыми соседними станциями не превышает L3. Минимальная стоимость проезда между двумя заданными станциями не превышает 109.
//Результат
//Выведите минимальную стоимость проезда между двумя заданными станциями.

public class Main {
    static final long INF = Long.MAX_VALUE / 2;

    static class SegmentTree {
        long[] tree;
        int n;

        SegmentTree(int size) {
            n = size;
            tree = new long[4 * n];
            Arrays.fill(tree, INF);
        }

        void update(int idx, int left, int right, int pos, long value) {
            if (left == right) {
                tree[idx] = value;
                return;
            }
            int mid = (left + right) / 2;
            if (pos <= mid) {
                update(idx * 2, left, mid, pos, value);
            } else {
                update(idx * 2 + 1, mid + 1, right, pos, value);
            }
            tree[idx] = Math.min(tree[idx * 2], tree[idx * 2 + 1]);
        }

        long query(int idx, int left, int right, int l, int r) {
            if (l > r) return INF;
            if (l <= left && right <= r) return tree[idx];
            int mid = (left + right) / 2;
            long res = INF;
            if (l <= mid) res = Math.min(res, query(idx * 2, left, mid, l, r));
            if (r > mid) res = Math.min(res, query(idx * 2 + 1, mid + 1, right, l, r));
            return res;
        }

        void set(int pos, long value) {
            update(1, 0, n - 1, pos, value);
        }

        long getMin(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }
    }

    static int lowerBound(long[] arr, int from, int to, long target) {
        int low = from, high = to;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long L1 = Long.parseLong(st.nextToken());
        long L2 = Long.parseLong(st.nextToken());
        long L3 = Long.parseLong(st.nextToken());
        long C1 = Long.parseLong(st.nextToken());
        long C2 = Long.parseLong(st.nextToken());
        long C3 = Long.parseLong(st.nextToken());

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        if (A > B) {
            int temp = A;
            A = B;
            B = temp;
        }

        long[] stations = new long[N + 1];
        stations[1] = 0;
        for (int i = 2; i <= N; i++) {
            stations[i] = Long.parseLong(br.readLine());
        }

        long[] dp = new long[N + 1];
        Arrays.fill(dp, INF);
        dp[A] = 0;

        int offset = A;
        int size = B - A + 1;
        SegmentTree segTree = new SegmentTree(size);
        segTree.set(A - offset, 0);

        for (int i = A + 1; i <= B; i++) {
            long target1 = stations[i] - L1;
            long target2 = stations[i] - L2;
            long target3 = stations[i] - L3;

            int low1 = lowerBound(stations, 1, N + 1, target1);
            if (low1 < A) low1 = A;
            if (low1 >= i) low1 = i;

            int low2 = lowerBound(stations, 1, N + 1, target2);
            if (low2 < A) low2 = A;

            int low3 = lowerBound(stations, 1, N + 1, target3);
            if (low3 < A) low3 = A;

            long cand1 = INF, cand2 = INF, cand3 = INF;

            if (low1 <= i - 1) {
                int l = low1 - offset;
                int r = i - 1 - offset;
                long minVal = segTree.getMin(l, r);
                if (minVal < INF) {
                    cand1 = minVal + C1;
                }
            }

            if (low2 <= low1 - 1) {
                int l = low2 - offset;
                int r = low1 - 1 - offset;
                long minVal = segTree.getMin(l, r);
                if (minVal < INF) {
                    cand2 = minVal + C2;
                }
            }

            if (low3 <= low2 - 1) {
                int l = low3 - offset;
                int r = low2 - 1 - offset;
                long minVal = segTree.getMin(l, r);
                if (minVal < INF) {
                    cand3 = minVal + C3;
                }
            }

            dp[i] = Math.min(cand1, Math.min(cand2, cand3));
            segTree.set(i - offset, dp[i]);
        }

        System.out.println(dp[B]);
    }
}