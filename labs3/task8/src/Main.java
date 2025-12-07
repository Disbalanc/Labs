public class Main {
    public static void main(String[] args) {
        final int SIZE = 10;
        char[] array = new char[SIZE];

        char currentChar = 'A';
        int index = 0;

        // Пропускаем гласные: A, E, I, O, U
        while (index < SIZE) {
            if (currentChar != 'A' && currentChar != 'E' &&
                    currentChar != 'I' && currentChar != 'O' && currentChar != 'U') {
                array[index] = currentChar;
                index++;
            }
            currentChar++;
        }

        // Вывод массива
        System.out.print("Массив согласных букв: ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}