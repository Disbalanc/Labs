import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;

public class JsonParser {
    private static final String FILE_PATH = "C:\\Users\\Zaooo\\OneDrive\\Документы\\git\\Labs\\Labs10\\example.json";
    private static JsonObject jsonObject;

    public static void main(String[] args) {
        try {
            loadJsonFile();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== JSON Парсер книг ===");
                System.out.println("1. Показать все книги");
                System.out.println("2. Поиск книг по автору");
                System.out.println("3. Добавить новую книгу");
                System.out.println("4. Удалить книгу по названию");
                System.out.println("0. Выход");
                System.out.print("Выберите действие: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        showAllBooks();
                        break;
                    case 2:
                        searchByAuthor(scanner);
                        break;
                    case 3:
                        addBook(scanner);
                        break;
                    case 4:
                        deleteBook(scanner);
                        break;
                    case 0:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверный выбор!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Загрузка JSON файла - ИСПРАВЛЕНО!
    private static void loadJsonFile() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Используем Jsoner.deserialize() вместо несуществующего метода parse()
            jsonObject = (JsonObject) Jsoner.deserialize(reader);
        }
    }

    // Показать все книги
    private static void showAllBooks() {
        System.out.println("\nКорневой элемент: " + jsonObject.keySet().iterator().next());
        JsonArray jsonArray = (JsonArray) jsonObject.get("books");

        if (jsonArray == null || jsonArray.isEmpty()) {
            System.out.println("Книги не найдены.");
            return;
        }

        int index = 1;
        for (Object o : jsonArray) {
            JsonObject book = (JsonObject) o;
            System.out.println("\n--- Книга #" + index++ + " ---");
            System.out.println("Название книги: " + book.get("title"));
            System.out.println("Автор книги: " + book.get("author"));
            System.out.println("Год издания: " + book.get("year"));
        }
    }

    // Поиск книг по автору
    private static void searchByAuthor(Scanner scanner) {
        System.out.print("Введите имя автора для поиска: ");
        String searchAuthor = scanner.nextLine();

        JsonArray jsonArray = (JsonArray) jsonObject.get("books");

        System.out.println("\nРезультаты поиска по автору \"" + searchAuthor + "\":");

        boolean found = false;
        jsonArray.stream()
                .filter(book -> book instanceof JsonObject)
                .map(book -> (JsonObject) book)
                .filter(book -> {
                    String author = (String) book.get("author");
                    return author != null && author.toLowerCase().contains(searchAuthor.toLowerCase());
                })
                .forEach(book -> {
                    System.out.println("\n--- Книга ---");
                    System.out.println("Название книги: " + book.get("title"));
                    System.out.println("Автор: " + book.get("author"));
                    System.out.println("Год издания: " + book.get("year"));
                });

        // Проверка наличия результатов
        long count = jsonArray.stream()
                .filter(book -> book instanceof JsonObject)
                .map(book -> (JsonObject) book)
                .filter(book -> {
                    String author = (String) book.get("author");
                    return author != null && author.toLowerCase().contains(searchAuthor.toLowerCase());
                })
                .count();

        if (count == 0) {
            System.out.println("Книги автора \"" + searchAuthor + "\" не найдены.");
        }
    }

    // Добавление новой книги
    private static void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();

        System.out.print("Введите автора: ");
        String author = scanner.nextLine();

        System.out.print("Введите год издания: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        JsonArray jsonArray = (JsonArray) jsonObject.get("books");

        JsonObject newBook = new JsonObject();
        newBook.put("title", title);
        newBook.put("author", author);
        newBook.put("year", year);

        jsonArray.add(newBook);

        saveJsonFile();
        System.out.println("Книга успешно добавлена!");
    }

    // Удаление книги по названию
    private static void deleteBook(Scanner scanner) {
        System.out.print("Введите название книги для удаления: ");
        String titleToDelete = scanner.nextLine();

        JsonArray jsonArray = (JsonArray) jsonObject.get("books");

        Iterator<Object> iterator = jsonArray.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            JsonObject book = (JsonObject) iterator.next();
            String title = (String) book.get("title");

            if (title != null && title.equalsIgnoreCase(titleToDelete)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            saveJsonFile();
            System.out.println("Книга \"" + titleToDelete + "\" успешно удалена!");
        } else {
            System.out.println("Книга \"" + titleToDelete + "\" не найдена.");
        }
    }

    // Сохранение JSON в файл
    private static void saveJsonFile() {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(Jsoner.prettyPrint(jsonObject.toJson()));
            file.flush();
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}