// Суперкласс для задачи 2
public class SuperAssign {
    private String text;

    // Конструктор с текстовым аргументом
    public SuperAssign(String text) {
        this.text = text;
    }

    // Метод для присваивания значения полю
    public void assign(String text) {
        this.text = text;
    }

    // Метод для получения длины текстовой строки
    public int getLength() {
        return text.length();
    }

    // Геттер для текста
    public String getText() {
        return text;
    }
}