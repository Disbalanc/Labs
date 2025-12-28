import java.util.*;
import java.io.*;

//1590. Шифр Бэкона
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//Программисту Васе не повезло — вместо отпуска его послали в командировку, на научную конференцию. Надо повышать уровень знаний, сказал начальник, важная конференция по криптографии, проводится во Франции — а там шифровали еще во времена Ришелье и взламывали чужие шифры еще во времена Виета.
//Вася быстро выяснил, что все луврские картины он уже где-то видел, вид эйфелевой башни приелся ему еще раньше, чем мышка стерла его с коврика, а такие стеклянные пирамиды у нас делают надо всякими киосками и сомнительными забегаловками. Одним словом, смотреть в Париже оказалось просто не на что, рыбу половить негде, поэтому Васе пришлось посещать доклады на конференции.
//Один из докладчиков, в очередной раз пытаясь разгадать шифры Бэкона, выдвинул гипотезу, что ключ к тайнам Бэкона можно подобрать, проанализировав все возможные подстроки произведений Бэкона.
//«Но их же слишком много!» — вслух удивился Вася.
//«Нет, не так уж и много!» — закричал докладчик — «подсчитайте и вы сами убедитесь!».
//Тем же вечером Вася нашел в интернете полное собрание сочинений Бэкона. Он написал программу, которая переработала тексты в одну длинную строку, выкинув из текстов все пробелы и знаки препинания. И вот теперь Вася весьма озадачен — а как же подсчитать количество различных подстрок этой строки?
//Исходные данные
//На входе дана непустая строка, полученная Васей. Строка состоит только из строчных латинских символов. Ее длина не превосходит 5000 символов.
//        Результат
//Выведите количество различных подстрок этой строки.

public class Main {

    static class State {
        int len;            // длина самой длинной строки в этом состоянии
        int link;           // суффиксная ссылка
        int[] next = new int[26]; // переходы по символам

        State() {
            Arrays.fill(next, -1);
        }
    }

    static State[] st;      // массив состояний
    static int size;        // количество состояний
    static int last;        // последнее состояние

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = s.length();

        // Инициализация суффиксного автомата
        initAutomaton(n * 2);

        // Построение суффиксного автомата
        for (int i = 0; i < n; i++) {
            extend(s.charAt(i) - 'a');
        }

        // Подсчет количества различных подстрок
        long result = countDistinctSubstrings();
        System.out.println(result);
    }

    static void initAutomaton(int maxStates) {
        st = new State[maxStates];
        for (int i = 0; i < maxStates; i++) {
            st[i] = new State();
        }
        size = 1;
        last = 0;
        st[0].len = 0;
        st[0].link = -1;
    }

    static void extend(int c) {
        int cur = size++;
        st[cur].len = st[last].len + 1;

        int p = last;
        while (p != -1 && st[p].next[c] == -1) {
            st[p].next[c] = cur;
            p = st[p].link;
        }

        if (p == -1) {
            st[cur].link = 0;
        } else {
            int q = st[p].next[c];
            if (st[p].len + 1 == st[q].len) {
                st[cur].link = q;
            } else {
                int clone = size++;
                st[clone].len = st[p].len + 1;
                st[clone].next = st[q].next.clone();
                st[clone].link = st[q].link;

                while (p != -1 && st[p].next[c] == q) {
                    st[p].next[c] = clone;
                    p = st[p].link;
                }

                st[q].link = clone;
                st[cur].link = clone;
            }
        }

        last = cur;
    }

    static long countDistinctSubstrings() {
        long result = 0;
        // Для каждого состояния, кроме начального (0)
        for (int i = 1; i < size; i++) {
            result += st[i].len - st[st[i].link].len;
        }
        return result;
    }
}