public class Task6 {
    private int max;
    private int min;

    // Конструктор без аргументов
    public Task6() {
        setValues();
    }

    // Конструктор с одним аргументом
    public Task6(int a) {
        setValues(a);
    }

    // Конструктор с двумя аргументами
    public Task6(int a, int b) {
        setValues(a, b);
    }

    // Метод без аргументов
    public void setValues() {
        this.max = 0;
        this.min = 0;
    }

    // Метод с одним аргументом
    public void setValues(int a) {
        this.max = a;
        this.min = a;
    }

    // Метод с двумя аргументами
    public void setValues(int a, int b) {
        // Сравниваем переданные аргументы между собой
        if (a >= b) {
            this.max = a;
            this.min = b;
        } else {
            this.max = b;
            this.min = a;
        }

        // Теперь сравниваем с текущими значениями полей
        if (this.max < max) { // если текущий max больше, чем вычисленный
            this.max = max;
        }
        if (this.min > min) { // если текущий min меньше, чем вычисленный
            this.min = min;
        }
    }

    // Метод для отображения значений полей
    public void displayValues() {
        System.out.println("Max: " + max + ", Min: " + min);
    }
}