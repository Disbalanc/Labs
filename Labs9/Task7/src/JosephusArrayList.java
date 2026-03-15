

import java.util.*;

public class JosephusArrayList {
    public static int solve(int n) {
        List<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        int index = 0;
        while (circle.size() > 1) {
            index = (index + 1) % circle.size();
            circle.remove(index);
            if (index == circle.size()) {
                index = 0;
            }
        }
        return circle.get(0);
    }

    public static void main(String[] args) {
        int n = 100000;

        long start = System.currentTimeMillis();
        int survivor = solve(n);
        long end = System.currentTimeMillis();

        System.out.println("ArrayList: Последний оставшийся = " + survivor);
        System.out.println("Время: " + (end - start) + " мс");
    }
}