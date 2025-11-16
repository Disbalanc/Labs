import java.util.*;
import java.io.*;

//1424. Маршрутка
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//        Вступление
//Водитель маршрутки Сергей Александрович Жадных прославился своей феноменальной жадностью повсеместно. Он сам неоднократно заявлял, что за лишнюю копейку готов задушить родного брата и продать всех друзей. К сожалению, проверить эти слова не представлялось возможным, поскольку никакого брата, а также друзей, дома и семьи у Сергея Александровича не было. И что ещё хуже, денег у него тоже не было. Единственным достоянием г-на Жадных являлась старая маршрутка, на которой он и колесил по городу, подвозя редких пассажиров и время от времени осматривая краем глаза тротуары в поисках мелких монет...
//В один из дней небеса сжалились над Сергеем Александровичем и решили прекратить его бесполезное существование в этом жестоком мире. С сей благой целью на голову ничего не подозревавшего г-на Жадных, вышедшего из маршрутки на призывный блеск 5-рублёвой монеты, свалился топор. Мечты о выгодной продаже бутылки мигом вылетели у него из головы, поскольку их место занял топор. В переносном смысле этого слова. Орудие небес не смогло пробить окостеневший череп г-на Жадных, однако, как выяснилось позднее, придало ему несколько весьма полезных свойств.
//Вскоре после продажи топора Сергей Александрович обнаружил, что способен предвидеть будущее. Какие возможности, какие перспективы открылись незадачливому водителю маршрутки! Кто мы такие и куда идём? Чего бояться и на что надеяться? Ответы на эти вопросы г-на Жадных совершенно не интересовали. А вот на то, чтобы с помощью новых умений попытаться заработать немного денег, выполняя привычную работу, сообразительности у Сергея Александровича хватило.
//Задача
//Ежедневно маршрутка совершает один рейс от первой до N-й остановки. В маршрутке M мест для пассажиров. Вечером, просчитав линии вероятностей, г-н Жадных (между прочим, потенциальный Тёмный Иной шестого уровня) выяснил, что завтра на остановках маршрутку будут поджидать K человек. Для каждого человека были определены номер остановки S[i], на которой он желает сесть в маршрутку, и номер остановки F[i], на которой он собирается выйти. В соответствии с ценовой политикой Сергея Александровича, каждый пассажир должен заплатить P рублей за билет независимо от количества остановок. Более того, притормозив на остановке, г-н Жадных может выбирать, кого из желающих посадить в маршрутку, а кого нет. Ставя перед собой задачу максимизации прибыли, Сергей Александрович вполне разумно решил определить, каких именно людей нужно сажать в маршрутку. К сожалению, для этого его сил оказалось недостаточно. А Ваших?
//Исходные данные
//Первая строка содержит целые числа N (2 ≤ N ≤ 100000), M (1 ≤ M ≤ 1000), K (0 ≤ K ≤ 50000) и P (1 ≤ P ≤ 10000). Каждая из следующих K строк содержит целые числа S[i] и F[i] (1 ≤ S[i] < F[i] ≤ N) для соответствующего человека.
//        Результат
//В первую строку вывести максимальную прибыль. Во вторую строку вывести через пробел и в любом порядке номера людей, которых следует сажать в маршрутку для получения этой прибыли. Если задача имеет несколько решений, то вывести любое из них.

public class Main {
    static class Passenger {
        int s, f, id;
        Passenger(int s, int f, int id) {
            this.s = s;
            this.f = f;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int P = scanner.nextInt();

        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int s = scanner.nextInt();
            int f = scanner.nextInt();
            passengers.add(new Passenger(s, f, i + 1));
        }

        passengers.sort(Comparator.comparingInt(p -> p.f));

        SegmentTree segTree = new SegmentTree(N);
        List<Integer> chosen = new ArrayList<>();

        for (Passenger p : passengers) {
            int l = p.s;
            int r = p.f - 1;
            int max = segTree.query(l, r);
            if (max < M) {
                segTree.update(l, r, 1);
                chosen.add(p.id);
            }
        }

        long profit = (long) chosen.size() * P;
        System.out.println(profit);
        if (!chosen.isEmpty()) {
            for (int i = 0; i < chosen.size(); i++) {
                System.out.print(chosen.get(i));
                if (i < chosen.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static class SegmentTree {
        int n;
        int[] tree;
        int[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }

        private void push(int idx, int l, int r) {
            if (lazy[idx] != 0) {
                tree[idx] += lazy[idx];
                if (l != r) {
                    lazy[2 * idx + 1] += lazy[idx];
                    lazy[2 * idx + 2] += lazy[idx];
                }
                lazy[idx] = 0;
            }
        }

        private void update(int idx, int l, int r, int segL, int segR, int val) {
            push(idx, l, r);
            if (segL > r || segR < l) return;
            if (segL <= l && r <= segR) {
                lazy[idx] += val;
                push(idx, l, r);
                return;
            }
            int mid = (l + r) / 2;
            update(2 * idx + 1, l, mid, segL, segR, val);
            update(2 * idx + 2, mid + 1, r, segL, segR, val);
            tree[idx] = Math.max(tree[2 * idx + 1], tree[2 * idx + 2]);
        }

        private int query(int idx, int l, int r, int segL, int segR) {
            push(idx, l, r);
            if (segL > r || segR < l) return 0;
            if (segL <= l && r <= segR) return tree[idx];
            int mid = (l + r) / 2;
            int leftVal = query(2 * idx + 1, l, mid, segL, segR);
            int rightVal = query(2 * idx + 2, mid + 1, r, segL, segR);
            return Math.max(leftVal, rightVal);
        }

        public void update(int l, int r, int val) {
            update(0, 1, n, l, r, val);
        }

        public int query(int l, int r) {
            return query(0, 1, n, l, r);
        }
    }
}