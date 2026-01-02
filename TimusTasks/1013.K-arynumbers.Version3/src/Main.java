import java.io.*;
import java.math.BigInteger;

//1013. K-ичные числа. Версия 3
//Ограничение времени: 0.5 секунды
//Ограничение памяти: 64 МБ
//Рассмотрим N-значные числа в системе счисления с основанием K. Будем считать число правильным, если его K-ичная запись не содержит двух подряд идущих нулей. Например:
//        1010230 — правильное 7-значное число;
//1000198 не является правильным числом;
//0001235 — не 7-значное, а 4-значное число.
//Даны числа N, K и M, вычислите количество правильных K-ичных чисел, состоящих из N цифр по модулю M.
//Ограничения: 2 ≤ N, K, M ≤ 1018.
//Исходные данные
//        Числа N, K и M в десятичной записи, разделенные переводом строки.
//        Результат
//Искомое количество в десятичной записи.

public class Main {

    static BigInteger mod;

    // Умножение двух матриц 2x2 по модулю mod
    static BigInteger[][] multiply(BigInteger[][] A, BigInteger[][] B) {
        BigInteger[][] C = new BigInteger[2][2];
        C[0][0] = A[0][0].multiply(B[0][0]).add(A[0][1].multiply(B[1][0])).mod(mod);
        C[0][1] = A[0][0].multiply(B[0][1]).add(A[0][1].multiply(B[1][1])).mod(mod);
        C[1][0] = A[1][0].multiply(B[0][0]).add(A[1][1].multiply(B[1][0])).mod(mod);
        C[1][1] = A[1][0].multiply(B[0][1]).add(A[1][1].multiply(B[1][1])).mod(mod);
        return C;
    }

    // Быстрое возведение матрицы в степень по модулю mod
    static BigInteger[][] power(BigInteger[][] matrix, BigInteger exponent) {
        BigInteger[][] result = {
                {BigInteger.ONE, BigInteger.ZERO},
                {BigInteger.ZERO, BigInteger.ONE}
        };
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.testBit(0)) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger N = new BigInteger(reader.readLine());
        BigInteger K = new BigInteger(reader.readLine());
        mod = new BigInteger(reader.readLine());

        // По условию N ≥ 2, но для полноты обработаем и N = 1
        if (N.equals(BigInteger.ONE)) {
            System.out.println(K.subtract(BigInteger.ONE).mod(mod));
            return;
        }

        BigInteger c = K.subtract(BigInteger.ONE).mod(mod);      // (K-1) mod M
        BigInteger Kmod = K.mod(mod);                           // K mod M
        BigInteger f1 = c;                                      // f(1)
        BigInteger f2 = c.multiply(Kmod).mod(mod);              // f(2)

        if (N.equals(BigInteger.valueOf(2))) {
            System.out.println(f2);
            return;
        }

        // Матрица перехода для рекуррентного соотношения
        BigInteger[][] matrix = {
                {c, c},
                {BigInteger.ONE, BigInteger.ZERO}
        };

        BigInteger exponent = N.subtract(BigInteger.valueOf(2));
        BigInteger[][] powered = power(matrix, exponent);

        // f(N) = первая строка матрицы * вектор [f(2), f(1)]^T
        BigInteger fN = powered[0][0].multiply(f2)
                .add(powered[0][1].multiply(f1))
                .mod(mod);
        System.out.println(fN);
    }
}