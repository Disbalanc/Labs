// ИСПРАВЛЕНО: правильные импорты из jsoup вместо javax.swing
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LinkParser {
    private static final String URL = "http://fat.urfu.ru/index.html";
    private static final String OUTPUT_FILE = "C:\\Users\\Zaooo\\OneDrive\\Документы\\git\\Labs\\Labs10\\news_output.txt";
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 2000;

    public static void main(String[] args) {
        Document doc = connectWithRetry();

        if (doc == null) {
            System.out.println("Не удалось подключиться к сайту после нескольких попыток.");
            return;
        }

        parseAndSaveNews(doc);
    }

    // Подключение с повторными попытками при ошибке
    private static Document connectWithRetry() {
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                System.out.println("Попытка подключения #" + attempt + " к " + URL);

                Document doc = Jsoup.connect(URL)
                        .timeout(10000) // таймаут 10 секунд
                        .userAgent("Mozilla/5.0")
                        .get();

                System.out.println("Успешное подключение!");
                return doc;

            } catch (IOException e) {
                System.out.println("Ошибка при подключении: " + e.getMessage());

                if (attempt < MAX_RETRIES) {
                    System.out.println("Повторная попытка через " + (RETRY_DELAY_MS / 1000) + " секунд...");
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        return null;
    }

    // Парсинг и сохранение новостей
    private static void parseAndSaveNews(Document doc) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            // Записываем заголовок с датой и временем
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println("=== Новости с сайта " + URL + " ===");
            writer.println("Дата парсинга: " + timestamp);
            writer.println("================================================\n");

            System.out.println("=== Новости с сайта ===\n");

            // Извлекаем заголовок страницы
            String title = doc.title();
            System.out.println("Заголовок страницы: " + title);
            writer.println("Заголовок страницы: " + title + "\n");

            // Пытаемся найти новостные блоки (адаптируйте селектор под реальную структуру сайта)
            Elements newsBlocks = doc.select(".blocktitle, .news, .news-item, h2, h3");

            if (newsBlocks.isEmpty()) {
                // Альтернативный способ - получаем все параграфы
                newsBlocks = doc.select("p, td");
            }

            int newsCount = 0;
            for (Element element : newsBlocks) {
                String text = element.text().trim();
                if (!text.isEmpty() && text.length() > 10) {
                    newsCount++;
                    String newsLine = "Новость #" + newsCount + ": " + text;
                    System.out.println(newsLine);
                    writer.println(newsLine);

                    if (newsCount >= 20) break; // Ограничение на количество
                }
            }

            if (newsCount == 0) {
                System.out.println("Новости не найдены на странице.");
                writer.println("Новости не найдены на странице.");
            }

            writer.println("\n================================================");
            writer.println("Всего найдено новостей: " + newsCount);

            System.out.println("\nДанные успешно сохранены в файл: " + OUTPUT_FILE);

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}