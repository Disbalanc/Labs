import java.util.concurrent.Semaphore;

public class Task3 {

    // Семафоры для синхронизации порядка вывода
    private static final Semaphore oddSemaphore  = new Semaphore(1); // нечётные начинают
    private static final Semaphore evenSemaphore = new Semaphore(0); // чётные ждут

    static class OddThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i += 2) {
                try {
                    oddSemaphore.acquire();
                    System.out.println("Нечётный поток [" + getName() + "] => " + i);
                    evenSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class EvenThread extends Thread {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    evenSemaphore.acquire();
                    System.out.println("Чётный поток  [" + getName() + "] => " + i);
                    oddSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread odd  = new OddThread();
        Thread even = new EvenThread();

        odd.setName("ODD");
        even.setName("EVEN");

        odd.start();
        even.start();

        try {
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Все потоки завершены.");
    }
}