
public class Test {
    public static void main(String[] args) {
        // Создание отдельных узлов
        Node n4 = new Node(4, null);        // хвост
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);          // голова

        Node head = n1; // Breakpoint 1: head -> [1] -> [2] -> [3] -> [4] -> null

        // Вывод списка
        System.out.println("Вывод списка:");
        Node ref = head; // Breakpoint 2: начинаем обход с головы
        while (ref != null) {
            System.out.print(ref.value + " -> ");
            ref = ref.next; // Breakpoint 3: переход к следующему узлу
        }
        System.out.println("null");
    }
}