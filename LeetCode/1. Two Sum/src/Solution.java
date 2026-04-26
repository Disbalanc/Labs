import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    // ================================================================
    // Подход 1: Грубая сила (Brute Force)
    // Сложность: O(n²) по времени, O(1) по памяти
    // ================================================================
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // По условию задачи всегда есть ровно одно решение,
        // но компилятор требует return вне if
        throw new IllegalArgumentException("Решение не найдено!");
    }

    // ================================================================
    // Подход 2: HashMap (оптимальный) ← РЕКОМЕНДУЕМЫЙ
    // Сложность: O(n) по времени, O(n) по памяти
    //
    // Идея:
    //   Для каждого элемента nums[i] ищем его "дополнение":
    //   complement = target - nums[i]
    //
    //   Если complement уже есть в HashMap → нашли пару!
    //   Иначе → кладём nums[i] и его индекс в HashMap.
    //
    //   HashMap хранит: { значение → индекс }
    // ================================================================
    public int[] twoSum(int[] nums, int target) {
        // Ключ   = значение элемента
        // Значение = индекс этого элемента в массиве
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Проверяем: было ли уже встречено дополнение?
            if (seen.containsKey(complement)) {
                // Индекс дополнения был сохранён раньше
                return new int[]{seen.get(complement), i};
            }

            // Сохраняем текущий элемент и его индекс
            seen.put(nums[i], i);
        }

        throw new IllegalArgumentException("Решение не найдено!");
    }

    // ================================================================
    // Точка входа для тестирования
    // ================================================================
    public static void main(String[] args) {
        Solution sol = new Solution();

        printSeparator("Подход 1: Brute Force O(n²)");

        // Пример 1 → [0, 1]
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("nums   = " + Arrays.toString(nums1));
        System.out.println("target = " + target1);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSumBruteForce(nums1, target1)));

        System.out.println();

        // Пример 2 → [1, 2]
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("nums   = " + Arrays.toString(nums2));
        System.out.println("target = " + target2);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSumBruteForce(nums2, target2)));

        System.out.println();

        // Пример 3 → [0, 1]
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("nums   = " + Arrays.toString(nums3));
        System.out.println("target = " + target3);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSumBruteForce(nums3, target3)));

        // ────────────────────────────────────────────────────────────
        printSeparator("Подход 2: HashMap O(n) ← оптимальный");

        // Пример 1 → [0, 1]
        System.out.println("nums   = " + Arrays.toString(nums1));
        System.out.println("target = " + target1);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSum(nums1, target1)));

        System.out.println();

        // Пример 2 → [1, 2]
        System.out.println("nums   = " + Arrays.toString(nums2));
        System.out.println("target = " + target2);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSum(nums2, target2)));

        System.out.println();

        // Пример 3 → [0, 1]
        System.out.println("nums   = " + Arrays.toString(nums3));
        System.out.println("target = " + target3);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSum(nums3, target3)));

        // ────────────────────────────────────────────────────────────
        printSeparator("Дополнительные тесты");

        // Отрицательные числа
        int[] nums4 = {-3, 4, 3, 90};
        int target4 = 0;
        System.out.println("nums   = " + Arrays.toString(nums4));
        System.out.println("target = " + target4);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSum(nums4, target4)));

        System.out.println();

        // Большие числа
        int[] nums5 = {-1_000_000_000, 1_000_000_000};
        int target5 = 0;
        System.out.println("nums   = " + Arrays.toString(nums5));
        System.out.println("target = " + target5);
        System.out.println("Ответ  = "
                + Arrays.toString(sol.twoSum(nums5, target5)));
    }

    // ================================================================
    // Вспомогательный метод для красивого вывода
    // ================================================================
    private static void printSeparator(String title) {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("  " + title);
        System.out.println("=".repeat(45));
    }
}