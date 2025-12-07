import java.io.*;
import java.util.*;

public class Main {
    private static final int N = 30000; // количество блоков
    private static final int T = 600;   // 10 минут в секундах

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Множество свободных блоков (для быстрого поиска минимального)
        TreeSet<Integer> freeBlocks = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            freeBlocks.add(i);
        }

        // Массив, хранящий время освобождения каждого блока (-1 = свободен)
        int[] releaseTime = new int[N + 1];
        Arrays.fill(releaseTime, -1);

        // Минимальная куча (приоритетная очередь) событий освобождения
        // Каждый элемент - массив из двух чисел: [время_освобождения, номер_блока]
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            int time = Integer.parseInt(parts[0]);
            char operation = parts[1].charAt(0);

            // Освобождаем все блоки, время освобождения которых <= текущего времени
            while (!heap.isEmpty() && heap.peek()[0] <= time) {
                int[] event = heap.poll();
                int block = event[1];
                // Проверяем, что событие не устарело (блок не был перезанят)
                if (releaseTime[block] == event[0]) {
                    releaseTime[block] = -1;
                    freeBlocks.add(block);
                }
            }

            if (operation == '+') { // Запрос на выделение
                int block = freeBlocks.pollFirst();
                releaseTime[block] = time + T;
                heap.offer(new int[]{time + T, block});
                pw.println(block);
            } else { // Запрос на доступ к блоку (формат: "время . номер_блока")
                int block = Integer.parseInt(parts[1].substring(1));
                if (releaseTime[block] != -1) { // Блок занят
                    releaseTime[block] = time + T;
                    heap.offer(new int[]{time + T, block});
                    pw.println("+");
                } else {
                    pw.println("-");
                }
            }
        }

        pw.flush();
    }
}