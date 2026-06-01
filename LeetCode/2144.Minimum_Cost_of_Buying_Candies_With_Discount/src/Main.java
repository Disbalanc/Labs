import java.util.Arrays;

public class Main {

    /**
     * Метод для расчета минимальной стоимости конфет.
     * @param cost Массив цен на конфеты
     * @return Минимальная стоимость
     */
    public static int minimumCost(int[] cost) {
        // 1. Сортируем массив. Теперь самые дорогие конфеты в конце.
        Arrays.sort(cost);

        int totalCost = 0;
        int n = cost.length;
        int count = 0;

        // 2. Идем с конца массива к началу
        for (int i = n - 1; i >= 0; i--) {
            count++;
            // Каждую третью конфету (count = 3, 6, 9...) мы пропускаем,
            // так как она бесплатная. Остальные суммируем.
            if (count % 3 != 0) {
                totalCost += cost[i];
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        System.out.println("=== Тестирование задачи Minimum Cost of Buying Candies ===");

        // Пример 1
        int[] cost1 = {1, 2, 3};
        System.out.println("Пример 1: " + Arrays.toString(cost1));
        System.out.println("Ожидаемый результат: 5, Фактический: " + minimumCost(cost1));

        // Пример 2
        int[] cost2 = {6, 5, 7, 9, 2, 2};
        System.out.println("\nПример 2: " + Arrays.toString(cost2));
        System.out.println("Ожидаемый результат: 23, Фактический: " + minimumCost(cost2));

        // Пример 3
        int[] cost3 = {5, 5};
        System.out.println("\nПример 3: " + Arrays.toString(cost3));
        System.out.println("Ожидаемый результат: 10, Фактический: " + minimumCost(cost3));

        // Дополнительный пример: 7 конфет
        int[] cost4 = {10, 20, 30, 40, 50, 60, 70};
        // Сортировка: 70, 60, (50-бесп), 40, 30, (20-бесп), 10
        // Сумма: 70+60+40+30+10 = 210
        System.out.println("\nПример 4: " + Arrays.toString(cost4));
        System.out.println("Ожидаемый результат: 210, Фактический: " + minimumCost(cost4));
    }
}