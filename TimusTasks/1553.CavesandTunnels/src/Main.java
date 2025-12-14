import java.io.*;
import java.util.*;

//1553. Caves and Tunnels
//Ограничение времени: 3.0 секунды
//Ограничение памяти: 64 МБ
//After landing on Mars surface, scientists found a strange system of caves connected by tunnels. So they began to research it using remote controlled robots. It was found out that there exists exactly one route between every pair of caves. But then scientists faced a particular problem. Sometimes in the caves faint explosions happen. They cause emission of radioactive isotopes and increase radiation level in the cave. Unfortunately robots don't stand radiation well. But for the research purposes they must travel from one cave to another. So scientists placed sensors in every cave to monitor radiation level in the caves. And now every time they move robots they want to know the maximal radiation level the robot will have to face during its relocation. So they asked you to write a program that will solve their problem.
//Исходные данные
//The first line of the input contains one integer N (1 ≤ N ≤ 100000) — the number of caves. Next N − 1 lines describe tunnels. Each of these lines contains a pair of integers ai, bi (1 ≤ ai, bi ≤ N) specifying the numbers of the caves connected by corresponding tunnel. The next line has an integer Q (Q ≤ 100000) representing the number of queries. The Q queries follow on a single line each. Every query has a form of "C U V", where C is a single character and can be either 'I' or 'G' representing the type of the query (quotes for clarity only). In the case of an 'I' query radiation level in U-th cave (1 ≤ U ≤ N) is incremented by V (0 ≤ V ≤ 10000). In the case of a 'G' query your program must output the maximal level of radiation on the way between caves with numbers U and V (1 ≤ U, V ≤ N) after all increases of radiation ('I' queries) specified before current query. It is assumed that initially radiation level is 0 in all caves, and it never decreases with time (because isotopes' half-life time is much larger than the time of observations).
//        Результат
//        For every 'G' query output one line containing the maximal radiation level by itself.

public class Main {
    static int n;
    static List<Integer>[] adj;
    static int[] parent, depth, size, heavy, head, pos;
    static int[] val;
    static int curPos;
    static SegmentTree segTree;

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int left, int right, int p, int v) {
            if (left == right) {
                tree[idx] = v;
                return;
            }
            int mid = (left + right) / 2;
            if (p <= mid) update(idx * 2, left, mid, p, v);
            else update(idx * 2 + 1, mid + 1, right, p, v);
            tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
        }

        int query(int idx, int left, int right, int l, int r) {
            if (l <= left && right <= r) return tree[idx];
            int mid = (left + right) / 2;
            int res = 0;
            if (l <= mid) res = Math.max(res, query(idx * 2, left, mid, l, r));
            if (r > mid) res = Math.max(res, query(idx * 2 + 1, mid + 1, right, l, r));
            return res;
        }
    }

    static void dfsSize(int v, int p) {
        parent[v] = p;
        size[v] = 1;
        int maxSize = 0;
        for (int u : adj[v]) {
            if (u == p) continue;
            depth[u] = depth[v] + 1;
            dfsSize(u, v);
            size[v] += size[u];
            if (size[u] > maxSize) {
                maxSize = size[u];
                heavy[v] = u;
            }
        }
    }

    static void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        if (heavy[v] != 0) {
            decompose(heavy[v], h);
        }
        for (int u : adj[v]) {
            if (u != parent[v] && u != heavy[v]) {
                decompose(u, u);
            }
        }
    }

    static int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int t = u; u = v; v = t;
            }
            res = Math.max(res, segTree.query(1, 0, n - 1, pos[head[u]], pos[u]));
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int t = u; u = v; v = t;
        }
        res = Math.max(res, segTree.query(1, 0, n - 1, pos[u], pos[v]));
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        n = Integer.parseInt(br.readLine());
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        parent = new int[n + 1];
        depth = new int[n + 1];
        size = new int[n + 1];
        heavy = new int[n + 1];
        head = new int[n + 1];
        pos = new int[n + 1];
        val = new int[n + 1];

        dfsSize(1, 0);
        curPos = 0;
        decompose(1, 1);

        segTree = new SegmentTree(n);

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (type == 'I') {
                val[u] += v;
                segTree.update(1, 0, n - 1, pos[u], val[u]);
            } else { // type == 'G'
                pw.println(queryPath(u, v));
            }
        }
        pw.flush();
    }
}