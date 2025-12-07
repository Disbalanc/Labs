import java.io.*;
import java.util.*;

public class Main {
    // Дерево Фенвика для подсчета количества звезд по координате X
    static class FenwickTree {
        int[] tree;
        int size;

        public FenwickTree(int size) {
            this.size = size;
            tree = new int[size + 1]; // индексация с 1
        }

        // Добавление единицы в позицию idx
        public void update(int idx, int delta) {
            while (idx <= size) {
                tree[idx] += delta;
                idx += idx & -idx; // переход к следующему узлу
            }
        }

        // Сумма на префиксе [1..idx]
        public int sum(int idx) {
            int s = 0;
            while (idx > 0) {
                s += tree[idx];
                idx -= idx & -idx; // переход к родителю
            }
            return s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Чтение количества звезд
        int N = Integer.parseInt(br.readLine().trim());
        FenwickTree ft = new FenwickTree(32001); // координаты X от 0 до 32000, сдвиг +1
        int[] levels = new int[N]; // levels[i] - количество звезд уровня i

        // Обработка звезд в порядке возрастания Y
        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().trim().split(" ");
            int x = Integer.parseInt(parts[0]);
            // y не используется, так как звезды уже отсортированы по Y

            // Уровень звезды = количество звезд с X' ≤ x среди уже обработанных
            int level = ft.sum(x + 1); // x+1 из-за сдвига в дереве Фенвика
            levels[level]++;

            // Добавляем текущую звезду в дерево
            ft.update(x + 1, 1);
        }

        // Вывод результатов
        for (int i = 0; i < N; i++) {
            pw.println(levels[i]);
        }

        pw.flush();
        pw.close();
        br.close();
    }
}