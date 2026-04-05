import java.util.*;

public class MedianOfTwoSortedArrays {

    /**
     * Решение 1: Binary Search (Оптимальное) - O(log(min(m,n)))
     * Используем бинарный поиск по меньшему массиву
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Убедимся, что nums1 - меньший массив
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right = m;

        while (left <= right) {
            // Разделение nums1
            int partition1 = (left + right) / 2;
            // Разделение nums2
            int partition2 = (m + n + 1) / 2 - partition1;

            // Граничные значения слева и справа от разделов
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Проверяем правильность разделения
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Нашли правильное разделение
                if ((m + n) % 2 == 0) {
                    // Чётное количество элементов
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    // Нечётное количество элементов
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                // Слишком далеко вправо в nums1
                right = partition1 - 1;
            } else {
                // Слишком далеко влево в nums1
                left = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Массивы не отсортированы");
    }

    /**
     * Решение 2: Merge и поиск медианы - O(m+n)
     * Проще для понимания, но медленнее
     */
    public static double findMedianSortedArraysMerge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        // Слияние двух отсортированных массивов
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Копируем оставшиеся элементы
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        // Находим медиану
        int totalLength = m + n;
        if (totalLength % 2 == 0) {
            return (merged[totalLength / 2 - 1] + merged[totalLength / 2]) / 2.0;
        } else {
            return merged[totalLength / 2];
        }
    }

    /**
     * Решение 3: Без полного слияния - O(m+n)
     * Оптимизация по памяти - не создаём весь массив
     */
    public static double findMedianSortedArraysOptimized(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        int medianIndex = totalLength / 2;

        int i = 0, j = 0;
        int current = 0, previous = 0;

        // Идём до медианы
        for (int count = 0; count <= medianIndex; count++) {
            previous = current;

            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                current = nums1[i++];
            } else {
                current = nums2[j++];
            }
        }

        if (totalLength % 2 == 0) {
            return (previous + current) / 2.0;
        } else {
            return current;
        }
    }

    /**
     * Решение с визуализацией процесса
     */
    public static double findMedianWithVisualization(int[] nums1, int[] nums2) {
        System.out.println("\n=== Визуализация поиска медианы ===");
        System.out.println("nums1 = " + Arrays.toString(nums1));
        System.out.println("nums2 = " + Arrays.toString(nums2));

        // Слияние с визуализацией
        int m = nums1.length;
        int n = nums2.length;
        List<Integer> merged = new ArrayList<>();

        int i = 0, j = 0;

        System.out.println("\nПроцесс слияния:");
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged.add(nums1[i]);
                System.out.printf("  Добавляем nums1[%d] = %d%n", i, nums1[i]);
                i++;
            } else {
                merged.add(nums2[j]);
                System.out.printf("  Добавляем nums2[%d] = %d%n", j, nums2[j]);
                j++;
            }
        }

        while (i < m) {
            merged.add(nums1[i]);
            System.out.printf("  Добавляем nums1[%d] = %d%n", i, nums1[i]);
            i++;
        }

        while (j < n) {
            merged.add(nums2[j]);
            System.out.printf("  Добавляем nums2[%d] = %d%n", j, nums2[j]);
            j++;
        }

        System.out.println("\nОбъединённый массив: " + merged);

        int totalLength = merged.size();
        double median;

        if (totalLength % 2 == 0) {
            int mid1 = totalLength / 2 - 1;
            int mid2 = totalLength / 2;
            median = (merged.get(mid1) + merged.get(mid2)) / 2.0;
            System.out.printf("\nЧётное количество элементов (%d)%n", totalLength);
            System.out.printf("Медиана = (элемент[%d] + элемент[%d]) / 2 = (%d + %d) / 2 = %.5f%n",
                    mid1, mid2, merged.get(mid1), merged.get(mid2), median);
        } else {
            int mid = totalLength / 2;
            median = merged.get(mid);
            System.out.printf("\nНечётное количество элементов (%d)%n", totalLength);
            System.out.printf("Медиана = элемент[%d] = %.5f%n", mid, median);
        }

        return median;
    }

    /**
     * Тестирование
     */
    public static void runTests() {
        System.out.println("\n=== Автоматические тесты ===\n");

        int[][][] testCases = {
                {{1, 3}, {2}},
                {{1, 2}, {3, 4}},
                {{0, 0}, {0, 0}},
                {{}, {1}},
                {{2}, {}},
                {{1, 3, 5, 7}, {2, 4, 6}},
                {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}},
                {{1}, {2, 3, 4, 5, 6}},
                {{1, 3, 5}, {2, 4, 6}},
                {{100000}, {100001}}
        };

        double[] expected = {2.0, 2.5, 0.0, 1.0, 2.0, 4.0, 5.5, 3.5, 3.5, 100000.5};

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] nums1 = testCases[i][0];
            int[] nums2 = testCases[i][1];

            double result = findMedianSortedArrays(nums1, nums2);
            boolean isCorrect = Math.abs(result - expected[i]) < 0.00001;

            if (isCorrect) passed++;

            System.out.printf("Тест %2d: nums1=%-20s nums2=%-20s%n",
                    i + 1, Arrays.toString(nums1), Arrays.toString(nums2));
            System.out.printf("         Результат: %.5f | Ожидалось: %.5f | %s%n",
                    result, expected[i], isCorrect ? "✓" : "✗");
        }

        System.out.printf("%nПройдено: %d/%d тестов%n", passed, testCases.length);
    }

    /**
     * Сравнение производительности
     */
    public static void performanceComparison() {
        System.out.println("\n=== Сравнение производительности ===\n");

        Random random = new Random(42);

        int[][] sizes = {{10, 10}, {100, 100}, {1000, 1000}, {500, 1500}};
        String[] descriptions = {
                "Малые массивы (10+10)",
                "Средние массивы (100+100)",
                "Большие массивы (1000+1000)",
                "Разные размеры (500+1500)"
        };

        for (int i = 0; i < sizes.length; i++) {
            int[] nums1 = generateSortedArray(sizes[i][0], random);
            int[] nums2 = generateSortedArray(sizes[i][1], random);

            System.out.println(descriptions[i] + ":");

            // Binary Search
            long start = System.nanoTime();
            double result1 = findMedianSortedArrays(nums1, nums2);
            long time1 = System.nanoTime() - start;

            // Merge
            start = System.nanoTime();
            double result2 = findMedianSortedArraysMerge(nums1, nums2);
            long time2 = System.nanoTime() - start;

            // Optimized
            start = System.nanoTime();
            double result3 = findMedianSortedArraysOptimized(nums1, nums2);
            long time3 = System.nanoTime() - start;

            System.out.printf("  Binary Search:    %8d нс | Медиана: %.5f%n", time1, result1);
            System.out.printf("  Merge:            %8d нс | Медиана: %.5f%n", time2, result2);
            System.out.printf("  Optimized:        %8d нс | Медиана: %.5f%n", time3, result3);
            System.out.println();
        }
    }

    private static int[] generateSortedArray(int size, Random random) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Интерактивный режим
     */
    public static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Интерактивный режим ===");
        System.out.println("Введите два отсортированных массива");
        System.out.println("Введите 'exit' для выхода\n");

        while (true) {
            System.out.print("Введите первый массив (например: 1 3 5): ");
            String input1 = scanner.nextLine().trim();

            if (input1.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            System.out.print("Введите второй массив (например: 2 4 6): ");
            String input2 = scanner.nextLine().trim();

            if (input2.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            try {
                int[] nums1 = parseArray(input1);
                int[] nums2 = parseArray(input2);

                // Проверка на отсортированность
                if (!isSorted(nums1) || !isSorted(nums2)) {
                    System.out.println("Внимание: Массивы должны быть отсортированы!");
                    System.out.println("Сортируем автоматически...");
                    Arrays.sort(nums1);
                    Arrays.sort(nums2);
                }

                System.out.println("\nВыберите метод:");
                System.out.println("1. Binary Search (оптимальный)");
                System.out.println("2. Merge (простой)");
                System.out.println("3. С визуализацией");
                System.out.print("Ваш выбор: ");

                String choice = scanner.nextLine();

                double result = 0;
                long startTime = System.nanoTime();

                switch (choice) {
                    case "1":
                        result = findMedianSortedArrays(nums1, nums2);
                        break;
                    case "2":
                        result = findMedianSortedArraysMerge(nums1, nums2);
                        break;
                    case "3":
                        result = findMedianWithVisualization(nums1, nums2);
                        break;
                    default:
                        System.out.println("Неверный выбор!\n");
                        continue;
                }

                long endTime = System.nanoTime();

                if (!choice.equals("3")) {
                    System.out.println("\nМедиана: " + result);
                }
                System.out.println("Время выполнения: " + (endTime - startTime) + " нс\n");

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
    }

    private static int[] parseArray(String input) {
        if (input.trim().isEmpty()) {
            return new int[0];
        }
        String[] parts = input.trim().split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Примеры из условия
        System.out.println("\n=== Примеры из задачи ===");

        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println("\nExample 1:");
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_1) +
                ", nums2 = " + Arrays.toString(nums2_1));
        System.out.println("Output: " + findMedianSortedArrays(nums1_1, nums2_1));
        System.out.println("Explanation: merged = [1,2,3], медиана = 2");

        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("\nExample 2:");
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_2) +
                ", nums2 = " + Arrays.toString(nums2_2));
        System.out.println("Output: " + findMedianSortedArrays(nums1_2, nums2_2));
        System.out.println("Explanation: merged = [1,2,3,4], медиана = (2+3)/2 = 2.5");

        // Демонстрация с визуализацией
        System.out.println("\n" + "=".repeat(50));
        findMedianWithVisualization(new int[]{1, 3, 5}, new int[]{2, 4, 6});

        // Тесты
        runTests();

        // Сравнение производительности
        performanceComparison();

        // Интерактивный режим
        interactiveMode();
    }
}