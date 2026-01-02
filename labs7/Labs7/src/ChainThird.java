// Третий класс для задачи 4 (наследует от ChainSecond)
public class ChainThird extends ChainSecond {
    public int number; // Открытое целочисленное поле

    // Конструктор с тремя параметрами
    public ChainThird(char symbol, String text, int number) {
        super(symbol, text);
        this.number = number;
    }

    // Конструктор копии
    public ChainThird(ChainThird other) {
        super(other); // Копируем символ и текст из родителя
        this.number = other.number;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "ChainThird{symbol='" + symbol + "', text='" + text + "', number=" + number + "}";
    }
}