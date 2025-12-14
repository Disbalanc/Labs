import java.io.*;
import java.util.*;

//1471. Расстояние в дереве
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Дано взвешенное дерево. Найти кратчайшее расстояние между заданными вершинами.
//Исходные данные
//Первая строка содержит целое число n — количество вершин в дереве (1 ≤ n ≤ 50000). Вершины нумеруются целыми числами от 0 до n – 1. В следующих n – 1 строках содержится по три целых числа u, v, w, которые соответствуют ребру весом w (0 ≤ w ≤ 1000), соединяющему вершины u и v. В следующей строке содержится целое число m — количество запросов (1 ≤ m ≤ 75000). В следующих m строках содержится по два числа — номера вершин, расстояние между которыми необходимо вычислить.
//Результат
//Для каждого запроса выведите на отдельной строке одно число — искомое расстояние.

public class Main {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge>[] graph;
    static int[][] parent;
    static int[] depth;
    static long[] distFromRoot;
    static int LOG;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        // Вычисляем максимальную степень двойки (log2(n) + 1)
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;

        // Инициализация структур данных
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        parent = new int[n][LOG];
        depth = new int[n];
        distFromRoot = new long[n];

        // Чтение рёбер дерева
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // Предобработка (DFS для заполнения массивов)
        dfs(0, -1, 0, 0);

        // Предвычисление двоичных подъёмов
        preprocess(n);

        // Обработка запросов
        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            writer.println(getDistance(u, v));
        }

        writer.flush();
    }

    // DFS для вычисления глубины, расстояния от корня и непосредственных родителей
    static void dfs(int node, int par, int dep, long dist) {
        depth[node] = dep;
        distFromRoot[node] = dist;
        parent[node][0] = par;

        for (Edge edge : graph[node]) {
            if (edge.to != par) {
                dfs(edge.to, node, dep + 1, dist + edge.weight);
            }
        }
    }

    // Предвычисление двоичных подъёмов
    static void preprocess(int n) {
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                if (parent[i][j-1] != -1) {
                    parent[i][j] = parent[parent[i][j-1]][j-1];
                } else {
                    parent[i][j] = -1;
                }
            }
        }
    }

    // Нахождение наименьшего общего предка (LCA)
    static int lca(int u, int v) {
        // Поднимаем более глубокую вершину
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Поднимаем u до уровня v
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0) {
                u = parent[u][i];
            }
        }

        // Если после подъёма u и v совпали, то v был предком u
        if (u == v) return u;

        // Поднимаем обе вершины одновременно
        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        // Родитель текущих u и v - это LCA
        return parent[u][0];
    }

    // Вычисление расстояния между двумя вершинами
    static long getDistance(int u, int v) {
        int lca = lca(u, v);
        return distFromRoot[u] + distFromRoot[v] - 2 * distFromRoot[lca];
    }
}