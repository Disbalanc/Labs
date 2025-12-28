import java.util.*;
import java.io.*;
import java.math.BigInteger;

//1158. Censored!
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//The alphabet of Freeland consists of exactly N letters. Each sentence of Freeland language (also known as Freish) consists of exactly M letters without word breaks. So, there exist exactly NM different Freish sentences.
//But after recent election of Mr. Grass Jr. as Freeland president some words offending him were declared unprintable and all sentences containing at least one of them were forbidden. The sentence S contains a word W if W is a substring of S i.e. exists such k >= 1 that S[k] = W[1], S[k+1] = W[2], ... , S[k+len(W)-1] = W[len(W)], where k+len(W)-1 <= M and len(W) denotes length of W. Everyone who uses a forbidden sentence is to be put to jail for 10 years.
//Find out how many different sentences can be used now by freelanders without risk to be put to jail for using it.
//Исходные данные
//The first line contains three integer numbers: N - the number of letters in Freish alphabet, M - the length of all Freish sentences and P - the number of forbidden words (1 ≤ N ≤ 50, 1 ≤ M ≤ 50, 0 ≤ P ≤ 10).
//The second line contains exactly N different characters - the letters of the Freish alphabet (all with ASCII code greater than 32).
//The following P lines contain forbidden words, each not longer than min(M, 10) characters, all containing only letters of Freish alphabet.
//Результат
//Output the only integer number - the number of different sentences freelanders can safely use.

public class Main {

    static class Node {
        int[] next;
        int fail;
        boolean terminal;

        Node(int alphabetSize) {
            next = new int[alphabetSize];
            Arrays.fill(next, -1);
            fail = 0;
            terminal = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]); // размер алфавита
        int M = Integer.parseInt(firstLine[1]); // длина строки
        int P = Integer.parseInt(firstLine[2]); // количество запрещенных слов

        String alphabetStr = reader.readLine();
        char[] alphabet = alphabetStr.toCharArray();

        // Создаем отображение символа в индекс
        int[] charToIndex = new int[256];
        Arrays.fill(charToIndex, -1);
        for (int i = 0; i < N; i++) {
            charToIndex[alphabet[i]] = i;
        }

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(N)); // корень

        // Добавляем запрещенные слова в бор
        for (int i = 0; i < P; i++) {
            String word = reader.readLine();
            int state = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                int idx = charToIndex[c];
                Node node = nodes.get(state);
                if (node.next[idx] == -1) {
                    node.next[idx] = nodes.size();
                    nodes.add(new Node(N));
                }
                state = node.next[idx];
            }
            nodes.get(state).terminal = true;
        }

        // Строим fail-ссылки с помощью BFS
        Queue<Integer> queue = new LinkedList<>();

        // Инициализируем fail для детей корня
        Node root = nodes.get(0);
        for (int i = 0; i < N; i++) {
            if (root.next[i] != -1) {
                nodes.get(root.next[i]).fail = 0;
                queue.add(root.next[i]);
            } else {
                root.next[i] = 0; // переход в корень для отсутствующих символов
            }
        }

        while (!queue.isEmpty()) {
            int state = queue.poll();
            Node node = nodes.get(state);

            // Если состояние, на которое ведет fail-ссылка, терминальное,
            // то текущее состояние тоже помечаем как терминальное
            if (nodes.get(node.fail).terminal) {
                node.terminal = true;
            }

            // Для каждого символа алфавита
            for (int i = 0; i < N; i++) {
                int nextState = node.next[i];
                if (nextState != -1) {
                    // Устанавливаем fail для ребенка
                    nodes.get(nextState).fail = nodes.get(node.fail).next[i];
                    queue.add(nextState);
                } else {
                    // Перенаправляем переход на состояние по fail-ссылке
                    node.next[i] = nodes.get(node.fail).next[i];
                }
            }
        }

        // Динамическое программирование
        int numStates = nodes.size();
        BigInteger[][] dp = new BigInteger[M + 1][numStates];
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j < numStates; j++) {
                dp[i][j] = BigInteger.ZERO;
            }
        }
        dp[0][0] = BigInteger.ONE;

        for (int i = 0; i < M; i++) {
            for (int state = 0; state < numStates; state++) {
                if (dp[i][state].equals(BigInteger.ZERO)) {
                    continue;
                }

                Node node = nodes.get(state);
                // Перебираем все символы алфавита
                for (int symbolIdx = 0; symbolIdx < N; symbolIdx++) {
                    int nextState = node.next[symbolIdx];
                    if (!nodes.get(nextState).terminal) {
                        dp[i + 1][nextState] = dp[i + 1][nextState].add(dp[i][state]);
                    }
                }
            }
        }

        // Суммируем все безопасные строки длины M
        BigInteger answer = BigInteger.ZERO;
        for (int state = 0; state < numStates; state++) {
            if (!nodes.get(state).terminal) {
                answer = answer.add(dp[M][state]);
            }
        }

        System.out.println(answer);
    }
}