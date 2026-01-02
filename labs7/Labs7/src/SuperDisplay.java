// Суперкласс для задачи 5
public class SuperDisplay {
    private String text;

    // Конструктор с текстовым параметром
    public SuperDisplay(String text) {
        this.text = text;
    }

    // Метод для отображения названия класса и значения поля
    public void display() {
        System.out.println("SuperDisplay: text = \"" + text + "\"");
    }

    // Геттер для текста
    public String getText() {
        return text;
    }
}