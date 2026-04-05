import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;


import java.io.FileWriter;

public class JsonCreator {
    public static void main(String[] args){
        JsonObject library = new JsonObject();
        JsonArray books = new JsonArray();

        JsonObject book1 = new JsonObject();
        book1.put("title","Война и мир");
        book1.put("author","Лев Толстой");
        book1.put("year",1869);

        JsonObject book2 = new JsonObject();
        book2.put("title","Мастер и Маргарита");
        book2.put("author","Михаил Булгаков");
        book2.put("year",1967);

        JsonObject book3 = new JsonObject();
        book3.put("title","Преступление и наказание");
        book3.put("author","Фёдор достоевский");
        book3.put("year",1866);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        library.put("books",books);

        try (FileWriter file = new FileWriter("C:\\Users\\Zaooo\\OneDrive\\Документы\\git\\Labs\\Labs10\\example.json")) {
            file.write(library.toJson());
            System.out.println("Json файл успешно создан!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
