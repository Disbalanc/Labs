// Суперкласс для задачи 1
public class SuperText {
    private String text;

    // Конструктор с текстовым параметром
    public SuperText(String text) {
        this.text = text;
    }

    // Переопределенный метод toString()
    @Override
    public String toString() {
        return "SuperText{text='" + text + "'}";
    }

    // Геттер для текста
    public String getText() {
        return text;
    }
}