// Задача 2: Класс со статическим счетчиком
public class Task2 {
    public static class StaticCounter {
        private static int counter = 0;

        public static void displayAndIncrement() {
            System.out.println("  Текущее значение: " + counter);
            counter++;
        }
    }
}