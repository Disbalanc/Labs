public class Example5 {
    private static int callCount = 0;

    public static void main(String[] args) {
        int n = 5;
        System.out.println("=== Дерево рекурсивных вызовов fact(" + n + ") ===\n");
        int result = factWithTrace(n, 0);
        System.out.println("\nРезультат: fact(" + n + ") = " + result);
        System.out.println("Всего вызовов: " + callCount);
    }

    // Исходный метод из примера
    private static int fact(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fact(n - 2) + fact(n - 1);
        }
    }

    // Метод с трассировкой дерева вызовов
    private static int factWithTrace(int n, int depth) {
        callCount++;
        int currentCall = callCount;
        String indent = "│ ".repeat(depth);

        System.out.println(indent + "┌ ВХОД fact(" + n + ") [вызов #" + currentCall + "]");

        int result;
        if (n == 0) {
            result = 0;
            System.out.println(indent + "│ Базовый случай: fact(0) = 0");
        } else if (n == 1) {
            result = 1;
            System.out.println(indent + "│ Базовый случай: fact(1) = 1");
        } else {
            System.out.println(indent + "│ Вычисляем fact(" + (n - 2) + ") + fact(" + (n - 1) + ")");
            System.out.println(indent + "│ Вызов LEFT: fact(" + (n - 2) + ")");
            int left = factWithTrace(n - 2, depth + 1);

            System.out.println(indent + "│ Вызов RIGHT: fact(" + (n - 1) + ")");
            int right = factWithTrace(n - 1, depth + 1);

            result = left + right;
            System.out.println(indent + "│ fact(" + n + ") = " + left + " + " + right + " = " + result);
        }

        System.out.println(indent + "└ ВЫХОД fact(" + n + ") = " + result + " [вызов #" + currentCall + "]");
        return result;
    }
}