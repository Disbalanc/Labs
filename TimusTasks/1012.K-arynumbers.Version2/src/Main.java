import java.math.BigInteger;
import java.util.Scanner;

//1012. K-ичные числа. Версия 2
//Ограничение времени: 0.5 секунды
//Ограничение памяти: 16 МБ
//Рассмотрим N-значные числа в системе счисления с основанием K. Будем считать число правильным, если его K-ичная запись не содержит двух подряд идущих нулей. Например:
//        1010230 — правильное 7-значное число;
//1000198 не является правильным числом;
//0001235 — не 7-значное, а 4-значное число.
//Даны числа N и K, вычислите количество правильных K-ичных чисел, состоящих из N цифр.
//        Ограничения: 2 ≤ K ≤ 10; N ≥ 2; N + K ≤ 1800.
//Исходные данные
//Числа N и K в десятичной записи, разделенные переводом строки.
//        Результат
//Искомое количество в десятичной записи.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        // Мы можем представить рекуррентное соотношение как:
        // F(n) = (K-1)*F(n-1) + (K-1)*F(n-2)
        // Это линейное рекуррентное соотношение второго порядка
        // Можно использовать матричное умножение для быстрого вычисления

        if (N == 1) {
            System.out.println(K - 1);
            scanner.close();
            return;
        }

        // Матрица перехода:
        // [F(n), F(n-1)] = [F(n-1), F(n-2)] * | (K-1)  (K-1) |
        //                                      |   1      0   |

        BigInteger kMinusOne = BigInteger.valueOf(K - 1);

        // Начальные значения:
        // F(1) = K-1
        // F(2) = (K-1)*K
        BigInteger f1 = kMinusOne;
        BigInteger f2 = kMinusOne.multiply(BigInteger.valueOf(K));

        if (N == 2) {
            System.out.println(f2);
            scanner.close();
            return;
        }

        // Для N >= 3 используем быстрое возведение матрицы в степень
        BigInteger[][] matrix = {
                {kMinusOne, kMinusOne},
                {BigInteger.ONE, BigInteger.ZERO}
        };

        // Возводим матрицу в степень (N-2)
        BigInteger[][] resultMatrix = matrixPower(matrix, N - 2);

        // Умножаем на вектор [F(2), F(1)]
        // result = resultMatrix[0][0]*F(2) + resultMatrix[0][1]*F(1)
        BigInteger result = resultMatrix[0][0].multiply(f2)
                .add(resultMatrix[0][1].multiply(f1));

        System.out.println(result);

        scanner.close();
    }

    // Умножение двух матриц 2x2
    private static BigInteger[][] matrixMultiply(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] result = new BigInteger[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = a[i][0].multiply(b[0][j])
                        .add(a[i][1].multiply(b[1][j]));
            }
        }

        return result;
    }

    // Быстрое возведение матрицы в степень
    private static BigInteger[][] matrixPower(BigInteger[][] matrix, int power) {
        if (power == 0) {
            // Возвращаем единичную матрицу
            return new BigInteger[][] {
                    {BigInteger.ONE, BigInteger.ZERO},
                    {BigInteger.ZERO, BigInteger.ONE}
            };
        }

        if (power == 1) {
            return matrix;
        }

        if (power % 2 == 0) {
            BigInteger[][] half = matrixPower(matrix, power / 2);
            return matrixMultiply(half, half);
        } else {
            BigInteger[][] half = matrixPower(matrix, power / 2);
            BigInteger[][] halfSquared = matrixMultiply(half, half);
            return matrixMultiply(halfSquared, matrix);
        }
    }
}