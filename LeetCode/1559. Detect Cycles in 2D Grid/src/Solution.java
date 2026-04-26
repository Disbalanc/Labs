public class Solution {

    // Направления: вверх, вниз, влево, вправо
    private static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};

    private int m, n;
    private char[][] grid;
    private boolean[][] visited;

    public boolean containsCycle(char[][] grid) {
        this.grid    = grid;
        this.m       = grid.length;
        this.n       = grid[0].length;
        this.visited = new boolean[m][n];

        // Запускаем DFS из каждой непосещённой клетки
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // prevRow, prevCol = -1 означает «нет предыдущей клетки»
                    if (dfs(i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * DFS-обход.
     *
     * @param row     текущая строка
     * @param col     текущий столбец
     * @param prevRow строка предыдущей клетки (откуда пришли)
     * @param prevCol столбец предыдущей клетки
     * @return true, если найден цикл
     */
    private boolean dfs(int row, int col, int prevRow, int prevCol) {
        visited[row][col] = true;

        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Выход за границы сетки
            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                continue;
            }

            // Клетка с другим символом — пропускаем
            if (grid[newRow][newCol] != grid[row][col]) {
                continue;
            }

            // Не возвращаемся в клетку, из которой пришли
            if (newRow == prevRow && newCol == prevCol) {
                continue;
            }

            // Если соседняя клетка уже посещена — нашли цикл!
            if (visited[newRow][newCol]) {
                return true;
            }

            // Рекурсивный вызов
            if (dfs(newRow, newCol, row, col)) {
                return true;
            }
        }

        return false;
    }

    // ----------------------------------------------------------------
    // Точка входа для тестирования
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Пример 1 → ожидаем true
        char[][] grid1 = {
                {'a','a','a','a'},
                {'a','b','b','a'},
                {'a','b','b','a'},
                {'a','a','a','a'}
        };
        System.out.println("Пример 1: " + sol.containsCycle(grid1)); // true

        // Пример 2 → ожидаем true
        char[][] grid2 = {
                {'c','c','c','a'},
                {'c','d','c','c'},
                {'c','c','e','c'},
                {'f','c','c','c'}
        };
        System.out.println("Пример 2: " + sol.containsCycle(grid2)); // true

        // Пример 3 → ожидаем false
        char[][] grid3 = {
                {'a','b','b'},
                {'b','z','b'},
                {'b','b','a'}
        };
        System.out.println("Пример 3: " + sol.containsCycle(grid3)); // false
    }
}