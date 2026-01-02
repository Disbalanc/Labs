// Первый класс для задачи 4
public class ChainFirst {
    public char symbol; // Открытое символьное поле

    // Конструктор с параметром
    public ChainFirst(char symbol) {
        this.symbol = symbol;
    }

    // Конструктор копии
    public ChainFirst(ChainFirst other) {
        this.symbol = other.symbol;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "ChainFirst{symbol='" + symbol + "'}";
    }
}