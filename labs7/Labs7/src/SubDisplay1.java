// Первый подкласс для задачи 5
public class SubDisplay1 extends SuperDisplay {
    protected int number; // Защищенное целочисленное поле

    // Конструктор с двумя параметрами
    public SubDisplay1(String text, int number) {
        super(text);
        this.number = number;
    }

    // Переопределенный метод display()
    @Override
    public void display() {
        System.out.println("SubDisplay1: text = \"" + getText() + "\", number = " + number);
    }
}