import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Создаем исходный файл программными средствами
            createSourceFile();

            // Читаем данные из исходного файла
            List<Object> data = readSourceFile();

            // Фильтруем данные
            List<Object> filteredData = filterData(data);

            // Записываем результат в новый файл
            writeResultFile(filteredData);

            System.out.println("Операция завершена успешно!");

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createSourceFile() throws IOException {
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("source.dat"));

        // Записываем две строки в формате UTF-8
        dos.writeUTF("Первая строка текста");
        dos.writeUTF("Вторая строка текста");

        // Записываем 5 чисел типа double
        double[] numbers = {12.5, -3.7, 8.2, -15.0, 7.3};
        for (double num : numbers) {
            dos.writeDouble(num);
        }

        dos.close();
        System.out.println("Исходный файл создан: source.dat");
    }

    private static List<Object> readSourceFile() throws IOException {
        List<Object> data = new ArrayList<>();
        DataInputStream dis = new DataInputStream(
                new FileInputStream("source.dat"));

        try {
            // Читаем две строки
            String line1 = dis.readUTF();
            String line2 = dis.readUTF();
            data.add(line1);
            data.add(line2);

            System.out.println("Прочитано строк:");
            System.out.println("1: " + line1);
            System.out.println("2: " + line2);

            // Читаем 5 чисел
            System.out.println("\nПрочитано чисел:");
            for (int i = 0; i < 5; i++) {
                double num = dis.readDouble();
                data.add(num);
                System.out.println((i+1) + ": " + num);
            }

        } catch (EOFException e) {
            // Достигнут конец файла
        } finally {
            dis.close();
        }

        return data;
    }

    private static List<Object> filterData(List<Object> data) {
        List<Object> result = new ArrayList<>();

        // Вторая строка (индекс 1)
        result.add(data.get(1));

        // Положительные числа
        for (int i = 2; i < data.size(); i++) {
            Object item = data.get(i);
            if (item instanceof Double) {
                double num = (Double) item;
                if (num > 0) {
                    result.add(num);
                }
            }
        }

        return result;
    }

    private static void writeResultFile(List<Object> filteredData) throws IOException {
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("result.dat"));

        System.out.println("\nЗаписываем в результирующий файл:");

        // Записываем вторую строку
        String secondLine = (String) filteredData.get(0);
        dos.writeUTF(secondLine);
        System.out.println("Строка: " + secondLine);

        // Записываем положительные числа
        System.out.println("Положительные числа:");
        for (int i = 1; i < filteredData.size(); i++) {
            double num = (Double) filteredData.get(i);
            dos.writeDouble(num);
            System.out.println(num);
        }

        dos.close();
        System.out.println("\nРезультирующий файл создан: result.dat");
    }
}