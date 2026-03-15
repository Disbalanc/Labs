import java.util.*;

public class JosephusComparison {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000, 100000};

        System.out.printf("%-10s %-15s %-15s%n", "N", "ArrayList(мс)", "LinkedList(мс)");
        System.out.println("-------------------------------------------");

        for (int n : sizes) {
            long t1 = measureTime(new ArrayList<>(), n);
            long t2 = measureTime(new LinkedList<>(), n);
            System.out.printf("%-10d %-15d %-15d%n", n, t1, t2);
        }
    }

    private static long measureTime(List<Integer> circle, int n) {
        for (int i = 1; i <= n; i++) circle.add(i);

        long start = System.currentTimeMillis();
        int index = 0;
        while (circle.size() > 1) {
            index = (index + 1) % circle.size();
            circle.remove(index);
            if (index == circle.size()) index = 0;
        }
        return System.currentTimeMillis() - start;
    }
}