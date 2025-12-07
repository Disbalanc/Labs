import java.io.*;
import java.util.*;

//1003. Чётность
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//Вы играете со своим другом в следующую игру. Ваш друг записывает последовательность, состоящую из нулей и единиц. Вы выбираете непрерывную подпоследовательность (например, подпоследовательность от третьей до пятой цифры включительно) и спрашиваете его, чётное или нечётное количество единиц содержит эта подпоследовательность. Ваш друг отвечает, после чего вы можете спросить про другую подпоследовательность, и так далее.
//Ваша задача — угадать всю последовательность чисел. Но вы подозреваете, что некоторые из ответов вашего друга могут быть неверными, и хотите уличить его в обмане. Вы решили написать программу, которая получит наборы ваших вопросов вместе с ответами друга и найдет первый ответ, который гарантированно неверен. Это должен быть такой ответ, что существует последовательность, удовлетворяющая ответам на предыдущие вопросы, но никакая последовательность не удовлетворяет этому ответу.
//        Исходные данные
//Ввод содержит несколько тестов. Первая строка каждого теста содержит одно число, равное длине последовательности нулей и единиц. Эта длина не превосходит 109. Во второй строке находится одно неотрицательное целое число — количество заданных вопросов и ответов на них. Количество вопросов и ответов не превышает 5 000. Остальные строки содержат вопросы и ответы. Каждая строка содержит один вопрос и ответ на этот вопрос: два целых числа (позиции первой и последней цифр выбранной подпоследовательности) и одно слово — “even” или “odd” — ответ, сообщающий чётность количества единиц в выбранной подпоследовательности, где “even” означает чётное количество единиц, а “odd” означает нечётное количество. Ввод заканчивается строкой, содержащей −1.
//Результат
//Каждая строка вывода должна содержать одно целое число X. Число X показывает, что существует последовательность нулей и единиц, удовлетворяющая первым X условиям чётности, но не существует последовательности, удовлетворяющей X + 1 условию. Если существует последовательность нулей и единиц, удовлетворяющая всем заданным условиям, то число X должно быть равно количеству всех заданных вопросов.

public class Main {

    static class DSU {
        int[] parent;
        int[] rank;
        int[] parity; // 0 - same parity as root, 1 - different

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            parity = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                int root = find(parent[x]);
                parity[x] ^= parity[parent[x]];
                parent[x] = root;
            }
            return parent[x];
        }

        public boolean union(int x, int y, int p) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                // Check if parity condition holds
                return (parity[x] ^ parity[y]) == p;
            }

            // Union by rank
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                parity[rootX] = parity[x] ^ parity[y] ^ p;
            } else {
                parent[rootY] = rootX;
                parity[rootY] = parity[x] ^ parity[y] ^ p;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootX]++;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while (true) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            int n = Integer.parseInt(line.trim());
            if (n == -1) {
                break;
            }

            int m = Integer.parseInt(br.readLine().trim());

            List<Query> queries = new ArrayList<>();
            Set<Integer> indices = new HashSet<>();

            for (int i = 0; i < m; i++) {
                String[] parts = br.readLine().trim().split(" ");
                int l = Integer.parseInt(parts[0]);
                int r = Integer.parseInt(parts[1]);
                int p = parts[2].equals("even") ? 0 : 1;

                queries.add(new Query(l, r, p));
                indices.add(l - 1);
                indices.add(r);
            }

            // Coordinate compression
            List<Integer> sortedIndices = new ArrayList<>(indices);
            Collections.sort(sortedIndices);
            Map<Integer, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < sortedIndices.size(); i++) {
                indexMap.put(sortedIndices.get(i), i);
            }

            DSU dsu = new DSU(sortedIndices.size());
            int answer = m;

            for (int i = 0; i < m; i++) {
                Query q = queries.get(i);
                int a = indexMap.get(q.l - 1);
                int b = indexMap.get(q.r);

                if (!dsu.union(a, b, q.p)) {
                    answer = i;
                    break;
                }
            }

            pw.println(answer);
        }

        pw.flush();
        pw.close();
        br.close();
    }

    static class Query {
        int l, r, p;

        Query(int l, int r, int p) {
            this.l = l;
            this.r = r;
            this.p = p;
        }
    }
}