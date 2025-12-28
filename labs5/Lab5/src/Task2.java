public class Task2 {
    private char startChar;
    private char endChar;

    // Конструктор для инициализации полей
    public Task2(char start, char end) {
        this.startChar = start;
        this.endChar = end;
    }

    // Метод для вывода символов между startChar и endChar
    public void printCharactersBetween() {
        if (startChar <= endChar) {
            for (char c = startChar; c <= endChar; c++) {
                System.out.print(c + " ");
            }
        } else {
            for (char c = startChar; c >= endChar; c--) {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    // Пример использования
    public static void main(String[] args) {
        Task2 obj = new Task2('A', 'D');
        obj.printCharactersBetween();
    }
}