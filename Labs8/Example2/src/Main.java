import java.io.*;
import java.net.URL;

public class Main {
    public static void readAllByByte(InputStream in) throws IOException{
        while (true){
            int oneByte = in.read();
            if(oneByte !=-1){
                System.out.println((char) oneByte);
            }else{
                System.out.println("\n"+ "end");
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try {
            // Чтение из файла
            InputStream inFile = new FileInputStream("C:\\MyFile1.txt");
            readAllByByte(inFile);
            System.out.println("\n\n\n");
            inFile.close();

            // Чтение из интернет-страницы
            InputStream inUrl = new URL("http://google.com").openStream();
            readAllByByte(inUrl);
            System.out.println("\n\n\n");
            inUrl.close();

            // Чтение из массива byte
            InputStream inArray = new ByteArrayInputStream(new byte[]{7,9,3,7,4});
            readAllByByte(inArray);
            inArray.close();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}