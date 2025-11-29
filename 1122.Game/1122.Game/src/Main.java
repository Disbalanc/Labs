import java.util.*;

//1122. Игра
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Нам, в СКБ Контур приходится много работать. Поэтому иногда бывает не грех и отдохнуть, поиграть. Рассмотрим, например, вот такую очень известную игру для одного человека.
//Problem illustration
//Есть поле размером 4×4. На нём расположены фишки, у которых одна сторона окрашена в белый цвет, а другая — в чёрный. Какие-то из них в данный момент лежат белой стороной вверх, какие-то вниз. За один ход можно перевернуть одну фишку и все соседние по горизонтали или вертикали с ней. Целью игры является позиция, в которой все фишки лежат одной стороной вверх (все чёрные либо все белые).
//Problem illustration
//Естественно, такая игра быстро надоедает, неожиданных и нетипичных позиций становится всё меньше и меньше. Поэтому сейчас в СКБ Контур больше распространён модифицированный вариант игры. В этом варианте ход заключается в перекладывании фиксированной комбинации фишек, попадающей в квадрат 3×3. Например, ход может заключаться в переворачивании всех соседей выбранной фишки по диагонали.
//        Problem illustration
//Комбинация выбирается произвольной; её можно задать в виде поля 3×3, где центральная клетка соответствует той, в которую делается ход. Например на рисунке слева вверху показана комбинация для обычной игры, а внизу — для описанной в предыдущем абзаце. Заметим, что комбинация не обязана быть симметричной. Ход делается всегда в одну из клеток игрового поля (то есть центральная клетка квадрата 3×3, определяющего ход, выбирается из клеток поля). Предписания комбинации на переворачивание фишек, попадающих за пределы поля, игнорируются.
//Для такой игры бывает неплохо знать, можно ли вообще перевернуть все фишки одной стороной вверх, и если можно, то за какое минимальное число ходов. Вот вам и предстоит сделать программу, которая могла бы дать ответ на эти вопросы.
//        Исходные данные
//В первых четырёх строках описывается начальное расположение фишек. Символ 'W' — обозначает фишку, лежащую вверх белой стороной, символ 'B' — чёрной. В следующих трёх строках описывается ход — комбинация переворачиваемых фишек. '0' — фишку переворачивать не надо, '1' — надо.
//        Результат
//Если добиться нужного расположения фишек невозможно, то выведите надпись «Impossible», иначе выведите минимальное количество ходов, за которое это расположение достигается.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the initial board state
        String[] board = new String[4];
        for (int i = 0; i < 4; i++) {
            board[i] = scanner.next();
        }

        // Read the move pattern
        String[] movePattern = new String[3];
        for (int i = 0; i < 3; i++) {
            movePattern[i] = scanner.next();
        }

        // Convert the board to a state integer
        int initialState = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i].charAt(j) == 'B') {
                    initialState |= (1 << (i * 4 + j));
                }
            }
        }

        // Precompute masks for each possible move position
        int[] masks = new int[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int mask = 0;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (movePattern[r].charAt(c) == '1') {
                            int ni = i + (r - 1);
                            int nj = j + (c - 1);
                            if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4) {
                                int bitIndex = ni * 4 + nj;
                                mask |= (1 << bitIndex);
                            }
                        }
                    }
                }
                masks[i * 4 + j] = mask;
            }
        }

        // BFS initialization
        int totalStates = 65536;
        int[] dist = new int[totalStates];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        dist[initialState] = 0;
        queue.add(initialState);

        // BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int k = 0; k < 16; k++) {
                int v = u ^ masks[k];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        // Check target states: all white (0) and all black (65535)
        int target1 = 0;
        int target2 = 65535;
        int result1 = dist[target1];
        int result2 = dist[target2];

        if (result1 == -1 && result2 == -1) {
            System.out.println("Impossible");
        } else if (result1 == -1) {
            System.out.println(result2);
        } else if (result2 == -1) {
            System.out.println(result1);
        } else {
            System.out.println(Math.min(result1, result2));
        }

        scanner.close();
    }
}