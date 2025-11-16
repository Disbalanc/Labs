import java.util.*;
import java.io.*;

//1517. Свобода выбора
//Ограничение времени: 2.0 секунды
//Ограничение памяти: 64 МБ
//        Вступление
//Не успели жители Албании привыкнуть к свободе слова (более подробно эта история описана в задаче «Свобода слова»), как им на голову свалилась ещё и свобода выбора. В самом ближайшем будущем населению предстоит пережить первые в истории страны демократические президентские выборы.
//О намерении бороться за высокий пост уже объявили два самых ярких албанских политика последних лет – либерал Мухаммед Тахир-оглы и его извечный оппонент консерватор Ахмед Касым-бей.
//        Задача
//Следуя демократическим традициям, перед выборами оба кандидата развлекаются тем, что выливают друг другу на головы тонны грязи под одобрительные возгласы избирателей. Каждый кандидат при любом удобном случае произносит предвыборную речь, в которой обвиняет своего политического оппонента в коррупции, неуважении к старшим, пособничестве террористам и вообще всячески демонстрирует своё уважение к противнику. В результате выступления Мухаммеда и Ахмеда стали похожи друг на друга до такой степени, что избирателям теперь вообще до лампочки, за кого голосовать.
//Этим хочет воспользоваться третий кандидат в президенты – председатель социалистической партии Албании товарищ Ктулху. Он поленился написать себе предвыборную речь, но между тем заметил, что некоторые фрагменты выступлений г-на Тахир-оглы и г-на Касым-бея полностью совпадают. Тогда тов. Ктулху решил взять самый длинный совпадающий фрагмент и сделать его своей предвыборной речью.
//        Исходные данные
//Первая строка содержит целое число N (1 ≤ N ≤ 100000). Вторая строка содержит речь г-на Тахир-оглы. Третья строка содержит речь г-на Касым-бея. Каждая речь состоит из N заглавных латинских букв.
//Результат
//Вывести речь тов. Ктулху. Если задача имеет несколько решений, то вывести любое из них.

public class Main {
    static long mod1 = 1000000007;
    static long mod2 = 1000000009;
    static long base = 131;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s1 = br.readLine();
        String s2 = br.readLine();

        // Precompute powers of base
        long[] pow1 = new long[n+1];
        long[] pow2 = new long[n+1];
        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i-1] * base) % mod1;
            pow2[i] = (pow2[i-1] * base) % mod2;
        }

        // Compute prefix hashes for s1
        long[] h1_1 = new long[n+1];
        long[] h2_1 = new long[n+1];
        for (int i = 0; i < n; i++) {
            char c = s1.charAt(i);
            h1_1[i+1] = (h1_1[i] * base + c) % mod1;
            h2_1[i+1] = (h2_1[i] * base + c) % mod2;
        }

        // Compute prefix hashes for s2
        long[] h1_2 = new long[n+1];
        long[] h2_2 = new long[n+1];
        for (int i = 0; i < n; i++) {
            char c = s2.charAt(i);
            h1_2[i+1] = (h1_2[i] * base + c) % mod1;
            h2_2[i+1] = (h2_2[i] * base + c) % mod2;
        }

        // Binary search for the maximum common substring length
        int low = 0, high = n;
        int maxLen = 0;
        int startIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int idx = hasCommonSubstring(mid, n, h1_1, h2_1, h1_2, h2_2, pow1, pow2);
            if (idx != -1) {
                maxLen = mid;
                startIndex = idx;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (maxLen == 0) {
            System.out.println("");
        } else {
            System.out.println(s2.substring(startIndex, startIndex + maxLen));
        }
    }

    static int hasCommonSubstring(int L, int n, long[] h1_1, long[] h2_1,
                                  long[] h1_2, long[] h2_2, long[] pow1, long[] pow2) {
        if (L == 0) return 0;

        Set<Long> set = new HashSet<>();

        // Add all substrings of length L from first string
        for (int i = 0; i <= n - L; i++) {
            long hash1 = (h1_1[i + L] - h1_1[i] * pow1[L] % mod1) % mod1;
            if (hash1 < 0) hash1 += mod1;

            long hash2 = (h2_1[i + L] - h2_1[i] * pow2[L] % mod2) % mod2;
            if (hash2 < 0) hash2 += mod2;

            // Combine both hashes into one long value
            long combined = hash1 * (mod2 + 1) + hash2;
            set.add(combined);
        }

        // Check substrings from second string
        for (int i = 0; i <= n - L; i++) {
            long hash1 = (h1_2[i + L] - h1_2[i] * pow1[L] % mod1) % mod1;
            if (hash1 < 0) hash1 += mod1;

            long hash2 = (h2_2[i + L] - h2_2[i] * pow2[L] % mod2) % mod2;
            if (hash2 < 0) hash2 += mod2;

            long combined = hash1 * (mod2 + 1) + hash2;
            if (set.contains(combined)) {
                return i;
            }
        }

        return -1;
    }
}