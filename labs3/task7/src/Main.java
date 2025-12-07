public class Main {
    public static void main(String[] args) {
        final int SIZE = 10;
        char[] array = new char[SIZE];

        // Заполнение массива буквами через одну, начиная с 'a'
        char currentChar = 'a';
        for (int i = 0; i < SIZE; i++) {
            array[i] = currentChar;
            currentChar += 2; // Пропускаем одну букву
        }

        // Вывод в прямом порядке
        System.out.print("Массив в прямом порядке: ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // Вывод в обратном порядке
        System.out.print("Массив в обратном порядке: ");
        for (int i = SIZE - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}