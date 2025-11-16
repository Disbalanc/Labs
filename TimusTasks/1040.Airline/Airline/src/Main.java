import java.util.*;

//1040. Авиакомпания
//Ограничение времени: 0.5 секунды
//Ограничение памяти: 64 МБ
//Некоторая авиакомпания является спонсором празднования 80-летия Уральского государственного университета. В обмен на это авиакомпания хочет, чтобы университет помог ей. Авиакомпания обслуживает N аэропортов и выполняет рейсы между некоторыми из них. Для упрощения работы рейсы нумеруются целыми числами от 1 до M. Если между двумя аэропортами есть рейс, то самолет летит в обоих направлениях с одним и тем же номером рейса. Между любыми двумя аэропортами может быть только один рейс. Из любого аэропорта можно добраться до любого другого, используя рейсы только этой авиакомпании.
//        Авиакомпания понимает, что ее самолеты могут привлечь террористов. Чтобы создать им трудности, компания хочет пронумеровать рейсы неким особым образом. Если есть несколько рейсов, которые отправляются из одного аэропорта, то наибольший общий делитель номеров их рейсов должен быть равен единице. Компания обращается к вам за помощью и помните, что это спонсор – вы должны поработать как следует.
//Необходимо написать программу, которая найдет требуемую нумерацию или сообщит, что выполнить требования невозможно. Если возможно несколько нумераций, то достаточно найти любую из них.
//        Исходные данные
//Первая строка содержит целые числа N и M (2 ≤ N ≤ 50; 1 ≤ M ≤ N · (N − 1) / 2). Следующие M строк содержат информацию о рейсах. Каждый рейс задается номерами аэропортов, которые он соединяет. Номера аэропортов – различные целые числа в диапазоне от 1 до N.
//Результат
//В первой строке выведите «YES», если можно найти требуемую нумерацию, и «NO» в противном случае. Если ответ «YES», то вторая строка должна содержать любую возможную нумерацию рейсов. Номера должны следовать в том порядке, в котором эти рейсы были перечислены во входных данных.

public class Main {
    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static class Edge {
        int u, v, index;
        Edge(int u, int v, int index) {
            this.u = u;
            this.v = v;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String testInput = "6 6\n1 2\n2 3\n2 4\n4 3\n5 6\n4 5";
        sc = new Scanner(testInput);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] degree = new int[N + 1];
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new Edge(u, v, i));
            degree[u]++;
            degree[v]++;
        }

        Random rand = new Random();
        int[] bestAssignment = null;
        boolean found = false;

        // Пробуем несколько случайных перестановок
        for (int attempt = 0; attempt < 100; attempt++) {
            int[] assignment = new int[M];
            boolean[] used = new boolean[M + 1];
            int[] currentGCD = new int[N + 1];

            // Создаем копию ребер и перемешиваем
            List<Edge> shuffledEdges = new ArrayList<>(edges);
            Collections.shuffle(shuffledEdges, rand);

            boolean validAssignment = true;

            for (Edge e : shuffledEdges) {
                int bestX = -1;
                int bestCost = Integer.MAX_VALUE;

                // Ищем лучший номер для текущего ребра
                for (int x = 1; x <= M; x++) {
                    if (used[x]) continue;

                    int newG1 = currentGCD[e.u] == 0 ? x : gcd(currentGCD[e.u], x);
                    int newG2 = currentGCD[e.v] == 0 ? x : gcd(currentGCD[e.v], x);

                    int cost = (newG1 != 1 ? 1 : 0) + (newG2 != 1 ? 1 : 0);

                    if (cost < bestCost) {
                        bestCost = cost;
                        bestX = x;
                    }
                }

                // Если не нашли подходящий номер, берем любой доступный
                if (bestX == -1) {
                    for (int x = 1; x <= M; x++) {
                        if (!used[x]) {
                            bestX = x;
                            break;
                        }
                    }
                }

                assignment[e.index] = bestX;
                used[bestX] = true;
                currentGCD[e.u] = currentGCD[e.u] == 0 ? bestX : gcd(currentGCD[e.u], bestX);
                currentGCD[e.v] = currentGCD[e.v] == 0 ? bestX : gcd(currentGCD[e.v], bestX);
            }

            // Проверяем валидность назначения
            boolean valid = true;
            for (int i = 1; i <= N; i++) {
                if (degree[i] > 1 && currentGCD[i] != 1) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                bestAssignment = assignment;
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("YES");
            for (int i = 0; i < M; i++) {
                System.out.print(bestAssignment[i] + (i < M - 1 ? " " : ""));
            }
            System.out.println();
        } else {
            System.out.println("NO");
        }

        sc.close();
    }
}