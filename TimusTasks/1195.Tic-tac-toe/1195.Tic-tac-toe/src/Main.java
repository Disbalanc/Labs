import java.util.*;

//1195. Крестики-нолики
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Зимние Олимпийские Игры. Соревнования по Крестикам-Ноликам на доске 3×3. В судейскую коллегию поступила недоигранная партия (у обоих участников выкачали слишком много крови на допинг-тесты). К моменту прерывания каждый из игроков сделал ровно по три хода. Судейская коллегия должна определить, как закончится партия при наилучшей игре обоих соперников.
//Так как Крестиков поставлено ровно столько же, сколько и ноликов, то коллегия хотела присудить ничью, но тут Главный судья заметил, что одна из сторон угрожает выиграть партию одним ходом. Ваша задача — определить, какая из сторон выиграет при наилучшей игре каждого игрока. По международным правилам первый ход в партии делают Крестики, а победителем считается игрок, выставивший три своих символа в строке, столбце или диагонали.
//        Исходные данные
//Дана таблица 3×3 из символов X, O (латинские заглавные буквы), отмечающих ходы соответственно крестиков и ноликов, и символов #, отмечающих незанятые пока поля. Таблица содержит ровно три крестика и три нолика. Гарантируется, что партия не завершена, а именно, никакая строка, столбец и диагональ не заполнена целиком ни крестиками, ни ноликами.
//Результат
//В случае выигрыша Крестиков выведите «Crosses win», в случае выигрыша Ноликов — «Ouths win», в случае ничьей — «Draw».

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = new char[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            String line = scanner.next();
            for (int j = 0; j < 3; j++) {
                board[index++] = line.charAt(j);
            }
        }

        List<Integer> free = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] == '#') {
                free.add(i);
            }
        }

        // Check if Crosses can win in one move
        for (int pos : free) {
            board[pos] = 'X';
            if (checkWin(board, 'X')) {
                System.out.println("Crosses win");
                return;
            }
            board[pos] = '#';
        }

        boolean canWin = false;
        boolean canDraw = false;

        for (int c : free) {
            board[c] = 'X';
            List<Integer> remaining = new ArrayList<>();
            for (int f : free) {
                if (f != c) {
                    remaining.add(f);
                }
            }

            boolean nolikWins = false;
            for (int o : remaining) {
                board[o] = 'O';
                if (checkWin(board, 'O')) {
                    nolikWins = true;
                    board[o] = '#';
                    break;
                }
                board[o] = '#';
            }

            if (nolikWins) {
                board[c] = '#';
                continue;
            }

            boolean allWin = true;
            for (int o : remaining) {
                board[o] = 'O';
                int last = -1;
                for (int l : remaining) {
                    if (l != o) {
                        last = l;
                    }
                }
                board[last] = 'X';
                if (!checkWin(board, 'X')) {
                    allWin = false;
                }
                board[last] = '#';
                board[o] = '#';
            }

            if (allWin) {
                canWin = true;
            } else {
                canDraw = true;
            }

            board[c] = '#';
        }

        if (canWin) {
            System.out.println("Crosses win");
        } else if (canDraw) {
            System.out.println("Draw");
        } else {
            System.out.println("Ouths win");
        }
    }

    public static boolean checkWin(char[] board, char player) {
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] line : lines) {
            if (board[line[0]] == player &&
                    board[line[1]] == player &&
                    board[line[2]] == player) {
                return true;
            }
        }
        return false;
    }
}