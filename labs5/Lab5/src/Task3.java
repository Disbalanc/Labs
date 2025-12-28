public class Task3 {
    private int number1;
    private int number2;

    // Конструктор без аргументов
    public Task3() {
        this.number1 = 0;
        this.number2 = 0;
    }

    // Конструктор с одним аргументом
    public Task3(int num) {
        this.number1 = num;
        this.number2 = num;
    }

    // Конструктор с двумя аргументами
    public Task3(int num1, int num2) {
        this.number1 = num1;
        this.number2 = num2;
    }

    // Метод для отображения значений
    public void displayValues() {
        System.out.println("Number1: " + number1 + ", Number2: " + number2);
    }

    // Пример использования
    public static void main(String[] args) {
        Task3 obj1 = new Task3();
        Task3 obj2 = new Task3(10);
        Task3 obj3 = new Task3(20, 30);

        obj1.displayValues();
        obj2.displayValues();
        obj3.displayValues();
    }
}