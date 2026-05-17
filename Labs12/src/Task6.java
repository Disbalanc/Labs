import java.util.concurrent.atomic.AtomicLong;

public class Task6 {

    private static final int[] array = new int[1_000_000];
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final AtomicLong globalSum = new AtomicLong(0);

    static {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // числа от 1 до 1_000_000
        }
    }

    static class SumThread extends Thread {
        private final int startIndex;
        private final int endIndex;

        public SumThread(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex   = endIndex;
        }

        @Override
        public void run() {
            long localSum = 0;
            for (int i = startIndex; i < endIndex; i++) {
                localSum += array[i];
            }
            globalSum.addAndGet(localSum); // атомарное прибавление
            System.out.println("Поток " + getName() +
                    " | Диапазон: [" + startIndex + ", " + endIndex + ")" +
                    " | Локальная сумма: " + localSum);
        }
    }

    public static long calculateSum() throws InterruptedException {
        System.out.println("Количество ядер: " + NUM_THREADS);

        Thread[] threads = new Thread[NUM_THREADS];
        int chunkSize = array.length / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end   = (i == NUM_THREADS - 1) ? array.length : start + chunkSize;
            threads[i] = new SumThread(start, end);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return globalSum.get();
    }

    public static void main(String[] args) throws InterruptedException {
        long sum = calculateSum();
        System.out.println("\nСумма (многопоточно): " + sum);

        // Проверка: сумма 1..N = N*(N+1)/2
        long expected = (long) array.length * (array.length + 1) / 2;
        System.out.println("Ожидаемая сумма:       " + expected);
        System.out.println("Результаты совпадают:  " + (sum == expected));
    }
}