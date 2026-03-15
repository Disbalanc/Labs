import java.util.*;

public class CollectionComparison {
    static final int N = 1_000_000;

    public static void main(String[] args) {
        System.out.println("N = " + N);
        System.out.println();

        // ===== ТАБЛИЦА 1: ДОБАВЛЕНИЕ =====
        System.out.println("========== ТАБЛИЦА 1: ДОБАВЛЕНИЕ ==========");

        // --- ArrayList ---
        System.out.println("\n--- ArrayList ---");
        // В конец
        List<Integer> al1 = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) al1.add(i);
        long alEndAdd = System.currentTimeMillis() - start;
        System.out.println("В конец:    " + alEndAdd + " мс");

        // В начало (уменьшенная выборка — иначе слишком долго)
        int nSmall = 100_000;
        List<Integer> al2 = new ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < nSmall; i++) al2.add(0, i);
        long alBeginAdd = System.currentTimeMillis() - start;
        System.out.println("В начало (" + nSmall + "): " + alBeginAdd + " мс");

        // В середину
        List<Integer> al3 = new ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < nSmall; i++) al3.add(al3.size() / 2, i);
        long alMidAdd = System.currentTimeMillis() - start;
        System.out.println("В середину (" + nSmall + "): " + alMidAdd + " мс");

        // --- LinkedList ---
        System.out.println("\n--- LinkedList ---");
        // В конец
        List<Integer> ll1 = new LinkedList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) ll1.add(i);
        long llEndAdd = System.currentTimeMillis() - start;
        System.out.println("В конец:    " + llEndAdd + " мс");

        // В начало
        List<Integer> ll2 = new LinkedList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) ll2.add(0, i);
        long llBeginAdd = System.currentTimeMillis() - start;
        System.out.println("В начало:   " + llBeginAdd + " мс");

        // В середину
        List<Integer> ll3 = new LinkedList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < nSmall; i++) ll3.add(ll3.size() / 2, i);
        long llMidAdd = System.currentTimeMillis() - start;
        System.out.println("В середину (" + nSmall + "): " + llMidAdd + " мс");

        // --- ArrayDeque ---
        System.out.println("\n--- ArrayDeque ---");
        // В конец (addLast)
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) ad1.addLast(i);
        long adEndAdd = System.currentTimeMillis() - start;
        System.out.println("В конец:    " + adEndAdd + " мс");

        // В начало (addFirst)
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) ad2.addFirst(i);
        long adBeginAdd = System.currentTimeMillis() - start;
        System.out.println("В начало:   " + adBeginAdd + " мс");

        System.out.println("В середину: не поддерживается");

        // ===== ТАБЛИЦА 2: УДАЛЕНИЕ =====
        System.out.println("\n========== ТАБЛИЦА 2: УДАЛЕНИЕ ==========");

        // --- ArrayList ---
        System.out.println("\n--- ArrayList ---");

        // С конца
        List<Integer> alDel1 = new ArrayList<>();
        for (int i = 0; i < N; i++) alDel1.add(i);
        start = System.currentTimeMillis();
        for (int i = alDel1.size() - 1; i >= 0; i--) alDel1.remove(i);
        long alEndDel = System.currentTimeMillis() - start;
        System.out.println("С конца:    " + alEndDel + " мс");

        // С начала
        List<Integer> alDel2 = new ArrayList<>();
        for (int i = 0; i < nSmall; i++) alDel2.add(i);
        start = System.currentTimeMillis();
        while (!alDel2.isEmpty()) alDel2.remove(0);
        long alBeginDel = System.currentTimeMillis() - start;
        System.out.println("С начала (" + nSmall + "): " + alBeginDel + " мс");

        // Из середины
        List<Integer> alDel3 = new ArrayList<>();
        for (int i = 0; i < nSmall; i++) alDel3.add(i);
        start = System.currentTimeMillis();
        while (!alDel3.isEmpty()) alDel3.remove(alDel3.size() / 2);
        long alMidDel = System.currentTimeMillis() - start;
        System.out.println("Из середины (" + nSmall + "): " + alMidDel + " мс");

        // --- LinkedList ---
        System.out.println("\n--- LinkedList ---");

        // С конца
        LinkedList<Integer> llDel1 = new LinkedList<>();
        for (int i = 0; i < N; i++) llDel1.add(i);
        start = System.currentTimeMillis();
        while (!llDel1.isEmpty()) llDel1.removeLast();
        long llEndDel = System.currentTimeMillis() - start;
        System.out.println("С конца:    " + llEndDel + " мс");

        // С начала
        LinkedList<Integer> llDel2 = new LinkedList<>();
        for (int i = 0; i < N; i++) llDel2.add(i);
        start = System.currentTimeMillis();
        while (!llDel2.isEmpty()) llDel2.removeFirst();
        long llBeginDel = System.currentTimeMillis() - start;
        System.out.println("С начала:   " + llBeginDel + " мс");

        // Из середины
        LinkedList<Integer> llDel3 = new LinkedList<>();
        for (int i = 0; i < nSmall; i++) llDel3.add(i);
        start = System.currentTimeMillis();
        while (!llDel3.isEmpty()) llDel3.remove(llDel3.size() / 2);
        long llMidDel = System.currentTimeMillis() - start;
        System.out.println("Из середины (" + nSmall + "): " + llMidDel + " мс");

        // --- ArrayDeque ---
        System.out.println("\n--- ArrayDeque ---");

        // С конца
        ArrayDeque<Integer> adDel1 = new ArrayDeque<>();
        for (int i = 0; i < N; i++) adDel1.addLast(i);
        start = System.currentTimeMillis();
        while (!adDel1.isEmpty()) adDel1.removeLast();
        long adEndDel = System.currentTimeMillis() - start;
        System.out.println("С конца:    " + adEndDel + " мс");

        // С начала
        ArrayDeque<Integer> adDel2 = new ArrayDeque<>();
        for (int i = 0; i < N; i++) adDel2.addLast(i);
        start = System.currentTimeMillis();
        while (!adDel2.isEmpty()) adDel2.removeFirst();
        long adBeginDel = System.currentTimeMillis() - start;
        System.out.println("С начала:   " + adBeginDel + " мс");

        System.out.println("Из середины: не поддерживается напрямую");

        // ===== ТАБЛИЦА 3: ПОЛУЧЕНИЕ ПО ИНДЕКСУ =====
        System.out.println("\n========== ТАБЛИЦА 3: ПОЛУЧЕНИЕ ПО ИНДЕКСУ ==========");

        // ArrayList: 1_000_000_000 обращений
        List<Integer> alGet = new ArrayList<>();
        for (int i = 0; i < N; i++) alGet.add(i);
        Random rnd = new Random(42);
        long nIndex = 1_000_000_000L;
        // Для ArrayList — можно все 1 млрд
        // но для экономии времени ограничим 10 млн
        long nIndexAL = Math.min(nIndex, 10_000_000L);
        start = System.currentTimeMillis();
        for (long i = 0; i < nIndexAL; i++) {
            alGet.get(rnd.nextInt(N));
        }
        long alGetTime = System.currentTimeMillis() - start;
        System.out.println("ArrayList get() [" + nIndexAL + " обращений]: " + alGetTime + " мс");

        // LinkedList: слишком медленно для больших N, ограничим
        int nGetLL = 10_000;
        List<Integer> llGet = new LinkedList<>();
        for (int i = 0; i < N; i++) llGet.add(i);
        rnd = new Random(42);
        start = System.currentTimeMillis();
        for (int i = 0; i < nGetLL; i++) {
            llGet.get(rnd.nextInt(N));
        }
        long llGetTime = System.currentTimeMillis() - start;
        System.out.println("LinkedList get() [" + nGetLL + " обращений]: " + llGetTime + " мс");

        // ArrayDeque
        System.out.println("ArrayDeque get(index): не поддерживается (нет метода get(int))");
    }
}