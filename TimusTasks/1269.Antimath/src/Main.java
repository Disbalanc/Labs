import java.util.*;
import java.io.*;

//1269. Антимат
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 8 МБ
//При разработке досок обсуждений часто возникает задача проверять сообщения пользователей на наличие нецензурной лексики. Ваши старшие товарищи доверили эту задачу вам. Нужно проверить, содержит ли текст хоть одно слово из списка в качестве подстроки.
//Исходные данные
//Первая строка содержит число n (1 ≤ n ≤ 10000) — количество слов. Следующие n строк содержат список слов, использование которых в нашем культурном обществе считается недопустимым. В слове могут встречаться любые символы, кроме символов с кодами 0, 10 и 13. Длина каждого слова не превышает 10000 символов. Общий объём слов не превышает 100 КБ. После этого идёт число m — количество строк в тексте. Общий объём текста не превышает 900 КБ.
//        Результат
//Выведите через пробел номер строки и номер позиции, в которой первый раз встретилось плохое слово. Если в тексте нет плохих слов, выведите «Passed».

public class Main {

    static class Node {
        Map<Character, Integer> next = new HashMap<>();
        int fail = 0;
        int length = 0; // длина самого длинного запрещенного слова, оканчивающегося в этом узле
        boolean isTerminal = false;
    }

    static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Считываем количество запрещенных слов
        int n = Integer.parseInt(reader.readLine());

        // Инициализируем корень автомата
        nodes.add(new Node());

        // Считываем запрещенные слова и добавляем их в автомат
        for (int i = 0; i < n; i++) {
            String word = reader.readLine();
            addWord(word);
        }

        // Строим автомат Ахо-Корасик
        buildAutomaton();

        // Считываем количество строк в тексте
        int m = Integer.parseInt(reader.readLine());

        // Обрабатываем текст построчно
        for (int lineNum = 1; lineNum <= m; lineNum++) {
            String line = reader.readLine();

            // Ищем первое вхождение запрещенного слова в этой строке
            Result result = findFirstOccurrence(line, lineNum);

            // Если нашли, выводим результат и завершаем программу
            if (result != null) {
                System.out.println(result.line + " " + result.position);
                return;
            }
        }

        // Если не нашли ни одного запрещенного слова
        System.out.println("Passed");
    }

    // Добавление слова в автомат
    private static void addWord(String word) {
        int state = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!nodes.get(state).next.containsKey(c)) {
                nodes.get(state).next.put(c, nodes.size());
                nodes.add(new Node());
            }

            state = nodes.get(state).next.get(c);
        }

        // Помечаем конечное состояние и сохраняем длину слова
        nodes.get(state).isTerminal = true;
        if (word.length() > nodes.get(state).length) {
            nodes.get(state).length = word.length();
        }
    }

    // Построение автомата Ахо-Корасик
    private static void buildAutomaton() {
        Queue<Integer> queue = new LinkedList<>();

        // Инициализируем fail-ссылки для детей корня
        for (Map.Entry<Character, Integer> entry : nodes.get(0).next.entrySet()) {
            int child = entry.getValue();
            nodes.get(child).fail = 0;
            queue.add(child);
        }

        // Обработка остальных узлов
        while (!queue.isEmpty()) {
            int current = queue.poll();
            Node node = nodes.get(current);

            // Обрабатываем переходы текущего узла
            for (Map.Entry<Character, Integer> entry : node.next.entrySet()) {
                char c = entry.getKey();
                int child = entry.getValue();

                // Находим состояние для fail-ссылки
                int failState = node.fail;
                while (failState != 0 && !nodes.get(failState).next.containsKey(c)) {
                    failState = nodes.get(failState).fail;
                }

                if (nodes.get(failState).next.containsKey(c)) {
                    nodes.get(child).fail = nodes.get(failState).next.get(c);
                } else {
                    nodes.get(child).fail = 0;
                }

                // Распространяем терминальность через суффиксные ссылки
                if (nodes.get(nodes.get(child).fail).isTerminal) {
                    nodes.get(child).isTerminal = true;
                    if (nodes.get(nodes.get(child).fail).length > nodes.get(child).length) {
                        nodes.get(child).length = nodes.get(nodes.get(child).fail).length;
                    }
                }

                queue.add(child);
            }
        }
    }

    // Поиск первого вхождения запрещенного слова в строке
    private static Result findFirstOccurrence(String line, int lineNum) {
        int state = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            // Переход по символу с использованием fail-ссылок
            while (state != 0 && !nodes.get(state).next.containsKey(c)) {
                state = nodes.get(state).fail;
            }

            if (nodes.get(state).next.containsKey(c)) {
                state = nodes.get(state).next.get(c);
            } else {
                state = 0;
            }

            // Проверяем, является ли текущее состояние терминальным
            if (nodes.get(state).isTerminal) {
                // Нашли запрещенное слово, вычисляем позицию начала
                int startPos = i - nodes.get(state).length + 2; // +1 для 1-индексации
                return new Result(lineNum, startPos);
            }
        }

        return null;
    }

    static class Result {
        int line;
        int position;

        Result(int line, int position) {
            this.line = line;
            this.position = position;
        }
    }
}