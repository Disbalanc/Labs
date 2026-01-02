// Подкласс для задачи 2
public class SubAssign extends SuperAssign {
    public int number; // Открытое целочисленное поле

    // Конструктор с двумя параметрами
    public SubAssign(int number, String text) {
        super(text);
        this.number = number;
    }

    // Метод без параметров
    public void assign() {
        super.assign(""); // Очищаем текст
        this.number = 0;
    }

    // Метод с текстовым параметром (переопределение)
    @Override
    public void assign(String text) {
        super.assign(text);
        // Число не меняется
    }

    // Метод с целочисленным параметром (перегрузка)
    public void assign(int number) {
        this.number = number;
        // Текст не меняется
    }

    // Метод с текстовым и целочисленным параметром
    public void assign(String text, int number) {
        super.assign(text);
        this.number = number;
    }

    // Геттер для числа
    public int getNumber() {
        return number;
    }
}