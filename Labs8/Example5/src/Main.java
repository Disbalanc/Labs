import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp1251");
        System.out.print("Введите имя файла => ");
        String fname = sc.nextLine();

        try {
            // Создание файла
            File f1 = new File(fname);
            f1.createNewFile();
            System.out.println("Полный путь файла: " + f1.getAbsolutePath());

            System.out.print("Введите количество строк для записи в файл => ");
            int n = sc.nextInt();

            // Поток для записи
            DataOutputStream dOut = new DataOutputStream(new FileOutputStream(f1));
            sc.nextLine(); // очистка буфера

            for (int i = 0; i < n; i++) {
                System.out.print("Введите строку для записи в файл => ");
                String s = sc.nextLine();
                dOut.writeUTF(s);
            }
            dOut.flush();
            dOut.close();

            // Чтение и вывод данных из созданного файла
            DataInputStream dIn = new DataInputStream(new FileInputStream(f1));
            System.out.println("\nСодержимое файла:");
            for (int i = 0; i < n; i++) {
                System.out.println(dIn.readUTF());
            }
            dIn.close();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }
    }
}