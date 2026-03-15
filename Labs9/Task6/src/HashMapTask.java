

import java.util.*;

public class HashMapTask {
    public static void main(String[] args) {
        // Заполнение HashMap 10 объектами <Integer, String>
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Яблоко");
        map.put(1, "Груша");
        map.put(2, "Апельсин");
        map.put(3, "Киви");
        map.put(4, "Манго");
        map.put(5, "Банан");
        map.put(6, "Виноград");
        map.put(7, "Ананас");
        map.put(8, "Клубника");
        map.put(9, "Черешня");

        System.out.println("=== Все элементы HashMap ===");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("  Ключ: " + entry.getKey()
                    + ", Значение: " + entry.getValue());
        }

        // 1. Найти строки, у которых ключ > 5
        System.out.println("\n=== Строки с ключом > 5 ===");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() > 5) {
                System.out.println("  Ключ: " + entry.getKey()
                        + " → " + entry.getValue());
            }
        }

        // 2. Если ключ = 0, вывести строки через запятую
        System.out.println("\n=== Если ключ = 0, вывести строки через запятую ===");
        if (map.containsKey(0)) {
            StringJoiner joiner = new StringJoiner(", ");
            for (String value : map.values()) {
                joiner.add(value);
            }
            System.out.println("  " + joiner.toString());
        }

        // 3. Перемножить все ключи, где длина строки > 5
        System.out.println("\n=== Произведение ключей, где длина строки > 5 ===");
        long product = 1;
        boolean found = false;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().length() > 5) {
                System.out.println("  Ключ: " + entry.getKey()
                        + ", Строка: \"" + entry.getValue()
                        + "\" (длина=" + entry.getValue().length() + ")");
                product *= entry.getKey();
                found = true;
            }
        }
        if (found) {
            System.out.println("  Произведение ключей: " + product);
        } else {
            System.out.println("  Строк с длиной > 5 не найдено.");
        }
    }
}