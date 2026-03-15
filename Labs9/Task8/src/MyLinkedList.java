import java.util.Scanner;

public class MyLinkedList {
    Node head;
    private static Scanner sc = new Scanner(System.in);

    // ============================================================
    //  а) Методы с использованием цикла
    // ============================================================

    /** Ввод с головы: каждый новый элемент становится новой головой */
    public void createHead(int count) {
        System.out.println("Ввод с головы (" + count + " элементов):");
        for (int i = 0; i < count; i++) {
            System.out.print("  Элемент " + (i + 1) + ": ");
            int val = sc.nextInt();
            head = new Node(val, head);
        }
    }

    /** Ввод с хвоста: каждый новый элемент добавляется в конец */
    public void createTail(int count) {
        System.out.println("Ввод с хвоста (" + count + " элементов):");
        Node tail = null;
        // Найти текущий хвост
        if (head != null) {
            tail = head;
            while (tail.next != null) tail = tail.next;
        }
        for (int i = 0; i < count; i++) {
            System.out.print("  Элемент " + (i + 1) + ": ");
            int val = sc.nextInt();
            Node newNode = new Node(val, null);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
    }

    /** Вывод: возвращается строка, сформированная из элементов списка */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node ref = head;
        while (ref != null) {
            sb.append(ref.value);
            if (ref.next != null) sb.append(", ");
            ref = ref.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /** Добавление элемента в начало списка */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    /** Добавление элемента в конец списка */
    public void addLast(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node ref = head;
        while (ref.next != null) {
            ref = ref.next;
        }
        ref.next = newNode;
    }

    /** Вставка элемента в список с указанным номером (1-based) */
    public void insert(int position, int value) {
        if (position <= 1) {
            addFirst(value);
            return;
        }
        Node newNode = new Node(value, null);
        Node ref = head;
        int k = 1;
        while (ref.next != null && k < position - 1) {
            ref = ref.next;
            k++;
        }
        newNode.next = ref.next;
        ref.next = newNode;
    }

    /** Удаление элемента с головы списка */
    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        head = head.next;
    }

    /** Удаление последнего элемента списка */
    public void removeLast() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node ref = head;
        while (ref.next.next != null) {
            ref = ref.next;
        }
        ref.next = null;
    }

    /** Удаление из списка элемента с указанным номером (1-based) */
    public void remove(int position) {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }
        if (position == 1) {
            removeFirst();
            return;
        }
        Node ref = head;
        int k = 1;
        while (ref.next != null && k < position - 1) {
            ref = ref.next;
            k++;
        }
        if (ref.next == null) {
            System.out.println("Позиция за пределами списка!");
            return;
        }
        ref.next = ref.next.next;
    }

    // ============================================================
    //  б) Методы с использованием рекурсии
    // ============================================================

    /** Рекурсивный ввод с головы */
    public void createHeadRec(int count) {
        if (count <= 0) return;
        System.out.print("  Элемент: ");
        int val = sc.nextInt();
        createHeadRec(count - 1);
        // После раскрутки рекурсии: элементы вставляются в порядке ввода
        head = new Node(val, head);
    }

    /** Рекурсивный ввод с хвоста */
    public void createTailRec(int count) {
        if (count <= 0) return;
        System.out.print("  Элемент: ");
        int val = sc.nextInt();
        addLast(val);
        createTailRec(count - 1);
    }

    /** Рекурсивный вывод: возвращается строка */
    public String toStringRec() {
        return "[" + toStringRecHelper(head) + "]";
    }

    private String toStringRecHelper(Node node) {
        if (node == null) return "";
        if (node.next == null) return String.valueOf(node.value);
        return node.value + ", " + toStringRecHelper(node.next);
    }

    // ============================================================
    //  Тестирование
    // ============================================================

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // Создание списка с хвоста (цикл)
        System.out.println("=== createTail (цикл) ===");
        list.createTail(4);
        System.out.println("Список: " + list);

        // AddFirst
        System.out.println("\n=== addFirst(99) ===");
        list.addFirst(99);
        System.out.println("Список: " + list);

        // AddLast
        System.out.println("\n=== addLast(77) ===");
        list.addLast(77);
        System.out.println("Список: " + list);

        // Insert (позиция 3)
        System.out.println("\n=== insert(3, 55) ===");
        list.insert(3, 55);
        System.out.println("Список: " + list);

        // RemoveFirst
        System.out.println("\n=== removeFirst() ===");
        list.removeFirst();
        System.out.println("Список: " + list);

        // RemoveLast
        System.out.println("\n=== removeLast() ===");
        list.removeLast();
        System.out.println("Список: " + list);

        // Remove (позиция 2)
        System.out.println("\n=== remove(2) ===");
        list.remove(2);
        System.out.println("Список: " + list);

        // Рекурсивный вывод
        System.out.println("\n=== toStringRec() ===");
        System.out.println("Список: " + list.toStringRec());

        // Рекурсивное создание с хвоста
        System.out.println("\n=== createTailRec (рекурсия, 3 элемента) ===");
        MyLinkedList list2 = new MyLinkedList();
        list2.createTailRec(3);
        System.out.println("Список: " + list2.toStringRec());

        // Рекурсивное создание с головы
        System.out.println("\n=== createHeadRec (рекурсия, 3 элемента) ===");
        MyLinkedList list3 = new MyLinkedList();
        list3.createHeadRec(3);
        System.out.println("Список: " + list3.toStringRec());
    }
}