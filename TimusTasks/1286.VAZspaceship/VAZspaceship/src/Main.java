import java.util.Scanner;

//1286. Космолёт марки ВАЗ
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Общеизвестно (См. «Астронавигация для чайников», под общ. ред. чл.-кор. АН Ассоциации Свободных Миров (АСМ) проф. Г.Бейтса, с. 147-148.), что космолёт, оснащённый гипердвигателем класса B, способен выполнять переход с любой планеты на любую другую. Однако, на Вашем корабле марки ВАЗ-2106 (Вселенский Астромобильный Завод, цифры обозначают год начала выпуска), заклинило рычаг переключения коробки передач. В результате корабль способен перемещаться из точки с координатами (i, j) лишь в одну из точек из следующего списка: (i+q, j+p), (i−q, j+p), (i+q, j−p), (i−q, j−p), (i+p, j+q), (i−p, j+q), (i+p, j−q), (i−p, j−q) (все координаты — целочисленные, в стандартной межгалактической системе — Idem, с. 214). Помогите капитану Вашего корабля выяснить, сможет ли он своими силами добраться до планеты назначения, или ему следует вызывать ремонтную бригаду.
//Исходные данные
//В первой строке даны два числа p и q (передаточные числа первой гиперпередачи сломанного корабля), разделённые пробелами. Во второй строке находится пара чисел x и y — координаты планеты, на которой произошла поломка. В третьей строке находится пара чисел x1 и y1 — координаты планеты назначения. Числа во второй и третьей строках также разделены пробелами. Все числа целые, по модулю не превосходят 2·109.
//Результат
//Если капитан сможет довести корабль до планеты назначения, выведите YES. Если же нужно вызывать ремонтную бригаду, выведите NO.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long p = scanner.nextLong();
        long q = scanner.nextLong();
        long x0 = scanner.nextLong();
        long y0 = scanner.nextLong();
        long x1 = scanner.nextLong();
        long y1 = scanner.nextLong();

        if (p == 0 && q == 0) {
            if (x0 == x1 && y0 == y1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            return;
        }

        long A = p + q;
        long B = p - q;
        long du = (x1 + y1) - (x0 + y0);
        long dv = (x1 - y1) - (x0 - y0);

        long g = gcd(A, B);
        if (g == 0) {
            if (du == 0 && dv == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            return;
        }

        if (du % g != 0 || dv % g != 0) {
            System.out.println("NO");
            return;
        }

        long A1 = A / g;
        long B1 = B / g;
        long du1 = du / g;
        long dv1 = dv / g;

        if (A1 == 0 || B1 == 0) {
            System.out.println("YES");
            return;
        }

        long[] xy = extendedGcd(A1, B1);
        long a0 = xy[0] * du1;
        long b0 = xy[1] * du1;
        long d0 = xy[0] * dv1;
        long c0 = xy[1] * dv1;

        long X = B1;
        long Y = A1;
        long D1 = d0 - a0;
        long D2 = c0 - b0;

        if ((X & 1) == 0 && (Y & 1) == 0) {
            if ((D1 & 1) == 0 && (D2 & 1) == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else if ((X & 1) == 0) {
            if ((D1 & 1) == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else if ((Y & 1) == 0) {
            if ((D2 & 1) == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            if ((D1 & 1) == (D2 & 1)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static long gcd(long a, long b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static long[] extendedGcd(long a, long b) {
        if (b == 0) {
            if (a == 0) {
                return new long[]{0, 0};
            } else if (a > 0) {
                return new long[]{1, 0};
            } else {
                return new long[]{-1, 0};
            }
        }
        long[] gcd = extendedGcd(b, a % b);
        long x = gcd[1];
        long y = gcd[0] - (a / b) * gcd[1];
        return new long[]{x, y};
    }
}