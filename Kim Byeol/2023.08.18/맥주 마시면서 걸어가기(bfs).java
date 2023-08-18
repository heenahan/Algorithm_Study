import java.util.*;
import java.io.*;

class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Node start = makeNode(br);

            List<Node> stores = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                Node store = makeNode(br);
                stores.add(store);
            }

            Node festival = makeNode(br);

            visited = new boolean[n];
            System.out.println(bfs(stores, start, festival));
        }

    }

    static Node makeNode(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Node(x, y);

    }

    static String bfs(List<Node> stores, Node start, Node festival) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Node before = q.poll();

            if (check1000m(before, festival)) return "happy";

            for (int i = 0; i < stores.size(); i++) {
                if (!visited[i]) {
                    if (check1000m(before, stores.get(i))) {
                        visited[i] = true;
                        q.add(stores.get(i));
                    }
                }
            }
        }

        return "sad";

    }

    static boolean check1000m(Node start, Node end) {
        int street = Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
        return street <= 1000;
    }

}
