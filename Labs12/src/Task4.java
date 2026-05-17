public class Task4 {

    static class NumberedThread extends Thread {
        private final int number;

        public NumberedThread(int number) {
            super("Thread-" + number);
            this.number = number;
        }

        @Override
        public void run() {
            System.out.println("Поток номер: " + number +
                    " | Имя: " + getName() +
                    " | ID: " + getId());
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        // Создаём потоки
        for (int i = 0; i < 10; i++) {
            threads[i] = new NumberedThread(i + 1);
        }

        // Запускаем потоки
        for (Thread t : threads) {
            t.start();
        }

        // Ожидаем завершения всех потоков
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все 10 потоков завершены.");
    }
}