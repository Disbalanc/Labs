// Второй подкласс для задачи 5
public class SubDisplay2 extends SuperDisplay {
    protected char symbol; // Защищенное символьное поле

    // Конструктор с двумя параметрами
    public SubDisplay2(String text, char symbol) {
        super(text);
        this.symbol = symbol;
    }

    // Переопределенный метод display()
    @Override
    public void display() {
        System.out.println("SubDisplay2: text = \"" + getText() + "\", symbol = '" + symbol + "'");
    }
}