import java.util.*;
import java.io.*;

//1097. Квадратная страна 2
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Квадратная Дума Квадратной страны постановила создать Национальный квадратный парк. Конечно же, парк должен занимать большой квадрат. К сожалению, на данный момент многие квадратные граждане инвестировали (не без помощи участников последнего чемпионата) свои квадрики в землю так, что часть страны уже занята. Возможно, не получится найти землю для парка, не затрагивая интересы частных владельцев. Если это действительно так, некоторые участки земли придется экспроприировать.
//Во избежание социальных волнений Дума должна разместить парк так, чтобы были затронуты интересы как можно менее влиятельных граждан. Лучше экспроприировать землю у тысячи простых граждан, чем у одного депутата или одного владельца банка.
//        Все земли, принадлежащие жителям Квадратной страны, отмечены целыми числами от 2 до 100 в зависимости от влиятельности владельца: земли Квадратного президента отмечены числом 100, земли олигархов – числом 99, земли депутатов – числом 98 и т.д.
//        Кроме того, некоторые земельные участки принадлежат членам (не квадратного) жюри, которые придумали эту задачу. Эта земля отмечена числом 255 и не может быть экспроприирована вообще.
//Исходные данные
//В первой строке указаны целые числа L и A – длина стороны Квадратной страны и длина стороны парка соответственно (1 ≤ A ≤ L ≤ 10000). Следующая строка содержит целое число M – количество занятых участков земли (1 ≤ M ≤ 100). Согласно квадратным законам, участок земли – это квадрат с целыми координатами вершин, стороны которого параллельны осям координат. Координаты левого нижнего угла Квадратной страны – (1, 1).
//Следующие M строк содержат информацию о занятых участках земли: влиятельность владельца, длину стороны квадрата и координаты левого нижнего угла. Влиятельность – целое число от 2 до 100 или число 255. Длина и координаты – целые числа от 1 до L. Каждый участок земли целиком находится в пределах страны и может пересекаться с другими участками земли только по своей границе. Вся земля, не принадлежащая ни одному занятому участку, является свободной.
//Результат
//Если можно построить парк на свободных землях, выведите число 1. Иначе выведите минимально возможную влиятельность владельцев земли, которую нужно экспроприировать (целое число от 2 до 100). Количество и площадь экспроприированных земельных участков не имеют значения. Следует учитывать влиятельность только самых влиятельных землевладельцев среди тех, которые пострадают от создания парка.
//Если невозможно создать парк, не включающий в себя земли членов жюри, выведите слово «IMPOSSIBLE».

public class Main {
    static class Plot {
        int inf, x, y, s;
        Plot(int inf, int x, int y, int s) {
            this.inf = inf;
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    static class Rectangle {
        int left, right, bottom, top;
        Rectangle(int l, int r, int b, int t) {
            left = l;
            right = r;
            bottom = b;
            top = t;
        }
    }

    static int L, A, M;
    static Plot[] plots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        plots = new Plot[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int inf = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            plots[i] = new Plot(inf, x, y, s);
        }

        // Проверяем, можно ли избежать земель членов жюри (255)
        if (!check(100)) {
            pw.println("IMPOSSIBLE");
            pw.close();
            return;
        }

        // Проверяем, можно ли разместить парк только на свободной земле (ответ 1)
        if (check(1)) {
            pw.println(1);
            pw.close();
            return;
        }

        // Бинарный поиск минимальной влиятельности
        int low = 2, high = 100;
        while (low < high) {
            int mid = (low + high) / 2;
            if (check(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        pw.println(low);
        pw.close();
    }

    static boolean check(int mid) {
        List<Rectangle> rects = new ArrayList<>();
        int maxX = L - A + 1; // максимальная координата левого нижнего угла парка по x
        int maxY = L - A + 1; // по y

        for (Plot p : plots) {
            if (p.inf > mid) { // запрещённые участки (включая 255)
                int left = Math.max(1, p.x - A + 1);
                int right = Math.min(maxX, p.x + p.s - 1);
                int bottom = Math.max(1, p.y - A + 1);
                int top = Math.min(maxY, p.y + p.s - 1);
                if (left <= right && bottom <= top) {
                    rects.add(new Rectangle(left, right, bottom, top));
                }
            }
        }

        // Если нет запрещённых прямоугольников, размещение возможно
        if (rects.isEmpty()) return true;

        // Собираем кандидатов координат x и y
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        xSet.add(1); xSet.add(maxX);
        ySet.add(1); ySet.add(maxY);

        for (Rectangle r : rects) {
            if (r.left > 1) xSet.add(r.left - 1);
            xSet.add(r.left);
            xSet.add(r.right);
            if (r.right < maxX) xSet.add(r.right + 1);

            if (r.bottom > 1) ySet.add(r.bottom - 1);
            ySet.add(r.bottom);
            ySet.add(r.top);
            if (r.top < maxY) ySet.add(r.top + 1);
        }

        List<Integer> xList = new ArrayList<>(xSet);
        List<Integer> yList = new ArrayList<>(ySet);
        Collections.sort(xList);
        Collections.sort(yList);

        // Проверяем каждую кандидатную точку
        for (int x : xList) {
            if (x < 1 || x > maxX) continue;
            for (int y : yList) {
                if (y < 1 || y > maxY) continue;
                boolean covered = false;
                for (Rectangle r : rects) {
                    if (x >= r.left && x <= r.right && y >= r.bottom && y <= r.top) {
                        covered = true;
                        break;
                    }
                }
                if (!covered) {
                    return true; // нашли свободную позицию
                }
            }
        }
        return false; // вся область покрыта запрещёнными прямоугольниками
    }
}