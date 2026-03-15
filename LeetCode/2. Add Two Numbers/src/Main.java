public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("=== LeetCode 2: Add Two Numbers ===\n");

        // Тест 1: 342 + 465 = 807
        ListNode l1 = ListNode.fromArray(new int[]{2, 4, 3});
        ListNode l2 = ListNode.fromArray(new int[]{5, 6, 4});
        ListNode result = sol.addTwoNumbers(l1, l2);
        System.out.println("Тест 1:");
        System.out.println("  l1     = [2,4,3] (342)");
        System.out.println("  l2     = [5,6,4] (465)");
        System.out.println("  Ответ  = " + result + " (ожидается [7,0,8] → 807)");

        // Тест 2: 0 + 0 = 0
        l1 = ListNode.fromArray(new int[]{0});
        l2 = ListNode.fromArray(new int[]{0});
        result = sol.addTwoNumbers(l1, l2);
        System.out.println("\nТест 2:");
        System.out.println("  l1     = [0]");
        System.out.println("  l2     = [0]");
        System.out.println("  Ответ  = " + result + " (ожидается [0])");

        // Тест 3: 9999999 + 9999 = 10009998
        l1 = ListNode.fromArray(new int[]{9, 9, 9, 9, 9, 9, 9});
        l2 = ListNode.fromArray(new int[]{9, 9, 9, 9});
        result = sol.addTwoNumbers(l1, l2);
        System.out.println("\nТест 3:");
        System.out.println("  l1     = [9,9,9,9,9,9,9] (9999999)");
        System.out.println("  l2     = [9,9,9,9]       (9999)");
        System.out.println("  Ответ  = " + result + " (ожидается [8,9,9,9,0,0,0,1] → 10009998)");

        // Тест 4: 99 + 1 = 100
        l1 = ListNode.fromArray(new int[]{9, 9});
        l2 = ListNode.fromArray(new int[]{1});
        result = sol.addTwoNumbers(l1, l2);
        System.out.println("\nТест 4:");
        System.out.println("  l1     = [9,9] (99)");
        System.out.println("  l2     = [1]   (1)");
        System.out.println("  Ответ  = " + result + " (ожидается [0,0,1] → 100)");

        System.out.println("\n✓ Все тесты выполнены!");
    }
}