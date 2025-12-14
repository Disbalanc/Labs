import java.io.*;
import java.util.*;

//1350. Столовая
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Опасно есть в столовой — можно отравиться несвежими продуктами, и вообще — опасно. Причём один человек может впасть в кому от столовской курицы, а другому хоть бы хны. И наоборот. Еду в столовой готовят из M разных продуктов. Всего в меню N продуктов, но не все они есть на раздаче. Допустим, что едят эту еду K + 1 студентов и для каждого из них известно, какими продуктами он может отравиться. Первым, естественно, ест самый хитрый — тот, кто пролез без очереди. И он, допустим, не отравился. Как тогда обед подействует на остальных?
//Исходные данные
//В первой строке находится одно число N (1 ≤ N ≤ 100). В следующих N строках названия продуктов — непустые последовательности латинских букв и цифр длиной не более 40 символов. Затем следует число K (1 ≤ K ≤ 100), за которым идёт K + 1 блоков, описывающих продукты из меню, опасные для посетителей столовой. i-й такой блок начинается строкой с числом Ni — количеством продуктов, вслед за которым идёт Ni строк с названиями опасных продуктов (0 ≤ Ni ≤ N). Первый блок описывает продукты, опасные для самого хитрого студента, следующие K блоков — для всех остальных. Вход заканчивается строкой, содержащей число M (0 ≤ M ≤ N).
//Результат
//Выведите K строк — в i-й строке:
//YES, если обед будет полностью безвреден для (i + 1)-го студента,
//NO, если среди имеющихся продуктов есть вредный для (i + 1)-го студента,
//MAYBE, если при таких исходных данных возможна и та, и другая ситуация

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Чтение количества всех продуктов
        int N = Integer.parseInt(reader.readLine().trim());

        // Чтение названий продуктов и создание карты для быстрого доступа
        Map<String, Integer> productIndex = new HashMap<>();
        String[] products = new String[N];

        for (int i = 0; i < N; i++) {
            String product = reader.readLine().trim();
            products[i] = product;
            productIndex.put(product, i);
        }

        // Чтение количества студентов (K)
        int K = Integer.parseInt(reader.readLine().trim());

        // Массив для хранения опасных продуктов каждого студента
        BitSet[] dangerousProducts = new BitSet[K + 1];
        for (int i = 0; i <= K; i++) {
            dangerousProducts[i] = new BitSet(N);
        }

        // Чтение данных о каждом студенте
        for (int i = 0; i <= K; i++) {
            int Ni = Integer.parseInt(reader.readLine().trim());

            for (int j = 0; j < Ni; j++) {
                String product = reader.readLine().trim();
                int index = productIndex.get(product);
                dangerousProducts[i].set(index);
            }
        }

        // Чтение количества доступных продуктов
        int M = Integer.parseInt(reader.readLine().trim());

        // Вычисление ответов для каждого студента (кроме первого)
        for (int i = 1; i <= K; i++) {
            System.out.println(getAnswer(N, dangerousProducts[0], dangerousProducts[i], M));
        }
    }

    private static String getAnswer(int totalProducts, BitSet firstStudentDangerous,
                                    BitSet currentStudentDangerous, int availableCount) {
        // Множество продуктов, безопасных для первого студента
        BitSet safeForFirst = new BitSet(totalProducts);
        safeForFirst.set(0, totalProducts);
        safeForFirst.andNot(firstStudentDangerous);

        // Продукты, опасные для текущего студента, но безопасные для первого
        BitSet dangerousForCurrentSafeForFirst = (BitSet) currentStudentDangerous.clone();
        dangerousForCurrentSafeForFirst.andNot(firstStudentDangerous);

        // Специальный случай: M = 0 - нет доступных продуктов
        if (availableCount == 0) {
            return "YES";
        }

        // Если нет продуктов, опасных для текущего студента, но безопасных для первого
        if (dangerousForCurrentSafeForFirst.isEmpty()) {
            return "YES";
        }

        // Продукты, безопасные для обоих студентов
        BitSet safeForBoth = new BitSet(totalProducts);
        safeForBoth.set(0, totalProducts);
        safeForBoth.andNot(firstStudentDangerous);
        safeForBoth.andNot(currentStudentDangerous);

        int safeForBothCount = safeForBoth.cardinality();

        // Проверяем, можно ли выбрать M продуктов только из безопасных для обоих
        if (safeForBothCount >= availableCount) {
            // Существует набор, безопасный для обоих, но также существует набор,
            // содержащий опасные продукты для текущего студента
            return "MAYBE";
        } else {
            // Любой набор из M продуктов, безопасных для первого, обязательно
            // содержит хотя бы один продукт, опасный для текущего студента
            return "NO";
        }
    }
}