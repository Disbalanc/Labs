// Второй класс для задачи 4 (наследует от ChainFirst)
public class ChainSecond extends ChainFirst {
    public String text; // Открытое текстовое поле

    // Конструктор с двумя параметрами
    public ChainSecond(char symbol, String text) {
        super(symbol);
        this.text = text;
    }

    // Конструктор копии
    public ChainSecond(ChainSecond other) {
        super(other); // Копируем символ из родителя
        this.text = other.text;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "ChainSecond{symbol='" + symbol + "', text='" + text + "'}";
    }
}