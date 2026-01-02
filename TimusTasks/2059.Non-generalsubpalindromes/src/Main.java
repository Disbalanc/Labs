import java.io.*;
import java.util.*;

//2059. Не общие подпалиндромы
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Вам даны строки A и B.
//Ваша задача — найти 3 числа:
//        1. x = количество непустых палиндромов p таких, что f(A, p) > f(B, p);
//2. y = количество непустых палиндромов p таких, что f(A, p) = f(B, p) и f(A, p) ≠ 0;
//        3. z = количество непустых палиндромов p таких, что f(A, p) < f(B, p),
//где f(A, p) = количество вхождений p в A.
//        Исходные данные
//Первая строка содержит число тестов T. Следующие 2T строки содержат непустые строки A и B для каждого теста. Длины A и B не превосходят 200 000. Гарантируется, что размер входных данных не превышает 8 мегабайт.
//        Результат
//Для каждого теста i выведите «Case #i: x y z» в отдельной строке.

public class Main {
    static final int ALPHABET = 26;
    static final int MAXLEN = 200005;
    static final long P1 = 91138233;
    static final long P2 = 97266353;
    static final long M1 = 1000000007;
    static final long M2 = 1000000009;

    static long[] pow1, pow2;

    static class Node {
        int[] next = new int[ALPHABET];
        int len, suffLink;
        long occ;
        long hash1, hash2;

        Node() {
            Arrays.fill(next, -1);
            len = 0;
            suffLink = 0;
            occ = 0;
            hash1 = hash2 = 0;
        }
    }

    static ArrayList<Node> treeA, treeB;
    static int lastA, lastB;
    static char[] strA, strB;

    static void initTree() {
        treeA = new ArrayList<>();
        treeB = new ArrayList<>();

        Node root0 = new Node();
        root0.len = -1;
        root0.suffLink = 0;
        treeA.add(root0);
        Node root1 = new Node();
        root1.len = 0;
        root1.suffLink = 0;
        treeA.add(root1);
        lastA = 1;

        root0 = new Node();
        root0.len = -1;
        root0.suffLink = 0;
        treeB.add(root0);
        root1 = new Node();
        root1.len = 0;
        root1.suffLink = 0;
        treeB.add(root1);
        lastB = 1;
    }

    static void addLetter(ArrayList<Node> tree, int pos, char[] s, int[] last) {
        int ch = s[pos] - 'a';
        int cur = last[0];
        while (true) {
            int curLen = tree.get(cur).len;
            if (pos - curLen - 1 >= 0 && s[pos - curLen - 1] == s[pos]) {
                break;
            }
            cur = tree.get(cur).suffLink;
        }

        if (tree.get(cur).next[ch] != -1) {
            last[0] = tree.get(cur).next[ch];
            tree.get(last[0]).occ++;
            return;
        }

        Node newNode = new Node();
        int newId = tree.size();
        tree.add(newNode);
        tree.get(cur).next[ch] = newId;
        newNode.len = tree.get(cur).len + 2;

        if (newNode.len == 1) {
            newNode.suffLink = 1;
            newNode.hash1 = (ch + 1) % M1;
            newNode.hash2 = (ch + 1) % M2;
        } else {
            int suffLinkCur = tree.get(cur).suffLink;
            while (true) {
                int suffLen = tree.get(suffLinkCur).len;
                if (pos - suffLen - 1 >= 0 && s[pos - suffLen - 1] == s[pos]) {
                    break;
                }
                suffLinkCur = tree.get(suffLinkCur).suffLink;
            }
            newNode.suffLink = tree.get(suffLinkCur).next[ch];
            Node curNode = tree.get(cur);
            newNode.hash1 = ((ch + 1) * pow1[newNode.len - 1] + curNode.hash1 * P1 + (ch + 1)) % M1;
            newNode.hash2 = ((ch + 1) * pow2[newNode.len - 1] + curNode.hash2 * P2 + (ch + 1)) % M2;
        }
        last[0] = newId;
        tree.get(last[0]).occ = 1;
    }

    static void propagateOccurrences(ArrayList<Node> tree) {
        for (int i = tree.size() - 1; i >= 0; i--) {
            if (tree.get(i).suffLink >= 0) {
                tree.get(tree.get(i).suffLink).occ += tree.get(i).occ;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        pow1 = new long[MAXLEN];
        pow2 = new long[MAXLEN];
        pow1[0] = pow2[0] = 1;
        for (int i = 1; i < MAXLEN; i++) {
            pow1[i] = (pow1[i - 1] * P1) % M1;
            pow2[i] = (pow2[i - 1] * P2) % M2;
        }

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            strA = br.readLine().toCharArray();
            strB = br.readLine().toCharArray();

            initTree();

            int[] lastAArr = {lastA};
            int[] lastBArr = {lastB};
            for (int i = 0; i < strA.length; i++) {
                addLetter(treeA, i, strA, lastAArr);
            }
            for (int i = 0; i < strB.length; i++) {
                addLetter(treeB, i, strB, lastBArr);
            }

            propagateOccurrences(treeA);
            propagateOccurrences(treeB);

            Map<Long, long[]> map = new HashMap<>();
            for (int i = 2; i < treeA.size(); i++) {
                Node node = treeA.get(i);
                long key = (node.hash1 << 32) | (node.hash2 & 0xffffffffL);
                long[] val = map.get(key);
                if (val == null) {
                    val = new long[2];
                    map.put(key, val);
                }
                val[0] = node.occ;
            }
            for (int i = 2; i < treeB.size(); i++) {
                Node node = treeB.get(i);
                long key = (node.hash1 << 32) | (node.hash2 & 0xffffffffL);
                long[] val = map.get(key);
                if (val == null) {
                    val = new long[2];
                    val[0] = 0;
                    map.put(key, val);
                }
                val[1] = node.occ;
            }

            long x = 0, y = 0, z = 0;
            for (long[] val : map.values()) {
                long cntA = val[0];
                long cntB = val[1];
                if (cntA > cntB) {
                    x++;
                } else if (cntA == cntB) {
                    if (cntA != 0) y++;
                } else {
                    z++;
                }
            }

            pw.println("Case #" + testCase + ": " + x + " " + y + " " + z);
        }

        pw.flush();
    }
}