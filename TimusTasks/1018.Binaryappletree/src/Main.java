import java.util.*;
import java.io.*;

//1018. Двоичная яблоня
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Представьте, как должна выглядеть яблоня в двоичном компьютерном мире. Вы правы, она выглядит как двоичное дерево, то есть любая ветка двоичной яблони может ветвиться в точности на две новые ветки. Занумеруем целыми числами корень двоичной яблони, точки ветвления и верхушки верхних ветвей. Пусть корень дерева всегда имеет номер 1, а все числа, используемые при нумерации, лежат в пределах от 1 до N, где N — общее число пронумерованных точек. На рисунке ниже приведён пример нумерации дерева с четырьмя ветвями. N здесь равно 5.
//        2   5
//        \ /
//        3   4
//        \ /
//        1
//Как вы понимаете, не очень удобно собирать яблоки с яблони, у которой много ветвей. Вот почему некоторые ветви следует удалить из дерева. Но вы также заинтересованы в удалении ветвей, которое приведёт к минимальной потере яблок. Известно количество яблок на каждой из ветвей, а также количество ветвей, которые нужно сохранить. Ваша задача — определить, сколько яблок можно оставить на яблоне, удалив лишние ветви.
//Исходные данные
//В первой строке даны числа N и Q (2 ≤ N ≤ 100; 1 ≤ Q ≤ N − 1). N обозначает количество пронумерованных точек в дереве, а Q — количество ветвей, которые нужно сохранить. Следующие N − 1 строк содержат описание ветвей. Описание каждой ветви состоит из трёх чисел, разделённых пробелом. Первые два из них определяют конечные точки ветви, третье — число яблок на данной ветви. Вы можете считать, что любая ветвь содержит не более 30000 яблок.
//        Результат
//Выведите единственное число — максимальное количество яблок, которое можно сохранить. Не забудьте сохранить корень яблони ;-)

public class Main {
    static class Node {
        int left = 0, right = 0;
        int wLeft = 0, wRight = 0;
    }

    static Node[] tree;
    static List<int[]>[] adj;
    static int[][] dp;
    static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        tree = new Node[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new Node();
        buildTree(1, 0);

        dp = new int[N + 1][Q + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(dp[i], -1);

        dfs(1);
        System.out.println(dp[1][Q]);
    }

    static void buildTree(int u, int parent) {
        int childCount = 0;
        for (int[] edge : adj[u]) {
            int v = edge[0], w = edge[1];
            if (v == parent) continue;
            if (childCount == 0) {
                tree[u].left = v;
                tree[u].wLeft = w;
            } else if (childCount == 1) {
                tree[u].right = v;
                tree[u].wRight = w;
            }
            childCount++;
            buildTree(v, u);
        }
    }

    static int dfs(int u) {
        int left = tree[u].left, right = tree[u].right;
        int wL = tree[u].wLeft, wR = tree[u].wRight;

        // Если лист
        if (left == 0 && right == 0) {
            dp[u][0] = 0;
            return 0;
        }

        // Рекурсивно обрабатываем детей
        int leftSize = 0, rightSize = 0;
        if (left != 0) {
            leftSize = dfs(left) + 1; // +1 для ребра (u, left)
        }
        if (right != 0) {
            rightSize = dfs(right) + 1; // +1 для ребра (u, right)
        }

        dp[u][0] = 0;
        int maxSize = Math.min(leftSize + rightSize, Q);

        // Перебираем количество ветвей в поддереве u
        for (int k = 1; k <= maxSize; k++) {
            // Перебираем, сколько взять из левого поддерева
            int maxLeft = Math.min(leftSize, k);
            for (int i = 0; i <= maxLeft; i++) {
                int j = k - i;
                if (j < 0 || j > rightSize) continue;

                int val = 0;
                // Вклад левого поддерева
                if (i > 0 && left != 0) {
                    if (dp[left][i - 1] >= 0) {
                        val += dp[left][i - 1] + wL;
                    } else {
                        continue;
                    }
                } else if (i > 0) {
                    continue;
                }

                // Вклад правого поддерева
                if (j > 0 && right != 0) {
                    if (dp[right][j - 1] >= 0) {
                        val += dp[right][j - 1] + wR;
                    } else {
                        continue;
                    }
                } else if (j > 0) {
                    continue;
                }

                dp[u][k] = Math.max(dp[u][k], val);
            }
        }

        return maxSize;
    }
}