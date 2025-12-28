import java.util.*;
import java.io.*;

//1102. Странный диалог
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 16 МБ
//Одна сущность по имени "one" беседует со своим другом, сущностью "puton", и нас интересует их разговор. "One" может говорить слова "out" и "output", кроме того, он может называть своего друга по имени. "Puton" может говорить слова "in", "input" и "one". Они прекрасно понимают друг друга и даже пишут диалоги в строки без пробелов между словами.
//Дано N строк. Определите, какие из них являются диалогами.
//        Исходные данные
//В первой строке ввода содержится целое число N (1 ≤ N ≤ 1000). Следующие N строк содержат непустые последовательности строчных латинских букв. Общая длина всех строк не превышает 4 · 106 символов.
//        Результат
//Вывод состоит из N строк. Строка содержит слово "YES", если соответствующая строка ввода является некоторым диалогом сущностей "one" и "puton", в противном случае строка содержит "NO".

public class Main {

    static class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.isWord = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

        // Создаем префиксное дерево
        Trie trie = new Trie();
        String[] words = {"in", "out", "one", "input", "puton", "output"};
        for (String word : words) {
            trie.insert(word);
        }

        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            String s = reader.readLine();
            writer.println(canBeDialogue(s, trie) ? "YES" : "NO");
        }

        writer.flush();
    }

    private static boolean canBeDialogue(String s, Trie trie) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;

            // Ищем все слова, начинающиеся с позиции i
            TrieNode node = trie.root;
            for (int j = i; j < Math.min(n, i + 10); j++) { // Максимальная длина слова 6
                char c = s.charAt(j);
                int idx = c - 'a';
                if (node.children[idx] == null) break;

                node = node.children[idx];
                if (node.isWord) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[n];
    }
}