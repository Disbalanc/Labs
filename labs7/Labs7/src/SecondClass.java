// Второй класс для задачи 3 (наследует от FirstClass)
public class SecondClass extends FirstClass {
    public char symbol; // Открытое символьное поле

    // Конструктор с двумя параметрами
    public SecondClass(int number, char symbol) {
        super(number);
        this.symbol = symbol;
    }

    // Метод с двумя параметрами (перегрузка)
    public void assign(int number, char symbol) {
        super.assign(number);
        this.symbol = symbol;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "SecondClass{number=" + number + ", symbol='" + symbol + "'}";
    }
}