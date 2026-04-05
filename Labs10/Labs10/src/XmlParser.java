import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XmlParser {
    private static final String FILE_PATH = "C:\\Users\\Zaooo\\OneDrive\\Документы\\git\\Labs\\Labs10\\example.xml";
    private static Document document;

    public static void main(String[] args) {
        try {
            loadDocument();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== XML Парсер книг ===");
                System.out.println("1. Показать все книги");
                System.out.println("2. Добавить новую книгу");
                System.out.println("3. Поиск книг по автору");
                System.out.println("4. Поиск книг по году издания");
                System.out.println("5. Удалить книгу по названию");
                System.out.println("0. Выход");
                System.out.print("Выберите действие: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch (choice) {
                    case 1:
                        showAllBooks();
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        searchByAuthor(scanner);
                        break;
                    case 4:
                        searchByYear(scanner);
                        break;
                    case 5:
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

    // Загрузка XML документа
    private static void loadDocument() throws Exception {
        File inputFile = new File(FILE_PATH);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(inputFile);
        document.getDocumentElement().normalize();
    }

    // Показать все книги
    private static void showAllBooks() {
        System.out.println("\nКорневой элемент: " + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("book");

        if (nodeList.getLength() == 0) {
            System.out.println("Книги не найдены.");
            return;
        }

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("\n--- Книга #" + (i + 1) + " ---");
                System.out.println("Название: " + element.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("Автор: " + element.getElementsByTagName("author").item(0).getTextContent());
                System.out.println("Год издания: " + element.getElementsByTagName("year").item(0).getTextContent());
            }
        }
    }

    // Добавление новой книги
    private static void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();

        System.out.print("Введите автора: ");
        String author = scanner.nextLine();

        System.out.print("Введите год издания: ");
        String year = scanner.nextLine();

        // Создание нового элемента book
        Element rootElement = document.getDocumentElement();
        Element newBook = document.createElement("book");

        Element titleElement = document.createElement("title");
        titleElement.appendChild(document.createTextNode(title));
        newBook.appendChild(titleElement);

        Element authorElement = document.createElement("author");
        authorElement.appendChild(document.createTextNode(author));
        newBook.appendChild(authorElement);

        Element yearElement = document.createElement("year");
        yearElement.appendChild(document.createTextNode(year));
        newBook.appendChild(yearElement);

        rootElement.appendChild(newBook);

        // Сохранение изменений в файл
        saveDocument();
        System.out.println("Книга успешно добавлена!");
    }

    // Поиск книг по автору
    private static void searchByAuthor(Scanner scanner) {
        System.out.print("Введите имя автора для поиска: ");
        String searchAuthor = scanner.nextLine();

        NodeList nodeList = document.getElementsByTagName("book");

        // Преобразуем NodeList в List для использования Stream API
        List<Element> books = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(node -> (Element) node)
                .filter(element -> {
                    String author = element.getElementsByTagName("author").item(0).getTextContent();
                    return author.toLowerCase().contains(searchAuthor.toLowerCase());
                })
                .collect(Collectors.toList());

        if (books.isEmpty()) {
            System.out.println("Книги автора \"" + searchAuthor + "\" не найдены.");
        } else {
            System.out.println("\nНайденные книги автора \"" + searchAuthor + "\":");
            for (Element element : books) {
                System.out.println("\n--- Книга ---");
                System.out.println("Название: " + element.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("Автор: " + element.getElementsByTagName("author").item(0).getTextContent());
                System.out.println("Год издания: " + element.getElementsByTagName("year").item(0).getTextContent());
            }
        }
    }

    // Поиск книг по году издания
    private static void searchByYear(Scanner scanner) {
        System.out.print("Введите год издания для поиска: ");
        String searchYear = scanner.nextLine();

        NodeList nodeList = document.getElementsByTagName("book");

        List<Element> books = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(node -> (Element) node)
                .filter(element -> {
                    String year = element.getElementsByTagName("year").item(0).getTextContent();
                    return year.equals(searchYear);
                })
                .collect(Collectors.toList());

        if (books.isEmpty()) {
            System.out.println("Книги " + searchYear + " года издания не найдены.");
        } else {
            System.out.println("\nНайденные книги " + searchYear + " года издания:");
            for (Element element : books) {
                System.out.println("\n--- Книга ---");
                System.out.println("Название: " + element.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("Автор: " + element.getElementsByTagName("author").item(0).getTextContent());
                System.out.println("Год издания: " + element.getElementsByTagName("year").item(0).getTextContent());
            }
        }
    }

    // Удаление книги по названию
    private static void deleteBook(Scanner scanner) {
        System.out.print("Введите название книги для удаления: ");
        String titleToDelete = scanner.nextLine();

        NodeList nodeList = document.getElementsByTagName("book");
        boolean found = false;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String title = element.getElementsByTagName("title").item(0).getTextContent();

                if (title.equalsIgnoreCase(titleToDelete)) {
                    Node parentNode = element.getParentNode();
                    parentNode.removeChild(element);
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            saveDocument();
            System.out.println("Книга \"" + titleToDelete + "\" успешно удалена!");
        } else {
            System.out.println("Книга \"" + titleToDelete + "\" не найдена.");
        }
    }

    // Сохранение документа в файл
    private static void saveDocument() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}