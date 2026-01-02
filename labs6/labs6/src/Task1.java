// Задача 1: Класс с перегруженным методом присваивания
public class Task1 {
    public static class TextCharContainer {
        private char charField;
        private String textField;

        public void assignValue(char ch) {
            this.charField = ch;
            System.out.println("  Символьное поле: '" + ch + "'");
        }

        public void assignValue(String text) {
            this.textField = text;
            System.out.println("  Текстовое поле: \"" + text + "\"");
        }

        public void assignValue(char[] chars) {
            if (chars.length == 1) {
                assignValue(chars[0]);
            } else {
                assignValue(new String(chars));
            }
        }
    }
}