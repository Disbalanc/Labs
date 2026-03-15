

import java.util.Scanner;

public class RecursiveArray {
    private static Scanner sc = new Scanner(System.in);

    // Рекурсивный ввод массива
    public static void inputArray(int[] arr, int index) {
        if (index >= arr.length) return;
        System.out.print("arr[" + index + "] = ");
        arr[index] = sc.nextInt();
        inputArray(arr, index + 1);
    }

    // Рекурсивный вывод массива
    public static void printArray(int[] arr, int index) {
        if (index >= arr.length) {
            System.out.println();
            return;
        }
        System.out.print(arr[index]);
        if (index < arr.length - 1) System.out.print(", ");
        printArray(arr, index + 1);
    }

    public static void main(String[] args) {
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Введите элементы массива:");
        inputArray(arr, 0);

        System.out.print("Массив: [");
        printArray(arr, 0);
        System.out.print("]");
    }
}