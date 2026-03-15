public class CreateFromHead {
    public static void main(String[] args) {
        // Способ "с головы": каждый новый узел становится головой
        // Старая голова становится полем next нового узла

        Node head = new Node(5, null);           // Breakpoint 1: head=[5]->null
        head = new Node(4, head);                 // head=[4]->[5]->null
        head = new Node(3, head);                 // head=[3]->[4]->[5]->null
        head = new Node(2, head);                 // Breakpoint 2: head=[2]->[3]->[4]->[5]->null
        head = new Node(1, head);                 // head=[1]->[2]->[3]->[4]->[5]->null

        // Вывод списка
        System.out.println("Список (создан с головы):");
        Node ref = head;                          // Breakpoint 3: начало обхода
        while (ref != null) {
            System.out.print(ref.value + " -> ");
            ref = ref.next;
        }
        System.out.println("null");
    }
}