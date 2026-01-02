// Первый суперкласс для задачи 3
public class FirstClass {
    public int number; // Открытое целочисленное поле

    // Конструктор с одним параметром
    public FirstClass(int number) {
        this.number = number;
    }

    // Метод с одним параметром для присваивания значения полю
    public void assign(int number) {
        this.number = number;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "FirstClass{number=" + number + "}";
    }
}