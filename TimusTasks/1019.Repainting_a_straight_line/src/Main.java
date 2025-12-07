import java.util.*;
import java.io.*;

public class Main {
    static class Interval {
        long start, end;
        char color; // 'w' или 'b'

        Interval(long s, long e, char c) {
            start = s;
            end = e;
            color = c;
        }

        long length() {
            return end - start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine().trim());
        final long MAX = 1000000001L; // 10^9 + 1 (полуинтервал [0, 10^9+1))
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0, MAX, 'w'));

        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().trim().split(" ");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            char c = parts[2].charAt(0);

            // Перекрашиваем полуинтервал [a, b)
            List<Interval> newIntervals = new ArrayList<>();

            for (Interval interval : intervals) {
                if (interval.end <= a || interval.start >= b) {
                    // Нет пересечения
                    newIntervals.add(interval);
                } else if (interval.start >= a && interval.end <= b) {
                    // Интервал полностью внутри перекрашиваемого отрезка
                    newIntervals.add(new Interval(interval.start, interval.end, c));
                } else {
                    // Частичное пересечение - разбиваем интервал
                    // Левая часть (до перекрашиваемого отрезка)
                    if (interval.start < a) {
                        newIntervals.add(new Interval(interval.start, a, interval.color));
                    }
                    // Центральная часть (пересечение)
                    long left = Math.max(interval.start, a);
                    long right = Math.min(interval.end, b);
                    if (left < right) {
                        newIntervals.add(new Interval(left, right, c));
                    }
                    // Правая часть (после перекрашиваемого отрезка)
                    if (interval.end > b) {
                        newIntervals.add(new Interval(b, interval.end, interval.color));
                    }
                }
            }

            // Объединяем соседние интервалы одинакового цвета
            intervals = mergeIntervals(newIntervals);
        }

        // Поиск самого длинного белого интервала
        long maxLen = -1;
        long bestStart = 0, bestEnd = 0;

        for (Interval interval : intervals) {
            if (interval.color == 'w') {
                long len = interval.length();
                if (len > maxLen || (len == maxLen && interval.start < bestStart)) {
                    maxLen = len;
                    bestStart = interval.start;
                    bestEnd = interval.end;
                }
            }
        }

        pw.println(bestStart + " " + bestEnd);
        pw.flush();
    }

    // Объединение соседних интервалов одинакового цвета
    static List<Interval> mergeIntervals(List<Interval> list) {
        if (list.isEmpty()) return new ArrayList<>();

        List<Interval> merged = new ArrayList<>();
        Interval current = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            Interval next = list.get(i);
            if (current.color == next.color && current.end == next.start) {
                // Объединяем интервалы
                current = new Interval(current.start, next.end, current.color);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        return merged;
    }
}