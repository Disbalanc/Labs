import java.io.*;
import java.util.StringTokenizer;

//1223. Chernobyl’ Eagle on a Roof
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Once upon a time an Eagle made a nest on the roof of a very large building. Time went by and some eggs appeared in the nest. There was a sunny day, and Niels Bohr was walking on the roof. He suddenly said: “Oops! All eggs surely have the same solidity, thus there is such non-negative number E that if one drops an egg from the floor number E, it will not be broken (and so for all the floors below the E-th), but if one drops it from the floor number E+1, the egg will be broken (and the same for every floor higher, than the E-th).” Now Professor Bohr is going to organize a series of experiments (i.e. drops). The goal of the experiments is to determine the constant E. It is evident that number E may be found by dropping eggs sequentially floor by floor from the lowest one. But there are other strategies to find E for sure with much less amount of experiments. You are to find the least number of eggs droppings, which is sufficient to find number E for sure, even in the worst case. Note that dropped eggs that are not broken can be used again in following experiments.
//The floors are numbered with positive integers starting from 1. If an egg has been broken being dropped from the first floor, you should consider that E is equal to zero. If an egg hasn’t been broken even being dropped from the highest floor, consider that E is also determined and equal to the total number of floors.
//        Исходные данные
//Input contains multiple (up to 1000) test cases. Each line is a test case. Each test case consists of two numbers separated with a space: the number of eggs, and the number of floors. Both numbers are positive and do not exceed 1000. Tests will end with the line containing two zeroes.
//        Результат
//For each test case output in a separate line the minimal number of experiments, which Niels Bohr will have to make even in the worst case.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Максимальные значения по условию
        final int MAX = 1000;

        // dp[eggs][drops] - максимальное количество этажей, которое можно проверить
        int[][] dp = new int[MAX + 1][MAX + 1];

        // Инициализация
        for (int eggs = 0; eggs <= MAX; eggs++) {
            for (int drops = 0; drops <= MAX; drops++) {
                if (eggs == 0 || drops == 0) {
                    dp[eggs][drops] = 0;
                } else {
                    // Рекуррентная формула
                    int val = dp[eggs - 1][drops - 1] + 1 + dp[eggs][drops - 1];
                    // Ограничиваем значением MAX, так как floors не превышает 1000
                    dp[eggs][drops] = Math.min(val, MAX);
                }
            }
        }

        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int eggs = Integer.parseInt(st.nextToken());
            int floors = Integer.parseInt(st.nextToken());

            // Конец ввода
            if (eggs == 0 && floors == 0) {
                break;
            }

            // Обработка краевых случаев
            if (eggs == 0) {
                out.println(0);
                continue;
            }

            if (eggs == 1) {
                out.println(floors);
                continue;
            }

            // Поиск минимального количества попыток
            int ans = -1;
            for (int drops = 1; drops <= MAX; drops++) {
                if (dp[eggs][drops] >= floors) {
                    ans = drops;
                    break;
                }
            }

            out.println(ans);
        }

        out.flush();
    }
}