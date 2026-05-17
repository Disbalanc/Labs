public class Task2 {

    static class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Поток [" + getName() + "] => " + i);
                try {
                    Thread.sleep(1000); // задержка 1 секунда
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Поток завершил работу.");
        }
    }

    public static void main(String[] args) {
        Thread thread = new CounterThread();
        thread.setName("CounterThread");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Главный поток завершён.");
    }
}