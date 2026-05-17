import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Task5 {

    private static final int[] array = new int[1_000_000];
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final AtomicInteger globalMax = new AtomicInteger(Integer.MIN_VALUE);

    static {
        // Инициализируем массив случайными значениями
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1_000_000);
        }
    }

    static class MaxFinderThread extends Thread {
        private final int startIndex;
        private final int endIndex;

        public MaxFinderThread(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex   = endIndex;
        }

        @Override
        public void run() {
            int localMax = Integer.MIN_VALUE;
            for (int i = startIndex; i < endIndex; i++) {
                if (array[i] > localMax) {
                    localMax = array[i];
                }
            }
            // Атомарно обновляем глобальный максимум
            int currentMax;
            do {
                currentMax = globalMax.get();
            } while (localMax > currentMax && !globalMax.compareAndSet(currentMax, localMax));

            System.out.println("Поток " + getName() +
                    " | Диапазон: [" + startIndex + ", " + endIndex + ")" +
                    " | Локальный макс: " + localMax);
        }
    }

    public static int findMax() throws InterruptedException {
        System.out.println("Количество ядер: " + NUM_THREADS);

        Thread[] threads = new Thread[NUM_THREADS];
        int chunkSize = array.length / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end   = (i == NUM_THREADS - 1) ? array.length : start + chunkSize;
            threads[i] = new MaxFinderThread(start, end);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return globalMax.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int max = findMax();
        System.out.println("\nМаксимальный элемент: " + max);

        // Проверка (однопоточный способ)
        int expectedMax = Integer.MIN_VALUE;
        for (int val : array) {
            if (val > expectedMax) expectedMax = val;
        }
        System.out.println("Проверка (однопоточно): " + expectedMax);
        System.out.println("Результаты совпадают: " + (max == expectedMax));
    }
}