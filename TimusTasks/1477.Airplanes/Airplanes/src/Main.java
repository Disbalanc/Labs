import java.util.Scanner;

//1477. Самолёты
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Вы, вероятно, бывали в аэропортах, в которых самолёты взлетают с интервалом в одну минуту или даже чаще. А вы не задумывались, сколько самолётов одновременно находится в воздухе? А в масштабах целого земного шара?
//        Будем считать, что Земля — идеальный шар с центром в точке (0, 0, 0) радиусом 6370 километров. Большинство пассажирских самолётов летает на высоте не более 15 километров. Если бы вам удалось посмотреть на земной шар со стороны, самолёты выглядели бы точками на его поверхности. Пусть в какой-то момент времени в воздухе находится N самолётов. Самолёт с номером i находится в точке пересечения земной сферы с лучом, исходящим из начала координат по направляющему вектору (Xi, Yi, Zi). Над каждой точкой земной поверхности находится не более одного самолёта. Ваша задача — посчитать, какое максимальное количество самолётов вы могли бы увидеть, находясь на очень большом расстоянии от Земли. С такого расстояния вы можете наблюдать некоторую открытую полусферу Земли.
//Исходные данные
//В первой строке находится целое число N (1 ≤ N ≤ 150). Далее в N строках задаются самолёты тройками целых чисел Xi, Yi, Zi. Все числа не превосходят по модулю 600. Хотя бы одно число в каждой тройке не равно 0.
//Результат
//Программа должна найти наибольшее количество самолётов, которое вы можете увидеть в описанный момент времени.

// Не решена, где-то ошибка

public class Main {
    static class Point {
        double x, y, z;

        Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        Point add(Point p) {
            return new Point(x + p.x, y + p.y, z + p.z);
        }

        Point cross(Point p) {
            return new Point(
                    y * p.z - z * p.y,
                    z * p.x - x * p.z,
                    x * p.y - y * p.x
            );
        }

        double dot(Point p) {
            return x * p.x + y * p.y + z * p.z;
        }

        double length() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        void normalize() {
            double len = length();
            if (len > 0) {
                x /= len;
                y /= len;
                z /= len;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            double z = scanner.nextDouble();
            points[i] = new Point(x, y, z);
            points[i].normalize();
        }

        int ans = 0;
        final double EPS = 1e-9;

        // Using single points as normals
        for (int i = 0; i < n; i++) {
            Point normal = points[i];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (normal.dot(points[j]) > -EPS) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }

        // Using cross products of pairs as normals
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point normal = points[i].cross(points[j]);
                double len = normal.length();
                if (len < EPS) continue;
                normal.normalize();
                int count = 0;
                for (int k = 0; k < n; k++) {
                    if (normal.dot(points[k]) > -EPS) {
                        count++;
                    }
                }
                ans = Math.max(ans, count);
            }
        }

        // Using sums of three points as normals
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    Point normal = points[i].add(points[j]).add(points[k]);
                    double len = normal.length();
                    if (len < EPS) continue;
                    normal.normalize();
                    int count = 0;
                    for (int l = 0; l < n; l++) {
                        if (normal.dot(points[l]) > -EPS) {
                            count++;
                        }
                    }
                    ans = Math.max(ans, count);
                }
            }
        }

        System.out.println(ans);
    }
}