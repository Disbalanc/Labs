class Node {
    int value;
    Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

public class CreateFromTail {
    public static void main(String[] args) {
        // Способ "с хвоста": создаём первый элемент (будущую голову),
        // затем последовательно добавляем в конец

        Node head = new Node(1, null);            // Breakpoint 1: head=[1]->null
        Node tail = head;                         // tail указывает на текущий конец

        tail.next = new Node(2, null);            // head=[1]->[2]->null
        tail = tail.next;

        tail.next = new Node(3, null);            // Breakpoint 2: head=[1]->[2]->[3]->null
        tail = tail.next;

        tail.next = new Node(4, null);
        tail = tail.next;

        tail.next = new Node(5, null);
        tail = tail.next;                         // head=[1]->[2]->[3]->[4]->[5]->null

        // Вывод списка
        System.out.println("Список (создан с хвоста):");
        Node ref = head;                          // Breakpoint 3
        while (ref != null) {
            System.out.print(ref.value + " -> ");
            ref = ref.next;
        }
        System.out.println("null");
    }
}