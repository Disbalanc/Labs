import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter out = null;

        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("C:\\MyFile1.txt"), "cp1251"));

            out = new PrintWriter("C:\\MyFile3.txt", "cp1251");

            int lineCount = 0;
            String s;

            while ((s = br.readLine()) != null) {
                lineCount++;
                out.println(lineCount + ": " + s);
                System.out.println(lineCount + ": " + s);
            }
            System.out.println("Записано " + lineCount + " строк");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии: " + e.getMessage());
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}