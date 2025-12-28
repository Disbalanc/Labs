import java.util.*;
import java.io.*;

//1732. Министерство правды
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Бело-чёрным по чёрно-белому написано изречение, подвергшееся цензуре со стороны Министерства правды. Автор его уже исчез вместе с историей о нём, и, пока Большой Брат наблюдает за кем-то другим, вам, как рядовому сотруднику миниправа, поручено стереть из изречения часть букв так, чтобы получилось другое, одобренное министерством, изречение.
//Министерство правды называет словом непустую последовательность латинских букв, а изречением — последовательность из одного или более слов, разделённых одним или несколькими пробелами. Пробелы также могут стоять перед первым и за последним словом изречения. Для сравнения двух изречений нужно сначала удалить все начальные и конечные пробелы, затем все блоки подряд идущих пробелов заменить на один пробел. Изречения считаются одинаковыми, если получившиеся строки совпадут. Когда сотрудник стирает букву в изречении, она заменяется на пробел.
//        Исходные данные
//В первой строке записано исходное изречение, а во второй — изречение, которое требуется получить. Длина каждого изречения не превосходит 100000 символов. Слова в этих изречениях разделены ровно одним пробелом, первый и последний символ каждой строки не может быть пробелом. Исходное и требуемое изречения различны.
//        Результат
//Если вы не сможете выполнить поручение, выведите в единственной строке «I HAVE FAILED!!!». В противном случае выведите исходное изречение, заменив в нём буквы, которые надо стереть, на символ подчёркивания.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String S = reader.readLine();
        String T = reader.readLine();

        // Разбиваем S на слова и запоминаем границы
        List<String> wordsS = new ArrayList<>();
        List<int[]> boundaries = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= S.length(); i++) {
            if (i == S.length() || S.charAt(i) == ' ') {
                if (start < i) {
                    wordsS.add(S.substring(start, i));
                    boundaries.add(new int[]{start, i});
                }
                start = i + 1;
            }
        }

        // Разбиваем T на слова
        String[] wordsT = T.split(" ");

        boolean[] used = new boolean[wordsS.size()];
        boolean[][] usedInWord = new boolean[wordsS.size()][];

        int j = 0;
        boolean failed = false;

        // Сопоставляем слова T со словами S
        for (String tWord : wordsT) {
            while (j < wordsS.size() && !isSubsequenceAndMark(tWord, wordsS.get(j), j, usedInWord)) {
                j++;
            }
            if (j == wordsS.size()) {
                failed = true;
                break;
            }
            used[j] = true;
            j++;
        }

        if (failed) {
            System.out.println("I HAVE FAILED!!!");
        } else {
            // Строим результат
            StringBuilder result = new StringBuilder();
            int wordIndex = 0;
            int charInWord = 0;

            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                if (c == ' ') {
                    result.append(' ');
                    // При переходе через пробел переходим к следующему слову
                    if (wordIndex < boundaries.size() && i >= boundaries.get(wordIndex)[1]) {
                        wordIndex++;
                        charInWord = 0;
                    }
                } else {
                    // Определяем, к какому слову относится текущий символ
                    if (wordIndex < boundaries.size() &&
                            i >= boundaries.get(wordIndex)[0] &&
                            i < boundaries.get(wordIndex)[1]) {

                        if (used[wordIndex] && usedInWord[wordIndex][charInWord]) {
                            result.append(c);
                        } else {
                            result.append('_');
                        }
                        charInWord++;
                    } else {
                        // Это не должно случиться
                        result.append(c);
                    }
                }
            }

            System.out.println(result.toString());
        }
    }

    // Проверяет, является ли pattern подпоследовательностью text.
    // Если да, то заполняет usedInWord[wordIndex] массивом булевых значений для использованных букв.
    private static boolean isSubsequenceAndMark(String pattern, String text, int wordIndex, boolean[][] usedInWord) {
        if (pattern.length() > text.length()) {
            return false;
        }
        boolean[] marked = new boolean[text.length()];
        int i = 0;
        for (int p = 0; p < pattern.length(); p++) {
            char pc = pattern.charAt(p);
            while (i < text.length() && text.charAt(i) != pc) {
                i++;
            }
            if (i == text.length()) {
                return false;
            }
            marked[i] = true;
            i++;
        }
        usedInWord[wordIndex] = marked;
        return true;
    }
}