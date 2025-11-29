import java.util.Scanner;

//1216. Two Pawns and One King
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Somewhere on the N × N chessboard, there are one white pawn, one black pawn, and the black King as well. A game is being played according to the usual chess rules* (except the absence of the white King). White is supposed to win, when it manages to promote its pawn to the Queen (even in the case when this queen would be immediately beaten by the next black’s move). Otherwise, Black is considered to be the winner (even if in some position White has no possibilities to move its pawn). Given an initial position, your program is to determine the winner.
//        * Usual chess rules mean exactly the following:
//White moves first.
//In the initial position, pawns are not on the first or the N-th horizontal row.
//White pawn is allowed to move to the fourth horizontal from the second, and black pawn is allowed to move to the (N−3)-rd horizontal row from the (N−1)-st. Pawns can't jump over other pieces.
//Just after the white pawn makes a move from x2 to x4, the black pawn may beat the white pawn en passant, moving from y4 to x3, if y is a vertical line, neighboring to the x-vertical.
//In all other cases, pawns move one field along the vertical and beat one field along the diagonal. King moves one field in any direction.
//If it is not Black’s turn to move, the black King should not be under check, i.e. in the position, when the white pawn could beat it.
//When a black pawn reaches the first horizontal, it should be promoted to the Queen, Rook, Knight or Bishop, according to the wish of the Black player. (But when a white pawn reaches the N-th horizontal, it should be promoted to the Queen and our game immediately finishes).
//Исходные данные
//The first line contains a single integer N, 6 ≤ N ≤ 26. In the second line, the positions of the white pawn, black pawn and black King (in this very order!) are given, separated by one or several white spaces. A description of a position consists of a small Latin letter, which denotes the vertical, and (without a space) an integer number (from 1 to N) denoting the horizontal.
//Результат
//Output a message "WHITE WINS", when White can win the game according to the above described rules, and "BLACK WINS" otherwise.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine(); // consume the rest of the line

        String whiteStr = scanner.next();
        String blackStr = scanner.next();
        String kingStr = scanner.next();

        int w_file = whiteStr.charAt(0) - 'a' + 1;
        int w_rank = Integer.parseInt(whiteStr.substring(1));

        int b_file = blackStr.charAt(0) - 'a' + 1;
        int b_rank = Integer.parseInt(blackStr.substring(1));

        int k_file = kingStr.charAt(0) - 'a' + 1;
        int k_rank = Integer.parseInt(kingStr.substring(1));

        // Check condition A: black pawn capture
        if (Math.abs(b_file - w_file) == 1) {
            for (int r = w_rank; r <= Math.min(N - 1, b_rank - 1); r++) {
                int M_white = movesWhite(w_rank, r, N);
                int M_black = movesBlack(b_rank, r + 1, N);
                if (M_black <= M_white) {
                    System.out.println("BLACK WINS");
                    return;
                }
            }
        }

        // Check condition B: king capture
        for (int r = w_rank; r <= N - 1; r++) {
            int M_white = movesWhite(w_rank, r, N);
            int minDist = Integer.MAX_VALUE;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    int x = w_file + dx;
                    int y = r + dy;
                    if (x < 1 || x > N || y < 1 || y > N) continue;
                    int dist = Math.abs(k_file - x) + Math.abs(k_rank - y);
                    if (dist < minDist) {
                        minDist = dist;
                    }
                }
            }
            if (minDist <= M_white) {
                System.out.println("BLACK WINS");
                return;
            }
        }

        System.out.println("WHITE WINS");
    }

    static int movesWhite(int w0, int r, int N) {
        if (r == w0) return 0;
        if (w0 == 2) {
            if (r == w0 + 1) return 1;
            else return r - w0 - 1;
        } else {
            return r - w0;
        }
    }

    static int movesBlack(int b0, int r, int N) {
        if (r == b0) return 0;
        if (b0 == N - 1) {
            if (r == b0 - 1) return 1;
            else return b0 - r - 1;
        } else {
            return b0 - r;
        }
    }
}