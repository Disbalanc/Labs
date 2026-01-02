public class Main {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ 5 ЗАДАЧ НА НАСЛЕДОВАНИЕ ===");
        System.out.println();

        // Задача 1
        System.out.println("ЗАДАЧА 1: Наследование с одним текстовым полем");
        System.out.println("Создаем объект суперкласса SuperText:");
        SuperText superText = new SuperText("Привет из суперкласса");
        System.out.println(superText);

        System.out.println("\nСоздаем объект подкласса SubText с одним аргументом:");
        SubText subText1 = new SubText("Текст для подкласса");
        System.out.println(subText1);

        System.out.println("\nСоздаем объект подкласса SubText с двумя аргументами:");
        SubText subText2 = new SubText("Первый текст", "Второй текст");
        System.out.println(subText2);
        System.out.println();

        // Задача 2
        System.out.println("ЗАДАЧА 2: Наследование с переопределением методов");
        System.out.println("Создаем объект суперкласса SuperAssign:");
        SuperAssign superAssign = new SuperAssign("Исходный текст");
        System.out.println("Длина текста: " + superAssign.getLength());

        System.out.println("\nСоздаем объект подкласса SubAssign:");
        SubAssign subAssign = new SubAssign(42, "Текст из конструктора");
        System.out.println("Текст: " + subAssign.getText());
        System.out.println("Число: " + subAssign.getNumber());
        System.out.println("Длина текста: " + subAssign.getLength());

        System.out.println("\nТестируем перегруженные методы:");
        subAssign.assign(); // без параметров
        subAssign.assign("Новый текст"); // текстовый параметр
        subAssign.assign(100); // целочисленный параметр
        subAssign.assign("Комбинированный", 200); // два параметра
        System.out.println();

        // Задача 3
        System.out.println("ЗАДАЧА 3: Цепочка наследования из трех классов");
        System.out.println("Создаем объект первого класса (суперкласса):");
        FirstClass first = new FirstClass(10);
        System.out.println(first);

        System.out.println("\nСоздаем объект второго класса:");
        SecondClass second = new SecondClass(20, 'B');
        System.out.println(second);

        System.out.println("\nСоздаем объект третьего класса:");
        ThirdClass third = new ThirdClass(30, 'C', "Третий класс");
        System.out.println(third);

        System.out.println("\nТестируем перегруженные методы assign:");
        first.assign(111);
        System.out.println("После first.assign(111): " + first);

        second.assign(222, 'X');
        System.out.println("После second.assign(222, 'X'): " + second);

        third.assign(333, 'Y', "Новый текст");
        System.out.println("После third.assign(333, 'Y', 'Новый текст'): " + third);
        System.out.println();

        // Задача 4
        System.out.println("ЗАДАЧА 4: Цепочка наследования с конструкторами копий");
        System.out.println("Создаем объекты через параметризованные конструкторы:");
        ChainFirst cf1 = new ChainFirst('A');
        ChainSecond cs1 = new ChainSecond('B', "Текст");
        ChainThird ct1 = new ChainThird('C', "Еще текст", 100);

        System.out.println("ChainFirst: " + cf1);
        System.out.println("ChainSecond: " + cs1);
        System.out.println("ChainThird: " + ct1);

        System.out.println("\nСоздаем объекты через конструкторы копий:");
        ChainFirst cf2 = new ChainFirst(cf1);
        ChainSecond cs2 = new ChainSecond(cs1);
        ChainThird ct2 = new ChainThird(ct1);

        System.out.println("Копия ChainFirst: " + cf2);
        System.out.println("Копия ChainSecond: " + cs2);
        System.out.println("Копия ChainThird: " + ct2);
        System.out.println();

        // Задача 5
        System.out.println("ЗАДАЧА 5: Два подкласса от одного суперкласса");
        System.out.println("Создаем объекты каждого класса:");
        SuperDisplay superObj = new SuperDisplay("Текст из суперкласса");
        SubDisplay1 subObj1 = new SubDisplay1("Текст из SubDisplay1", 123);
        SubDisplay2 subObj2 = new SubDisplay2("Текст из SubDisplay2", 'Z');

        System.out.println("\nВызываем display() для каждого объекта:");
        superObj.display();
        subObj1.display();
        subObj2.display();

        System.out.println("\nПроверяем полиморфизм:");
        SuperDisplay polymorphic;

        polymorphic = superObj;
        System.out.print("superObj через SuperDisplay: ");
        polymorphic.display();

        polymorphic = subObj1;
        System.out.print("subObj1 через SuperDisplay: ");
        polymorphic.display();

        polymorphic = subObj2;
        System.out.print("subObj2 через SuperDisplay: ");
        polymorphic.display();

        System.out.println("\n=== ВСЕ ЗАДАЧИ ВЫПОЛНЕНЫ ===");
    }
}