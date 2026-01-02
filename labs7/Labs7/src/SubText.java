// Подкласс для задачи 1
public class SubText extends SuperText {
    private String additionalText;

    // Конструктор с одним параметром (наследует от суперкласса)
    public SubText(String text) {
        super(text);
        this.additionalText = ""; // По умолчанию пустая строка
    }

    // Конструктор с двумя параметрами
    public SubText(String text, String additionalText) {
        super(text);
        this.additionalText = additionalText;
    }

    // Переопределенный метод toString()
    @Override
    public String toString() {
        String superText = super.getText();
        return "SubText{text='" + superText + "', additionalText='" + additionalText + "'}";
    }
}