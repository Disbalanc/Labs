import java.util.*;

//1884. Путь к универу
//Ограничение времени: 0.5 секунды
//Ограничение памяти: 64 МБ
//Егор очень спешил на пары и уже почти добрался до университета, оставалось только перейти дорогу. Подойдя к краю тротуара, он посмотрел налево и увидел n приближающихся автомобилей. Затем он посмотрел направо и увидел m автомобилей. «Сигануть бы прямо сейчас, но жизнь дороже пар», — подумал Егор.
//В Екатеринбурге правостороннее движение. Все машины едут по дороге со скоростью 20 км/ч. Егор передвигается со скоростью 5 км/ч. Ширина дороги — 4 метра (две полосы по два метра), ширина каждого автомобиля — 2 метра, длина — 5 метров. Егор может начать пересекать дорогу только в той точке, в которой он подошёл к ней. Во время пересечения дороги он может двигаться только строго перпендикулярно ей, не меняя скорости и не останавливаясь.
//Найдите минимальное время, которое нужно подождать Егору, прежде чем начать движение. Гарантируется, что Егор успеет перейти дорогу раньше, чем в области видимости появятся новые автомобили.
//Исходные данные
//В первой строке дано целое число n — количество автомобилей, приближающихся слева (1 ≤ n ≤ 300). Во второй строке записаны целые числа d1, d2, …, dn — расстояния до этих автомобилей в метрах. 1 ≤ di ≤ 10 000; di − di−1 ≥ 5. В третьей строке записано целое число m — количество автомобилей, приближающихся справа (1 ≤ m ≤ 300). В четвёртой строке приведены расстояния до этих автомобилей в том же формате, что и до автомобилей слева.
//Результат
//Выведите минимальное время в секундах, которое нужно подождать Егору, прежде чем начать переходить дорогу. Ответ следует вывести с точностью не менее шести знаков после десятичной точки.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] left = new double[n];
        for (int i = 0; i < n; i++) {
            left[i] = scanner.nextDouble();
        }
        int m = scanner.nextInt();
        double[] right = new double[m];
        for (int i = 0; i < m; i++) {
            right[i] = scanner.nextDouble();
        }

        List<double[]> intervals = new ArrayList<>();

        for (double d : left) {
            double T = 9.0 * d / 50.0;
            double low = T - 1.44;
            double high = T + 0.9;
            if (high > 0) {
                double start = Math.max(0, low);
                if (start < high) {
                    intervals.add(new double[]{start, high});
                }
            }
        }

        for (double d : right) {
            double T = 9.0 * (d - 2) / 50.0;
            double low = T - 2.88;
            double high = T - 0.54;
            if (high > 0) {
                double start = Math.max(0, low);
                if (start < high) {
                    intervals.add(new double[]{start, high});
                }
            }
        }

        if (intervals.isEmpty()) {
            System.out.println("0.000000");
            return;
        }

        intervals.sort(Comparator.comparingDouble(a -> a[0]));
        List<double[]> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            double[] current = intervals.get(i);
            double[] last = merged.get(merged.size()-1);
            if (current[0] < last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(current);
            }
        }

        if (merged.get(0)[0] > 0) {
            System.out.println("0.000000");
        } else {
            System.out.printf("%.6f\n", merged.get(0)[1]);
        }
    }
}