import java.util.Arrays;

public class ExamplesLab {

    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК ПРИМЕРОВ 1-14 ===");

        runExample1();
        runExample2();
        runExample3();
        runExample4();
        runExample5();
        runExample6();
        runExample7();

        System.out.println("\nПример 8 (вызов метода с исключением):");
        try { m8(); } catch (Exception e) { System.out.println("Перехвачено в main: " + e); }

        System.out.println("\nПример 9 (return в try):");
        System.out.println("Результат: " + m9());

        System.out.println("\nПример 10 (return в finally):");
        System.out.println("Результат: " + m10());

        runExample11();

        System.out.println("\nПример 12 (IllegalArgumentException):");
        try { m12(null, 0.000001); } catch (Exception e) { System.out.println("Ошибка: " + e.getMessage()); }

        runExample13();
        runExample14(new String[]{"arg1", "arg2"}); // Имитация аргументов

        System.out.println("\n=== ПРИМЕРЫ ЗАВЕРШЕНЫ ===\n");
    }

    public static void runExample1() {
        System.out.println("\nПример 1:");
        try {
            System.out.println("0");
            throw new RuntimeException("Непроверяемая ошибка");
        } catch (RuntimeException e) {
            System.out.println("1 " + e);
        }
        System.out.println("2");
    }

    public static void runExample2() {
        System.out.println("\nПример 2:");
        try {
            System.out.println("0");
            if (true) throw new RuntimeException("Непроверяемая ошибка");
            // System.out.println("1"); // Убрано, так как код недостижим
        } catch (Exception e) {
            System.out.println("2 " + e);
        }
        System.out.println("3");
    }

    public static void runExample3() {
        System.out.println("\nПример 3:");
        try {
            System.out.println("0");
            throw new RuntimeException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        } catch (RuntimeException e) {
            System.out.println("2");
        } catch (Exception e) {
            System.out.println("3");
        }
        System.out.println("4");
    }

    public static void runExample4() {
        System.out.println("\nПример 4:");
        try {
            System.out.println("0");
            throw new RuntimeException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        } catch (Exception e) {
            System.out.println("2");
        } catch (Error e) {
            System.out.println("3");
        }
        System.out.println("4");
    }

    public static void runExample5() {
        System.out.println("\nПример 5 (Исправлен: добавлен catch):");
        try {
            System.out.println("0");
            throw new RuntimeException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        } catch (RuntimeException e) {
            System.out.println("Перехвачено общее исключение, чтобы программа не упала");
        }
        System.out.println("2");
    }

    public static void runExample6() {
        System.out.println("\nПример 6 (Исправлен порядок catch):");
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (ArithmeticException e) {
            System.out.println("1");
        } catch (RuntimeException e) { // Потомок должен быть выше предка (Exception)
            System.out.println("3");
        } catch (Exception e) {
            System.out.println("2");
        }
        System.out.println("4");
    }

    public static void runExample7() {
        System.out.println("\nПример 7:");
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
            // Это исключение НЕ будет поймано следующим catch в этом же блоке
            // throw new ArithmeticException(); 
        } catch (ArithmeticException e) {
            System.out.println("2");
        }
        System.out.println("3");
    }

    public static int m8() {
        try {
            System.out.println("0");
            throw new RuntimeException();
        } finally {
            System.out.println("1");
        }
    }

    public static int m9() {
        try {
            System.out.println("0");
            return 55;
        } finally {
            System.out.println("1");
        }
    }

    public static int m10() {
        try {
            System.out.println("0");
            return 15;
        } finally {
            System.out.println("1");
            return 20;
        }
    }

    public static void runExample11() {
        System.out.println("\nПример 11:");
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
        } finally {
            System.out.println("2");
        }
        System.out.println("3");
    }

    public static void m12(String str, double chislo) {
        if (str == null) throw new IllegalArgumentException("Строка введена неверно");
        if (chislo > 0.001) throw new IllegalArgumentException("Неверное число");
    }

    public static void runExample13() {
        System.out.println("\nПример 13 (имитация ошибки массива):");
        try {
            String[] args = new String[0]; // Пустой массив для провокации ошибки
            int l = args.length;
            int h = 10 / l;
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс не существует");
        }
    }

    public static void m14(int x) throws ArithmeticException {
        int h = 10 / x;
    }

    public static void runExample14(String[] args) {
        System.out.println("\nПример 14:");
        try {
            int l = args.length;
            System.out.println("размер массива= " + l);
            m14(l);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль");
        }
    }
}