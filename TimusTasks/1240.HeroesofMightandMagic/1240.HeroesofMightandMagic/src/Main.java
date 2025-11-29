import java.util.*;
import java.io.*;

//1240. Heroes of Might and Magic
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//In the new version of the famous game “Heroes of Might and Magic” heroes themselves take active part in battles. More of that, hero can defeat some monsters alone, without any supporting army. In this problem you are asked to develop the program which would find the strategy for a mage hero fighting face to face with a pack of monsters.
//Each hero initially has HPH hit points and MPH mana points. Heroes can use different spells. Your hero knows three spells: Lighting Bolt, Teleport and Heal. Each spell costs one mana point.
//Each monster has HPM hit points. Pack of monsters is a single group of several monsters who act as one. Therefore if initially the pack consists of NM monsters, they have NM × HPM hit points. As the battle proceeds, monsters' number of hit points decreases. If monsters have H hit points, that means that the group consists of ceiling(H / HPM) monsters (ceiling is a function that returns the smallest integer number not less its argument).
//The battle runs on a one-dimensional battlefield consisting of N + 1 squares, numbered starting from 0. Your hero resides on the square number 0 and does not move. Monsters initially reside on Nth square and can move. Monsters can move at most V squares a turn.
//The battle consists of turns. First your hero makes a turn, then the monsters, and so on. Monsters' strategy is very easy - they move in the direction of your hero min(V, P - 1) squares where P is the square number where they were in the beginning of their turn. If the monsters are on the square number 1 in the end of the movement, then they strike your hero. If there are K monsters left in a pack, their strike decreases hit points of the hero by K. If your hero has non-positive hit points, then the hero is defeated.
//Your hero's turn is always the casting of some spell. Lighting Bolt spell removes LP hit points from a pack of monsters, where P is the square number on which the monsters reside. Teleport spell moves monsters to any desired square (except 0 where your hero resides). Heal spell adds dH hit points to hero. However, his hit points never exceed HPH, so if after using Heal spell his hit points are greater then HPH, they are decreased to HPH. If your hero has zero mana points and there is at least one monster left in the pack, then the hero is defeated.
//Find the strategy which would allow your hero to defeat monsters. Monsters are defeated if their hit points are non-positive.
//        Исходные данные
//The first line contains positive integers separated by spaces in the following order: N, HPH, MPH, HPM, NM, V, dH. (1 ≤ N ≤ 10, 2 ≤ HPH ≤ 100, 1 ≤ MPH ≤ 50, 1 ≤ HPM ≤ 10, 1 ≤ NM ≤ 10, 1 ≤ V ≤ N, 1 ≤ dH < HPH). The second line contains N integer numbers L1, L2, …, LN (1 ≤ LP ≤ 10), separated by spaces.
//Результат
//If the hero cannot win the battle, write the word DEFEATED. In the other case write the word VICTORIOUS on the first line and then write any sequence of hero's actions that leads to victory, where each line starting from the second one must correspond to one hero's turn. The first character of the line must be one of the following:
//L - Cast Lighting Bolt spell.
//T - Cast Teleport spell.
//        H - Cast Heal spell.
//If the hero casts Teleport spell then T character must be followed by a space and an integer number from 1 to N - the square number where the monsters should be teleported to.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int HPH = sc.nextInt();
        int MPH = sc.nextInt();
        int HPM = sc.nextInt();
        int NM = sc.nextInt();
        int V = sc.nextInt();
        int dH = sc.nextInt();

        int[] L = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            L[i] = sc.nextInt();
        }

        int max_m_hp = NM * HPM;

        boolean[][][][] visited = new boolean[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];
        String[][][][] moveUsed = new String[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];
        int[][][][] parent_hp = new int[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];
        int[][][][] parent_mana = new int[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];
        int[][][][] parent_m_hp = new int[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];
        int[][][][] parent_pos = new int[HPH + 1][MPH + 1][max_m_hp + 1][N + 1];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{HPH, MPH, max_m_hp, N});
        visited[HPH][MPH][max_m_hp][N] = true;
        moveUsed[HPH][MPH][max_m_hp][N] = null;

        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            int hp = s[0], mana = s[1], m_hp = s[2], pos = s[3];

            if (mana >= 1) {
                int damage = L[pos];
                int new_m_hp = m_hp - damage;
                if (new_m_hp <= 0) {
                    List<String> path = new ArrayList<>();
                    path.add("L");
                    int cur_hp = hp, cur_mana = mana, cur_m_hp = m_hp, cur_pos = pos;
                    while (moveUsed[cur_hp][cur_mana][cur_m_hp][cur_pos] != null) {
                        path.add(moveUsed[cur_hp][cur_mana][cur_m_hp][cur_pos]);
                        int prev_hp = parent_hp[cur_hp][cur_mana][cur_m_hp][cur_pos];
                        int prev_mana = parent_mana[cur_hp][cur_mana][cur_m_hp][cur_pos];
                        int prev_m_hp = parent_m_hp[cur_hp][cur_mana][cur_m_hp][cur_pos];
                        int prev_pos = parent_pos[cur_hp][cur_mana][cur_m_hp][cur_pos];
                        cur_hp = prev_hp;
                        cur_mana = prev_mana;
                        cur_m_hp = prev_m_hp;
                        cur_pos = prev_pos;
                    }
                    Collections.reverse(path);
                    System.out.println("VICTORIOUS");
                    for (String move : path) {
                        System.out.println(move);
                    }
                    return;
                } else {
                    int new_pos = pos - Math.min(V, pos - 1);
                    int K = (new_m_hp + HPM - 1) / HPM;
                    int new_hp = hp;
                    if (new_pos == 1) {
                        new_hp = hp - K;
                    }
                    if (new_hp > 0 && new_hp <= HPH && mana - 1 >= 0 && new_m_hp > 0 && new_m_hp <= max_m_hp && new_pos >= 1 && new_pos <= N) {
                        if (!visited[new_hp][mana - 1][new_m_hp][new_pos]) {
                            visited[new_hp][mana - 1][new_m_hp][new_pos] = true;
                            parent_hp[new_hp][mana - 1][new_m_hp][new_pos] = hp;
                            parent_mana[new_hp][mana - 1][new_m_hp][new_pos] = mana;
                            parent_m_hp[new_hp][mana - 1][new_m_hp][new_pos] = m_hp;
                            parent_pos[new_hp][mana - 1][new_m_hp][new_pos] = pos;
                            moveUsed[new_hp][mana - 1][new_m_hp][new_pos] = "L";
                            queue.add(new int[]{new_hp, mana - 1, new_m_hp, new_pos});
                        }
                    }
                }
            }

            if (mana >= 1) {
                for (int new_p = 1; new_p <= N; new_p++) {
                    int new_pos_after_move = new_p - Math.min(V, new_p - 1);
                    int K = (m_hp + HPM - 1) / HPM;
                    int new_hp = hp;
                    if (new_pos_after_move == 1) {
                        new_hp = hp - K;
                    }
                    if (new_hp > 0 && new_hp <= HPH && mana - 1 >= 0 && m_hp > 0 && m_hp <= max_m_hp && new_pos_after_move >= 1 && new_pos_after_move <= N) {
                        if (!visited[new_hp][mana - 1][m_hp][new_pos_after_move]) {
                            visited[new_hp][mana - 1][m_hp][new_pos_after_move] = true;
                            parent_hp[new_hp][mana - 1][m_hp][new_pos_after_move] = hp;
                            parent_mana[new_hp][mana - 1][m_hp][new_pos_after_move] = mana;
                            parent_m_hp[new_hp][mana - 1][m_hp][new_pos_after_move] = m_hp;
                            parent_pos[new_hp][mana - 1][m_hp][new_pos_after_move] = pos;
                            moveUsed[new_hp][mana - 1][m_hp][new_pos_after_move] = "T " + new_p;
                            queue.add(new int[]{new_hp, mana - 1, m_hp, new_pos_after_move});
                        }
                    }
                }
            }

            if (mana >= 1) {
                int new_hp0 = Math.min(hp + dH, HPH);
                int new_pos = pos - Math.min(V, pos - 1);
                int K = (m_hp + HPM - 1) / HPM;
                int new_hp = new_hp0;
                if (new_pos == 1) {
                    new_hp = new_hp0 - K;
                }
                if (new_hp > 0 && new_hp <= HPH && mana - 1 >= 0 && m_hp > 0 && m_hp <= max_m_hp && new_pos >= 1 && new_pos <= N) {
                    if (!visited[new_hp][mana - 1][m_hp][new_pos]) {
                        visited[new_hp][mana - 1][m_hp][new_pos] = true;
                        parent_hp[new_hp][mana - 1][m_hp][new_pos] = hp;
                        parent_mana[new_hp][mana - 1][m_hp][new_pos] = mana;
                        parent_m_hp[new_hp][mana - 1][m_hp][new_pos] = m_hp;
                        parent_pos[new_hp][mana - 1][m_hp][new_pos] = pos;
                        moveUsed[new_hp][mana - 1][m_hp][new_pos] = "H";
                        queue.add(new int[]{new_hp, mana - 1, m_hp, new_pos});
                    }
                }
            }
        }

        System.out.println("DEFEATED");
    }
}