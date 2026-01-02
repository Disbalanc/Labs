import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание папки My
            File dir = new File("C:\\My");
            dir.mkdir();

            // Создание исходного файла numIsh.txt и запись в него чисел типа float
            File f1 = new File("C:\\My\\numIsh.txt");
            f1.createNewFile();

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.println("Сколько вещественных чисел записать в файл?");
            int count = sc.nextInt();

            DataOutputStream wr = new DataOutputStream(new FileOutputStream(f1.getAbsolutePath()));
            System.out.println("Введите числа:");
            for (int i = 0; i < count; i++) {
                wr.writeFloat(sc.nextFloat());
            }
            wr.flush();
            wr.close();

            // Создание файла numRez.txt и переписывание в него чисел из numIsh.txt
            File f2 = new File("C:\\My\\numRez.txt");
            f2.createNewFile();

            // Поток для чтения из исходного файла
            DataInputStream rd = new DataInputStream(new FileInputStream(f1.getAbsolutePath()));
            // Поток для записи в результирующий файл
            wr = new DataOutputStream(new FileOutputStream(f2.getAbsolutePath()));

            System.out.println("\nКопирование чисел:");
            try {
                while (true) {
                    float number = rd.readFloat();
                    wr.writeFloat(number);
                    System.out.println("Число " + number);
                }
            } catch (EOFException e) {
                System.out.println("Конец файла достигнут");
            }

            wr.flush();
            wr.close();
            rd.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}