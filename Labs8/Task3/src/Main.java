import java.io.*;
import java.util.*;

public class Main {
    // Множество согласных букв русского алфавита
    private static final Set<Character> CONSONANTS = new HashSet<>(Arrays.asList(
            'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р',
            'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'Й', 'К', 'Л', 'М', 'Н', 'П', 'Р',
            'С', 'Т', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ'
    ));

    public static void main(String[] args) {
        try {
            // Создаем исходный файл с текстом
            createSourceTextFile();

            // Обрабатываем файл
            processFile();

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }

    private static void createSourceTextFile() throws IOException {
        PrintWriter writer = new PrintWriter("input_text.txt", "UTF-8");

        // Текст из стихотворения Пушкина
        writer.println("Я помню чудное мгновенье:");
        writer.println("Передо мной явилась ты,");
        writer.println("Как мимолетное виденье,");
        writer.println("Как гений чистой красоты.");
        writer.println("");
        writer.println("В томленьях грусти безнадежной,");
        writer.println("В тревогах шумной суеты,");
        writer.println("Звучал мне долго голос нежный");
        writer.println("И снились милые черты.");

        writer.close();
        System.out.println("Создан исходный файл: input_text.txt");
    }

    private static void processFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input_text.txt"), "UTF-8"));

        PrintWriter writer = new PrintWriter("output_words.txt", "UTF-8");

        String line;
        int lineNumber = 0;

        System.out.println("\nОбработка файла:");
        System.out.println("=================");

        while ((line = reader.readLine()) != null) {
            lineNumber++;

            if (line.trim().isEmpty()) {
                continue; // Пропускаем пустые строки
            }

            System.out.println("\nСтрока " + lineNumber + ": " + line);

            // Разбиваем строку на слова с помощью split()
            String[] words = line.split("[\\s\\p{Punct}]+");
            List<String> consonantWords = new ArrayList<>();

            // Отбираем слова, начинающиеся с согласных
            for (String word : words) {
                if (!word.isEmpty() && startsWithConsonant(word)) {
                    consonantWords.add(word);
                }
            }

            // Записываем результат в выходной файл
            if (!consonantWords.isEmpty()) {
                writer.println("Строка " + lineNumber + " (слов: " + consonantWords.size() + "):");
                writer.println(String.join(", ", consonantWords));
                writer.println(); // Пустая строка для разделения

                System.out.println("  Найдено слов: " + consonantWords.size());
                System.out.println("  Слова: " + String.join(", ", consonantWords));
            } else {
                System.out.println("  Слова с согласных не найдены");
            }
        }

        reader.close();
        writer.close();

        System.out.println("\n=================");
        System.out.println("Результат записан в файл: output_words.txt");
    }

    private static boolean startsWithConsonant(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        char firstChar = word.charAt(0);
        return CONSONANTS.contains(firstChar);
    }
}