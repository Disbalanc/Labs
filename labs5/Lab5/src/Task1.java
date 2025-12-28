public class Task1 {
    private char symbol;

    // Метод для присвоения значения полю
    public void setSymbol(char s) {
        symbol = s;
    }

    // Метод, возвращающий код символа
    public int getCode() {
        return (int) symbol;
    }

    // Метод для вывода символа и его кода
    public void printSymbolAndCode() {
        System.out.println("Символ: " + symbol);
        System.out.println("Код символа: " + (int) symbol);
    }

    // Пример использования
    public static void main(String[] args) {
        Task1 obj = new Task1();
        obj.setSymbol('A');
        System.out.println("Код символа: " + obj.getCode());
        obj.printSymbolAndCode();
    }
}