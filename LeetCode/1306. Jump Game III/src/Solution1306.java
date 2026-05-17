import java.util.*;

public class Solution1306 {

    // Решение через BFS
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int index = queue.poll();

            // Нашли индекс со значением 0
            if (arr[index] == 0) {
                return true;
            }

            // Прыжок вправо: i + arr[i]
            int right = index + arr[index];
            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.offer(right);
            }

            // Прыжок влево: i - arr[i]
            int left = index - arr[index];
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.offer(left);
            }
        }

        return false;
    }

    // Альтернативное решение через DFS (рекурсия)
    public boolean canReachDFS(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }

    private boolean dfs(int[] arr, int index, boolean[] visited) {
        // Выход за границы или уже посещён
        if (index < 0 || index >= arr.length || visited[index]) {
            return false;
        }
        // Нашли 0
        if (arr[index] == 0) {
            return true;
        }

        visited[index] = true;

        return dfs(arr, index + arr[index], visited) ||
                dfs(arr, index - arr[index], visited);
    }

    public static void main(String[] args) {
        Solution1306 sol = new Solution1306();

        // Тест 1: ожидается true
        System.out.println(sol.canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5)); // true

        // Тест 2: ожидается true
        System.out.println(sol.canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0)); // true

        // Тест 3: ожидается false
        System.out.println(sol.canReach(new int[]{3, 0, 2, 1, 2}, 2));        // false

        // DFS версия
        System.out.println("--- DFS ---");
        System.out.println(sol.canReachDFS(new int[]{4, 2, 3, 0, 3, 1, 2}, 5)); // true
        System.out.println(sol.canReachDFS(new int[]{3, 0, 2, 1, 2}, 2));        // false
    }
}