import java.io.*;
import java.util.*;

//1521. Военные учения 2
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//        Вступление
//В ходе недавних военных учений (более подробно эта история рассказана в задаче «Военные учения») министр обороны Советской Федерации товарищ Иванов имел возможность лично убедиться в блестящей боевой готовности солдат вверенной ему Советской Армии. Но одна вещь всё же продолжала беспокоить выдающегося военачальника. Прославленный генерал понимал, что была продемонстрирована лишь физическая подготовка солдат. Теперь настало время организовать очередные учения и проверить интеллектуальные способности личного состава.
//        Генерал Шульман, вновь назначенный ответственным за проведение учений, пожертвовал все выделенные деньги бедным и с чистой совестью лёг спать. Во сне генералу явился учебник по тактике и изложил схему, руководствуясь которой можно провести учения совершенно бесплатно.
//Задача
//В соответствии с этой схемой учения делятся на N раундов, в течение которых N солдат, последовательно пронумерованных от 1 до N, маршируют друг за другом по кругу, т.е. первый следует за вторым, второй за третьим, ..., (N-1)-й за N-м, а N-й за первым. В каждом раунде очередной солдат выбывает из круга и идёт чистить унитазы, а оставшиеся продолжают маршировать. В очередном раунде выбывает солдат, марширующий на K позиций впереди выбывшего на предыдущем раунде. В первом раунде выбывает солдат с номером K.
//Разумеется, г-н Шульман не питал никаких надежд на то, что солдаты в состоянии сами определить очерёдность выбывания из круга. «Эти неучи даже траву не могут ровно покрасить», – фыркнул он и отправился за помощью к прапорщику Шкурко.
//        Исходные данные
//Единственная строка содержит целые числа N (1 ≤ N ≤ 100000) и K (1 ≤ K ≤ N).
//Результат
//Вывести через пробел номера солдат в порядке их выбывания из круга.

public class Main {
    static class Fenwick {
        int[] tree;
        int size;

        Fenwick(int n) {
            size = n;
            tree = new int[n + 1];
        }

        void add(int idx, int delta) {
            while (idx <= size) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }

        int sum(int idx) {
            int s = 0;
            while (idx > 0) {
                s += tree[idx];
                idx -= idx & -idx;
            }
            return s;
        }

        // Находит наименьший индекс, для которого сумма на префиксе >= target
        int find_by_sum(int target) {
            int idx = 0;
            int bitMask = Integer.highestOneBit(size);
            while (bitMask != 0) {
                int tIdx = idx + bitMask;
                if (tIdx <= size && tree[tIdx] < target) {
                    target -= tree[tIdx];
                    idx = tIdx;
                }
                bitMask >>= 1;
            }
            return idx + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Fenwick ft = new Fenwick(N);
        for (int i = 1; i <= N; i++) {
            ft.add(i, 1);
        }

        StringBuilder result = new StringBuilder();
        int cur = 1;
        for (int i = 0; i < N; i++) {
            int total_before = ft.sum(N);
            int rank_cur = ft.sum(cur);
            int steps = (K - 1) % total_before;
            int target_rank = (rank_cur + steps) % total_before;
            if (target_rank == 0) {
                target_rank = total_before;
            }
            int pos = ft.find_by_sum(target_rank);
            result.append(pos).append(' ');

            int rank_pos = target_rank; // ft.sum(pos) до удаления равно target_rank
            ft.add(pos, -1);
            int total_after = total_before - 1;
            if (total_after == 0) {
                break;
            }
            int next_rank;
            if (rank_pos <= total_after) {
                next_rank = rank_pos;
            } else {
                next_rank = 1;
            }
            cur = ft.find_by_sum(next_rank);
        }

        System.out.println(result.toString().trim());
    }
}