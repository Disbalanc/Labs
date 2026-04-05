import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadExcelFileExample {
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "C:\\Users\\Zaooo\\OneDrive\\Документы\\git\\Labs\\Labs10\\example.xlsx";

        int attempt = 0;
        boolean success = false;

        while (attempt < MAX_RETRIES && !success) {
            attempt++;
            System.out.println("\n=== Попытка чтения файла #" + attempt + " ===");

            try {
                success = readExcelFile(filePath, scanner);
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            }

            if (!success && attempt < MAX_RETRIES) {
                System.out.println("\nХотите попробовать снова? (да/нет): ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (!answer.equals("да") && !answer.equals("yes") && !answer.equals("y")) {
                    break;
                }

                System.out.print("Введите новый путь к файлу (или Enter для прежнего): ");
                String newPath = scanner.nextLine().trim();
                if (!newPath.isEmpty()) {
                    filePath = newPath;
                }
            }
        }

        if (!success) {
            System.out.println("\nНе удалось прочитать Excel файл после " + attempt + " попыток.");
        }

        scanner.close();
    }

    private static boolean readExcelFile(String filePath, Scanner scanner) {
        File file = new File(filePath);

        // Проверка существования файла
        if (!file.exists()) {
            System.out.println("ОШИБКА: Файл не найден: " + filePath);
            System.out.println("   Рекомендация: Проверьте правильность пути к файлу.");
            return false;
        }

        // Проверка расширения файла
        if (!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
            System.out.println("ОШИБКА: Неверный формат файла.");
            System.out.println("   Рекомендация: Файл должен иметь расширение .xlsx или .xls");
            return false;
        }

        // Проверка возможности чтения
        if (!file.canRead()) {
            System.out.println("ОШИБКА: Нет прав на чтение файла.");
            System.out.println("   Рекомендация: Проверьте права доступа к файлу.");
            return false;
        }

        FileInputStream inputStream = null;
        XSSFWorkbook workbook = null;

        try {
            inputStream = new FileInputStream(file);
            System.out.println("✓ Файл успешно открыт.");

            workbook = new XSSFWorkbook(inputStream);
            System.out.println("✓ Excel книга успешно загружена.");
            System.out.println("  Количество листов: " + workbook.getNumberOfSheets());

            // Вывод списка доступных листов
            System.out.println("\nДоступные листы:");
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println("  " + (i + 1) + ". " + workbook.getSheetName(i));
            }

            // Запрос имени листа
            System.out.print("\nВведите название листа для чтения: ");
            String sheetName = scanner.nextLine().trim();

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("ОШИБКА: Лист \"" + sheetName + "\" не найден.");
                System.out.println("   Рекомендация: Выберите лист из списка выше.");

                // Предлагаем использовать первый лист
                System.out.print("Использовать первый лист? (да/нет): ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("да") || answer.equals("yes") || answer.equals("y")) {
                    sheet = workbook.getSheetAt(0);
                    System.out.println("Выбран лист: " + sheet.getSheetName());
                } else {
                    return false;
                }
            }

            System.out.println("\n✓ Лист \"" + sheet.getSheetName() + "\" успешно загружен.");
            System.out.println("  Количество строк: " + (sheet.getLastRowNum() + 1));

            // Проверка на пустой лист
            if (sheet.getLastRowNum() < 0) {
                System.out.println("⚠ ПРЕДУПРЕЖДЕНИЕ: Лист пустой.");
                return true;
            }

            // Чтение данных
            System.out.println("\n=== Содержимое листа ===\n");

            for (Row row : sheet) {
                StringBuilder rowData = new StringBuilder();
                for (Cell cell : row) {
                    String cellValue = getCellValueAsString(cell);
                    rowData.append(cellValue).append("\t");
                }
                System.out.println(rowData.toString());
            }

            System.out.println("\n✓ Чтение файла завершено успешно!");
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("ОШИБКА: Файл не найден.");
            System.out.println("   Путь: " + filePath);
            return false;

        } catch (EncryptedDocumentException e) {
            System.out.println("ОШИБКА: Файл зашифрован паролем.");
            System.out.println("   Рекомендация: Снимите защиту паролем с файла.");
            return false;

        } catch (IOException e) {
            System.out.println("    ОШИБКА: Ошибка чтения файла.");
            System.out.println("   Возможные причины:");
            System.out.println("   - Файл поврежден");
            System.out.println("   - Файл открыт в другой программе");
            System.out.println("   - Недостаточно памяти");
            System.out.println("   Детали: " + e.getMessage());
            return false;

        } finally {
            // Закрытие ресурсов
            try {
                if (workbook != null) workbook.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                System.out.println("Предупреждение: Ошибка при закрытии файла.");
            }
        }
    }

    // Безопасное получение значения ячейки
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        try {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    double value = cell.getNumericCellValue();
                    // Проверяем, является ли число целым
                    if (value == Math.floor(value)) {
                        return String.valueOf((long) value);
                    }
                    return String.valueOf(value);
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    try {
                        return String.valueOf(cell.getNumericCellValue());
                    } catch (Exception e) {
                        return cell.getStringCellValue();
                    }
                case BLANK:
                    return "";
                default:
                    return cell.toString();
            }
        } catch (Exception e) {
            return "[ошибка чтения]";
        }
    }
}