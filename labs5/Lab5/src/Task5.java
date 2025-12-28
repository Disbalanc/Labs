public class Task5 {
    private int value;

    // Конструктор без аргументов
    public Task5() {
        setValue();
    }

    // Конструктор с аргументом
    public Task5(int val) {
        setValue(val);
    }

    // Метод без аргументов
    public void setValue() {
        this.value = 0;
    }

    // Метод с аргументом
    public void setValue(int val) {
        if (val > 100) {
            this.value = 100;
        } else {
            this.value = val;
        }
    }

    // Метод для проверки значения поля
    public int getValue() {
        return value;
    }

    // Метод для отображения значения
    public void displayValue() {
        System.out.println("Значение поля: " + value);
    }

    // Пример использования
    public static void main(String[] args) {
        Task5 obj1 = new Task5();
        Task5 obj2 = new Task5(50);
        Task5 obj3 = new Task5(150);

        obj1.displayValue(); // 0
        obj2.displayValue(); // 50
        obj3.displayValue(); // 100

        obj1.setValue(75);
        obj1.displayValue(); // 75

        obj2.setValue();
        obj2.displayValue(); // 0
    }
}