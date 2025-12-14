import java.io.*;
import java.util.*;

//1414. Астрономическая база данных
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//После вывода на орбиту Земли телескопа Hubble количество известных человечеству звёзд заметно возросло. Можно представить себе, насколько увеличится их количество, когда человек освоит гиперпространственный прыжок!
//Предусмотрительные астрономы уже сейчас хотят подготовиться к этому моменту. Они создают программное обеспечение для ведения базы данных всех известных звёзд. База данных будет многопользовательской, и все астрономы вселенной смогут принять участия в её заполнении. Чтобы сделать программное обеспечение максимально удобным для пользователя, необходимо реализовать функцию контекстной подсказки: при вводе очередного символа названия звезды, ПО должно предлагать список звёзд, названия которых начинаются с уже введённых пользователем символов. Вам придётся помочь астрономам в их космической проблеме, и разработать прототип алгоритма, который позже будет использован для ведения астрономической базы данных.
//        Исходные данные
//На вход вашей программе подаётся последовательность операций, по одной в строке. Первый символ строки обозначает тип операции. Оставшиеся символы строки (малые латинские буквы или цифры) — это аргумент операции.
//Типы операций:
//        '+' — добавить название звезды в базу данных. Аргумент этой операции — это название звезды, которое необходимо добавить. Поскольку работа с базой данных идёт в многопользовательском режиме, информация об одной звезде может быть введена в базу несколько раз. В начале выполнения программы считайте, что в базе данных содержится единственное слово «sun».
//        '?' — найти все названия, начинающиеся с символов указанных в аргументе этой команды.
//        Можете считать, что во входных данных содержится не более 10000 операций, а все аргументы операций содержат не менее одного и не более 20 символов.
//        Результат
//На операцию '+' выводить ничего не надо.
//На каждую операцию '?' необходимо вывести результат запроса: аргумент команды, а вслед за ним список названий звёзд, начинающихся с данных символов и содержащихся на момент запроса в базе данных.
//Названия звёзд необходимо выводить в лексикографическом порядке, по одному в строке, без повторений.
//Если таких названий больше 20, то необходимо вывести лишь первые 20. Названия должны быть напечатаны с отступом слева в два пробела, как это показано в примере выходных данных (в примере для наглядности пробелы заменены точками).

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        // Множество слов, заканчивающихся в этом узле (для избежания дубликатов)
        Set<String> words = new HashSet<>();
        boolean isEnd = false;
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.isEnd = true;
            node.words.add(word);
        }

        public List<String> searchByPrefix(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode node = root;

            // Переходим к узлу, соответствующему префиксу
            for (char ch : prefix.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    return result; // Префикс не найден
                }
                node = node.children.get(ch);
            }

            // Обход поддерева в лексикографическом порядке
            dfs(node, result, 20);
            return result;
        }

        private void dfs(TrieNode node, List<String> result, int limit) {
            if (result.size() >= limit) return;

            // Добавляем слова из текущего узла (если есть)
            if (node.isEnd) {
                List<String> sortedWords = new ArrayList<>(node.words);
                Collections.sort(sortedWords);
                for (String word : sortedWords) {
                    if (result.size() >= limit) return;
                    result.add(word);
                }
            }

            // Рекурсивно обходим дочерние узлы в лексикографическом порядке
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                dfs(entry.getValue(), result, limit);
                if (result.size() >= limit) return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();

        // Начальное состояние: добавляем "sun"
        trie.insert("sun");

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            char operation = line.charAt(0);
            String argument = line.substring(1);

            if (operation == '+') {
                // Добавление звезды
                trie.insert(argument);
            } else if (operation == '?') {
                // Поиск по префиксу
                List<String> results = trie.searchByPrefix(argument);

                // Вывод результата
                System.out.println(argument);
                for (String word : results) {
                    System.out.println("  " + word);
                }
            }
        }
    }
}