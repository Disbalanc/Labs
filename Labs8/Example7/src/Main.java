import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter out = null;
        try {
            br = new BufferedReader(new FileReader("C:\\MyFile1.txt"), 1024);
            out = new BufferedWriter(new FileWriter("C:\\MyFileCopy.txt"));

            int LineCount = 0;
            String s;

            while ((s = br.readLine())!=null) {
                LineCount++;
                System.out.println(LineCount + ":"+s);
                out.write(s);
                out.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        finally {
            br.close();
            out.flush();
            out.close();
        }
    }
}