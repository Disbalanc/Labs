// Задача 7: Коды символов
public class Task7 {
    public static class CharConverter {
        public static int[] convertToCodes(char[] chars) {
            int[] codes = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                codes[i] = chars[i];
            }
            return codes;
        }
    }
}