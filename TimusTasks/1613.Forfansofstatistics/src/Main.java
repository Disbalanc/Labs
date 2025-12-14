import java.io.*;
import java.util.*;

//1613. Для любителей статистики
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Вы никогда не задумывались над тем, сколько человек за год перевозят трамваи города с десятимиллионным населением, в котором каждый третий житель пользуется трамваем по два раза в день?
//Предположим, что на планете Земля n городов, в которых есть трамваи. Любители статистики подсчитали для каждого из этих городов, сколько человек перевезено трамваями этого города за последний год. Из этих данных была составлена таблица, в которой города были отсортированы по алфавиту. Позже выяснилось, что для статистики названия городов несущественны, и тогда их просто заменили числами от 1 до n. Поисковая система, работающая с этими данными, должна уметь быстро отвечать на вопрос, есть ли среди городов с номерами от l до r такой, что за год трамваи этого города перевезли ровно x человек. Вам предстоит реализовать этот модуль системы.
//Исходные данные
//В первой строке записано целое число n (1 ≤ n ≤ 69999). В следующей строке приведены статистические данные в виде списка целых чисел через пробел, i-е число в этом списке — количество человек, перевезенных за год трамваями i-го города. Все числа в списке положительны и не превосходят 109 − 1. В третьей строке записано целое число q — количество запросов (1 ≤ q ≤ 69999). В следующих q строках перечислены запросы. Каждый запрос — это тройка целых чисел l, r и x, записанных через пробел (1 ≤ l ≤ r ≤ n; 1 ≤ x ≤ 109 − 1).
//Результат
//Выведите строку длины q, в которой i-й символ равен «1», если ответ на i-й запрос утвердителен, и «0» в противном случае.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().split(" ");

        // Строим отображение: значение -> список позиций (1-based)
        Map<Integer, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(tokens[i]);
            positions.computeIfAbsent(value, k -> new ArrayList<>()).add(i + 1);
        }

        StringBuilder result = new StringBuilder();
        int q = Integer.parseInt(reader.readLine());

        for (int i = 0; i < q; i++) {
            tokens = reader.readLine().split(" ");
            int l = Integer.parseInt(tokens[0]);
            int r = Integer.parseInt(tokens[1]);
            int x = Integer.parseInt(tokens[2]);

            List<Integer> posList = positions.get(x);
            if (posList == null) {
                result.append('0');
                continue;
            }

            // Бинарный поиск первой позиции >= l
            int idx = Collections.binarySearch(posList, l);
            if (idx < 0) {
                idx = -idx - 1;
            }
            if (idx < posList.size() && posList.get(idx) <= r) {
                result.append('1');
            } else {
                result.append('0');
            }
        }

        System.out.println(result.toString());
    }
}