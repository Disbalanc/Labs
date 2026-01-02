import java.io.*;
import java.util.Arrays;

public class Main {
    public static void readAppByArray(InputStream in) throws IOException{
        byte[] buff = new byte[5];
        while (true){
            int count = in.read(buff);
            if(count != -1){
                System.out.println("количество ="+ count+ ", buff = "+ Arrays.toString(buff) + ",str= "+ new String(buff,0,count,"cp1251"));
            }else {
                break;
            }
        }
    }
    public static void main(String[] args) {
        String fileName = "C:\\MyFile1.txt";
        InputStream inFile = null;
        try {
            inFile = new FileInputStream(fileName);
            readAppByArray(inFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if(inFile !=null){
                try {
                    inFile.close();
                }catch (IOException ignore){
                    /*NOP*/
                }
            }
        }
    }
}