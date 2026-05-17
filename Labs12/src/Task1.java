import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task1 {

    static class TimeThread extends Thread {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        public TimeThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) {
                System.out.println("Поток: " + getName() +
                        " | Время: " + LocalTime.now().format(formatter));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new TimeThread("Thread-Alpha");
        Thread t2 = new TimeThread("Thread-Beta");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Все потоки завершены.");
    }
}