import java.io.*;
import java.net.URL;

public class Main {

    public static void readAllByByte(Reader in) throws IOException {
        while (true) {
            int oneByte = in.read();
            if (oneByte != -1) {
                System.out.print((char) oneByte);
            } else {
                System.out.print("\nконец\n");
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            // С потоком связан файл
            InputStream inFile = new FileInputStream("C:\\MyFile1.txt");
            Reader rFile = new InputStreamReader(inFile, "cp1251");
            System.out.println("=== Чтение из файла ===");
            readAllByByte(rFile);
            inFile.close();
            rFile.close();

            // С потоком связана интернет-страница
            System.out.println("=== Чтение из интернета ===");
            InputStream inUrl = new URL("http://google.com").openStream();
            Reader rUrl = new InputStreamReader(inUrl, "cp1251");
            readAllByByte(rUrl);
            inUrl.close();
            rUrl.close();

            // С потоком связан массив типа byte
            System.out.println("=== Чтение из массива byte ===");
            InputStream inArray = new ByteArrayInputStream(new byte[] {72, 101, 108, 108, 111});
            Reader rArray = new InputStreamReader(inArray, "cp1251");
            readAllByByte(rArray);
            inArray.close();
            rArray.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
    }
}