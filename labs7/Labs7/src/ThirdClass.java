// Третий класс для задачи 3 (наследует от SecondClass)
public class ThirdClass extends SecondClass {
    public String text; // Открытое текстовое поле

    // Конструктор с тремя параметрами
    public ThirdClass(int number, char symbol, String text) {
        super(number, symbol);
        this.text = text;
    }

    // Метод с тремя аргументами (перегрузка)
    public void assign(int number, char symbol, String text) {
        super.assign(number, symbol);
        this.text = text;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "ThirdClass{number=" + number + ", symbol='" + symbol + "', text='" + text + "'}";
    }
}