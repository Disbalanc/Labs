import java.util.*;
import java.io.*;

//1205. На метро или пешком?
//Ограничение времени: 1.0 секунды
//Ограничение памяти: 64 МБ
//Представьте себя в большом городе. Вы хотите попасть из точки A в точку B. Для этого вы можете идти пешком или использовать метро. Перемещение с помощью метро быстрее, но входить в метро и выходить из него можно только на станциях. Чтобы сэкономить время, вы решили написать программу нахождения самого быстрого пути.
//Исходные данные
//Первая строка содержит два вещественных числа: скорость ходьбы и скорость перемещения с помощью метро. Скорости лежат в пределах от 0.5 до 10000. Вторая скорость всегда не меньше первой.
//Затем следует описание метро. В первой строке описания находится целое число N — количество станций метро (2 ≤ N ≤ 200). Следующие N строк содержат по два вещественных числа каждая (i-я строка содержит координаты i-й станции). Затем следует описание соединений между станциями. Каждое соединение определяется парой целых чисел — номерами соединённых станций. Соединения в списке не повторяются. Общее количество соединений не превышает 400. Список соединений завершает пара нулей.
//        Можно считать, что станции соединяются по прямой, так что время перемещения между станциями равно расстоянию между ними, делённому на скорость перемещения с помощью метро. Перемещаться между станциями можно в любую сторону. Время перемещения пешком между двумя точками равно расстоянию между ними, делённому на скорость ходьбы.
//        Отметим, что вход и выход из метро, а также смена поезда возможны только на станциях и не требуют дополнительного времени.
//Последними даны координаты точек A и B по паре координат в строке.
//Результат
//Первая строка должна содержать минимальное время, необходимое, чтобы попасть из точки A в точку B. Время должно быть выведено с точностью 10−6. Вторая строка описывает использование метро при передвижении. Она начинается количеством станций, которые нужно посетить, затем следует список номеров станций в порядке, в котором их нужно посетить.

public class Main {
    static class Edge {
        int to;
        double weight;
        boolean isMetro;
        Edge(int to, double weight, boolean isMetro) {
            this.to = to;
            this.weight = weight;
            this.isMetro = isMetro;
        }
    }

    static class Vertex implements Comparable<Vertex> {
        int id;
        double dist;
        Vertex(int id, double dist) {
            this.id = id;
            this.dist = dist;
        }
        @Override
        public int compareTo(Vertex other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double walkSpeed = sc.nextDouble();
        double metroSpeed = sc.nextDouble();
        int N = sc.nextInt();
        double[][] stations = new double[N][2];
        for (int i = 0; i < N; i++) {
            stations[i][0] = sc.nextDouble();
            stations[i][1] = sc.nextDouble();
        }
        boolean[][] isMetro = new boolean[N][N];
        while (true) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            if (i == 0 && j == 0) break;
            i--; j--;
            isMetro[i][j] = true;
            isMetro[j][i] = true;
        }
        double[] A = new double[2];
        double[] B = new double[2];
        A[0] = sc.nextDouble();
        A[1] = sc.nextDouble();
        B[0] = sc.nextDouble();
        B[1] = sc.nextDouble();

        int totalVertices = N + 2;
        double[][] allPoints = new double[totalVertices][2];
        for (int i = 0; i < N; i++) {
            allPoints[i] = stations[i];
        }
        allPoints[N] = A;
        allPoints[N+1] = B;

        // Build graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < totalVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Add walking edges between every pair
        for (int i = 0; i < totalVertices; i++) {
            for (int j = i + 1; j < totalVertices; j++) {
                double dist = distance(allPoints[i], allPoints[j]);
                double time = dist / walkSpeed;
                graph.get(i).add(new Edge(j, time, false));
                graph.get(j).add(new Edge(i, time, false));
            }
        }

        // Add metro edges between connected stations
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isMetro[i][j]) {
                    double dist = distance(allPoints[i], allPoints[j]);
                    double time = dist / metroSpeed;
                    graph.get(i).add(new Edge(j, time, true));
                    graph.get(j).add(new Edge(i, time, true));
                }
            }
        }

        // Dijkstra
        double[] dist = new double[totalVertices];
        int[] prev = new int[totalVertices];
        boolean[] isMetroEdge = new boolean[totalVertices];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[N] = 0;
        prev[N] = -1;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(N, 0));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            int u = current.id;
            if (current.dist > dist[u]) continue;
            for (Edge e : graph.get(u)) {
                int v = e.to;
                double newDist = dist[u] + e.weight;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                    isMetroEdge[v] = e.isMetro;
                    pq.add(new Vertex(v, newDist));
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int cur = N+1;
        while (cur != -1) {
            path.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(path);

        // Build station list
        List<Integer> stationList = new ArrayList<>();
        for (int i = 1; i < path.size() - 1; i++) {
            int v = path.get(i);
            if (v < N) {
                if (isMetroEdge[v] || isMetroEdge[path.get(i+1)]) {
                    stationList.add(v);
                }
            }
        }

        // Output time
        System.out.printf("%.10f\n", dist[N+1]);

        // Output stations
        System.out.print(stationList.size());
        for (int s : stationList) {
            System.out.print(" " + (s+1));
        }
        System.out.println();
    }

    static double distance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx*dx + dy*dy);
    }
}