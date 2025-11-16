import java.util.Scanner;

//1470. НЛО
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//В обязанности уфолога Васи входит наблюдать за всеми неопознанными летающими объектами (НЛО) в части пространства, ограниченной кубом размером N × N × N. Этот куб поделен на кубические сектора размером 1 × 1 × 1. Во время наблюдений Васи могут происходить следующие события:
//в каком-то секторе появляется несколько новых НЛО;
//в каком-то секторе исчезает несколько НЛО;
//также ему может позвонить начальник и спросить, сколько НЛО в данный момент находится в части пространства, состоящей из нескольких секторов.
//        В момент, когда Вася начал свои наблюдения, во всём пространстве не было ни одного НЛО.
//        Исходные данные
//Первая строка содержит целое число N (1 ≤ N ≤ 128). Координаты кубических секторов — целые числа от 0 до N–1.
//Далее следуют записи о происходивших событиях по одной записи в каждой строке. В начале строки записано число M.
//Если M равно 1, то за ним следуют 4 числа — x (0 ≤ x < N), y (0 ≤ y < N), z (0 ≤ z < N), K (–20000 ≤ K ≤ 20000) — координаты кубического сектора и величина, на которую в нем изменилось количество НЛО. После изменения количество НЛО ни в каком секторе не может стать отрицательным.
//Если M равно 2, то за ним следуют 6 чисел — x1, y1, z1, x2, y2, z2 (0 ≤ x1 ≤ x2 < N, 0 ≤ y1 ≤ y2 < N, 0 ≤ z1 ≤ z2 < N), которые означают, что Васе велено подсчитать количество НЛО в секторах (x, y, z) из области: x1 ≤ x ≤ x2, y1 ≤ y ≤ y2, z1 ≤ z ≤ z2.
//Если M равно 3, то это означает, что Вася устал наблюдать за НЛО и пошёл спать. Эта запись встречается только один раз и является последней.
//Количество записей не превышает 100002.
//Результат
//Для каждого вопроса выведите в отдельной строке одно число — искомое количество НЛО.

public class Main {
    private static long[][][] tree;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        tree = new long[n + 1][n + 1][n + 1];

        while (true) {
            int m = scanner.nextInt();
            if (m == 3) {
                break;
            } else if (m == 1) {
                int x = scanner.nextInt() + 1;
                int y = scanner.nextInt() + 1;
                int z = scanner.nextInt() + 1;
                long k = scanner.nextLong();
                update(x, y, z, k);
            } else if (m == 2) {
                int x1 = scanner.nextInt() + 1;
                int y1 = scanner.nextInt() + 1;
                int z1 = scanner.nextInt() + 1;
                int x2 = scanner.nextInt() + 1;
                int y2 = scanner.nextInt() + 1;
                int z2 = scanner.nextInt() + 1;
                long result = rangeQuery(x1, y1, z1, x2, y2, z2);
                System.out.println(result);
            }
        }
    }

    private static void update(int x, int y, int z, long delta) {
        for (int i = x; i <= n; i += i & -i) {
            for (int j = y; j <= n; j += j & -j) {
                for (int k = z; k <= n; k += k & -k) {
                    tree[i][j][k] += delta;
                }
            }
        }
    }

    private static long query(int x, int y, int z) {
        long sum = 0;
        for (int i = x; i > 0; i -= i & -i) {
            for (int j = y; j > 0; j -= j & -j) {
                for (int k = z; k > 0; k -= k & -k) {
                    sum += tree[i][j][k];
                }
            }
        }
        return sum;
    }

    private static long rangeQuery(int x1, int y1, int z1, int x2, int y2, int z2) {
        long total = query(x2, y2, z2);
        total -= query(x1 - 1, y2, z2);
        total -= query(x2, y1 - 1, z2);
        total -= query(x2, y2, z1 - 1);
        total += query(x1 - 1, y1 - 1, z2);
        total += query(x1 - 1, y2, z1 - 1);
        total += query(x2, y1 - 1, z1 - 1);
        total -= query(x1 - 1, y1 - 1, z1 - 1);
        return total;
    }
}