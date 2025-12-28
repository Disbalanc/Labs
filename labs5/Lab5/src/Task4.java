public class Task4 {
    private char character;
    private int number;

    // Конструктор с двумя аргументами
    public Task4(int num, char ch) {
        this.number = num;
        this.character = ch;
    }

    // Конструктор с одним аргументом double
    public Task4(double value) {
        // Целая часть определяет код символа
        int code = (int) value;
        this.character = (char) code;

        // Дробная часть (только десятые и сотые) определяет целочисленное поле
        double fractional = value - code; // получаем дробную часть
        fractional = fractional * 100; // умножаем на 100
        this.number = (int) fractional; // берем целую часть
    }

    // Метод для отображения значений
    public void displayValues() {
        System.out.println("Символ: " + character + " (код: " + (int) character + ")");
        System.out.println("Число: " + number);
    }

    // Пример использования
    public static void main(String[] args) {
        Task4 obj1 = new Task4(10, 'B');
        Task4 obj2 = new Task4(65.1267);

        obj1.displayValues();
        obj2.displayValues(); // Символ 'A', число 12
    }
}