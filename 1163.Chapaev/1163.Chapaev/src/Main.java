import java.util.*;
import java.io.*;

//1163. Chapaev
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Legendary divisional commander Vasiliy I. Chapaev was fond of playing this beautiful game with his aide-de-camp Petka during their (scanty) spare time. The game is played as follows. There are eight white and eight red draughts on the board at the beginning of the game. The red side starts the game by making the first turn. The turn consists of selecting an arbitrary draught of player’s colour and pushing it with a flick into a certain direction. Then this draught begins to move in this direction until it completely falls off the board. If it hits or even just touches another draught of any colour during the movement, the latter is immediately removed from the board being considered killed. In any case the moving draught continues its movement without changing its speed or direction. After the moving draught has completed its movement across the board (has fallen off) the other player has to make his turn. If there are no draughts of player’s colour left he is considered to lose the game.
//The historians have a record of an initial position in one of such games. Unfortunately, the result of this game is unknown. Your task is to establish the truth taking for granted that both Chapaev and Petka always used the optimal strategy.
//        Исходные данные
//Each of two lines contains eight pairs of numbers - the coordinates of centres of red and white draughts respectively. The draughts are considered to be cylinders of radius 0.4 and height 0.15. The coordinates are calculated so that the board is a square 8x8 with vertices (0, 0), (0, 8), (8, 0) and (8, 8). There will be no draught that overlaps or touches another one. Also each piece in the initial position is completely contained within the limits of the board.
//Результат
//Output RED or WHITE corresponding to the winning side.

public class Main {
    static double[][] points = new double[16][2];
    static double[][] dist = new double[16][16];
    static double[][] theta = new double[16][16];
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read red pieces
        String line = sc.nextLine();
        String[] tokens = line.split(" ");
        for (int i = 0; i < 8; i++) {
            points[i][0] = Double.parseDouble(tokens[2*i]);
            points[i][1] = Double.parseDouble(tokens[2*i+1]);
        }

        // Read white pieces
        line = sc.nextLine();
        tokens = line.split(" ");
        for (int i = 0; i < 8; i++) {
            points[i+8][0] = Double.parseDouble(tokens[2*i]);
            points[i+8][1] = Double.parseDouble(tokens[2*i+1]);
        }

        // Precompute dist and theta for all pairs
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == j) {
                    dist[i][j] = 0.0;
                    theta[i][j] = 0.0;
                } else {
                    double dx = points[j][0] - points[i][0];
                    double dy = points[j][1] - points[i][1];
                    dist[i][j] = Math.sqrt(dx*dx + dy*dy);
                    theta[i][j] = Math.atan2(dy, dx);
                    if (theta[i][j] < 0) {
                        theta[i][j] += 2 * Math.PI;
                    }
                }
            }
        }

        int states = 1 << 16;
        dp = new int[2][states];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        int initialMask = (1 << 16) - 1;
        int result = dfs(0, initialMask);

        if (result == 1) {
            System.out.println("RED");
        } else {
            System.out.println("WHITE");
        }
    }

    static int dfs(int turn, int mask) {
        if (dp[turn][mask] != -1) {
            return dp[turn][mask];
        }

        // Check if current player has any pieces left
        boolean hasPiece = false;
        if (turn == 0) {
            for (int i = 0; i < 8; i++) {
                if ((mask & (1 << i)) != 0) {
                    hasPiece = true;
                    break;
                }
            }
        } else {
            for (int i = 8; i < 16; i++) {
                if ((mask & (1 << i)) != 0) {
                    hasPiece = true;
                    break;
                }
            }
        }

        if (!hasPiece) {
            return dp[turn][mask] = 0;
        }

        // For each piece A of the current player
        for (int A = 0; A < 16; A++) {
            if ((turn == 0 && A >= 8) || (turn == 1 && A < 8)) {
                continue;
            }
            if ((mask & (1 << A)) == 0) {
                continue;
            }

            List<Double> angles = new ArrayList<>();
            angles.add(0.0);
            angles.add(2 * Math.PI);

            // Gather critical angles from other pieces
            for (int B = 0; B < 16; B++) {
                if (B == A) continue;
                if ((mask & (1 << B)) == 0) continue;

                double d = dist[A][B];
                double thetaAB = theta[A][B];
                double alpha;
                if (d > 0.8) {
                    alpha = Math.asin(0.8 / d);
                } else {
                    alpha = Math.PI / 2;
                }

                double theta1 = thetaAB - alpha;
                double theta2 = thetaAB + alpha;

                // Normalize angles to [0, 2*PI)
                if (theta1 < 0) theta1 += 2 * Math.PI;
                if (theta2 < 0) theta2 += 2 * Math.PI;

                angles.add(theta1);
                angles.add(theta2);
            }

            Collections.sort(angles);

            // Consider midpoints between consecutive angles
            for (int i = 0; i < angles.size() - 1; i++) {
                double mid = (angles.get(i) + angles.get(i+1)) / 2.0;
                int newMask = mask;
                newMask &= ~(1 << A); // Remove piece A

                // Check which other pieces are hit
                for (int B = 0; B < 16; B++) {
                    if (B == A) continue;
                    if ((newMask & (1 << B)) == 0) continue;
                    if (isHit(A, B, mid)) {
                        newMask &= ~(1 << B);
                    }
                }

                int nextTurn = 1 - turn;
                if (dfs(nextTurn, newMask) == 0) {
                    return dp[turn][mask] = 1;
                }
            }
        }

        return dp[turn][mask] = 0;
    }

    static boolean isHit(int A, int B, double phi) {
        double dx = points[B][0] - points[A][0];
        double dy = points[B][1] - points[A][1];
        double dot = dx * Math.cos(phi) + dy * Math.sin(phi);
        if (dot <= 1e-9) {
            return false;
        }
        double dMin = Math.abs(dx * Math.sin(phi) - dy * Math.cos(phi));
        return dMin <= 0.8 + 1e-9;
    }
}