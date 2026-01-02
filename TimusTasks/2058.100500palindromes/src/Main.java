import java.io.*;
import java.util.*;

//2058. 100500 палиндромов
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Для каждого префикса данной строки определите, возможно ли его разбить на 1, 2, 3, 4, 5, …, n непустых палиндромов. Заметим, что если мы можем разбить строку на k палиндромов, то мы можем разбить её и на k + 2 палиндрома.
//        Исходные данные
//Вход содержит строку из n строчных латинских букв(1 ≤ n ≤ 3 · 105).
//Результат
//Выведите n пар чисел. i-я строка должна содержать минимальное нечётное k (или −1, если его не существует) и минимальное чётное k (или −2, если его не существует) такие, что мы можем разбить строку s[1..i] на k непустых палиндромов.

public class Main {
    static final int ALPHABET = 26;
    static final int INF = 1000000000;

    static class Node {
        int[] next = new int[ALPHABET];
        int len, suffLink;

        Node() {
            Arrays.fill(next, -1);
            len = 0;
            suffLink = 0;
        }
    }

    static ArrayList<Node> tree;
    static int last;
    static String s;

    static void initTree() {
        tree = new ArrayList<>();
        // Узел 0 - корень для нечетных палиндромов (длина -1)
        Node node0 = new Node();
        node0.len = -1;
        node0.suffLink = 0;
        tree.add(node0);
        // Узел 1 - корень для четных палиндромов (длина 0)
        Node node1 = new Node();
        node1.len = 0;
        node1.suffLink = 0;
        tree.add(node1);
        last = 1;
    }

    static void addLetter(int pos) {
        int cur = last;
        int ch = s.charAt(pos) - 'a';
        while (true) {
            int curLen = tree.get(cur).len;
            if (pos - curLen - 1 >= 0 && s.charAt(pos - curLen - 1) == s.charAt(pos)) {
                break;
            }
            cur = tree.get(cur).suffLink;
        }

        if (tree.get(cur).next[ch] != -1) {
            last = tree.get(cur).next[ch];
            return;
        }

        Node newNode = new Node();
        newNode.len = tree.get(cur).len + 2;
        tree.add(newNode);
        int newId = tree.size() - 1;
        tree.get(cur).next[ch] = newId;

        if (newNode.len == 1) {
            newNode.suffLink = 1;
        } else {
            int suffLinkCur = tree.get(cur).suffLink;
            while (true) {
                int suffLen = tree.get(suffLinkCur).len;
                if (pos - suffLen - 1 >= 0 && s.charAt(pos - suffLen - 1) == s.charAt(pos)) {
                    break;
                }
                suffLinkCur = tree.get(suffLinkCur).suffLink;
            }
            newNode.suffLink = tree.get(suffLinkCur).next[ch];
        }
        last = newId;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        int n = s.length();

        initTree();

        int[] odd = new int[n + 1];
        int[] even = new int[n + 1];
        Arrays.fill(odd, INF);
        Arrays.fill(even, INF);
        odd[0] = INF;
        even[0] = 0;

        for (int i = 0; i < n; i++) {
            addLetter(i);
            int cur = last;
            odd[i + 1] = INF;
            even[i + 1] = INF;

            while (cur > 1) {
                int len = tree.get(cur).len;
                int l = i - len + 1;
                if (even[l] != INF) {
                    odd[i + 1] = Math.min(odd[i + 1], even[l] + 1);
                }
                if (odd[l] != INF) {
                    even[i + 1] = Math.min(even[i + 1], odd[l] + 1);
                }
                cur = tree.get(cur).suffLink;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int o = (odd[i] == INF) ? -1 : odd[i];
            int e = (even[i] == INF) ? -2 : even[i];
            sb.append(o).append(' ').append(e).append('\n');
        }
        System.out.print(sb.toString());
    }
}