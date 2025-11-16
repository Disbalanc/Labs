import java.util.Scanner;

//1550. Пирамида декана 3
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Недавно, на финале чемпионата мира в Токио, Билл Паучер спросил декана математико-механического факультета УрГУ Магаза Оразкимовича:
//        — Ну как там уральские студенты, не устали еще катать вашу пирамиду? (Cм. задачи Пирамида декана и Пирамида декана 2.)
//        — Не устанут, она же совсем легкая, — ответил декан.
//        — Ну да легкая, она же не пустая, а какой-то водой заполнена, — возмутился переводивший разговор Денис.
//        — А какого объема ваша пирамида? — спросил тогда Билл Паучер.
//Сегодня вам предстоит ответить на вопрос Билла Паучера.
//        Обратите внимание, что пирамида декана на самом деле является подставкой для ручки, и имеет сквозное цилиндрическое отверстие. Если пирамида стоит на своем основании, то ось отверстия строго вертикальна.
//Исходные данные
//В первой строке указаны два числа H и W — высота пирамиды и длина стороны основания (напоминаем, что пирамида декана — это правильная четырёхугольная пирамида). Во второй строке находятся два числа X и Y — положение центра отверстия (считаем, что оси направлены вдоль ребер основания и центр координат находится в центре основания пирамиды). В третьей строке указан радиус отверстия R. Известно, что отверстие для ручки не пересекает ребер пирамиды. (0 < H, W < 104, |X| < W/2, |Y| < W/2, 0 < R < W/2)
//Результат
//Выведите объем пирамиды с точностью до 10–3.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double H = scanner.nextDouble();
        double W = scanner.nextDouble();
        double X = scanner.nextDouble();
        double Y = scanner.nextDouble();
        double R = scanner.nextDouble();

        double V_pyr = W * W * H / 3.0;
        int steps_z = 2000;
        int steps_x = 2000;
        double dz = H / steps_z;
        double V_hole = 0;

        for (int i = 0; i < steps_z; i++) {
            double z = i * dz + dz / 2;
            double L = W * (1 - z / H);
            double left = -L / 2;
            double right = L / 2;
            double bottom = -L / 2;
            double top = L / 2;

            if (X + R < left || X - R > right || Y + R < bottom || Y - R > top) {
                continue;
            }

            double x_low = Math.max(left, X - R);
            double x_high = Math.min(right, X + R);
            if (x_low >= x_high) {
                continue;
            }

            double A = 0;
            double dx = (x_high - x_low) / steps_x;
            for (int j = 0; j < steps_x; j++) {
                double x = x_low + j * dx + dx / 2;
                double d = Math.sqrt(R * R - (x - X) * (x - X));
                double y_low = Math.max(bottom, Y - d);
                double y_high = Math.min(top, Y + d);
                if (y_low < y_high) {
                    A += (y_high - y_low) * dx;
                }
            }
            V_hole += A * dz;
        }

        double result = V_pyr - V_hole;
        System.out.printf("%.5f\n", result);
    }
}