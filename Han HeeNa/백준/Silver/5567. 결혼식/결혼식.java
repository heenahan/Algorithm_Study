import java.io.*;
import java.util.*;

class Node {
    private int v; private int depth;

    public Node(int v, int depth) {
        this.v = v;
        this.depth = depth;
    }

    public int getV() {
        return v;
    }

    public int getDepth() {
        return depth;
    }
}

public class Main {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 친구 수
        int m = Integer.parseInt(br.readLine()); // 리스트의 길이

        visited = new boolean[n + 1];
        map.put(1, new ArrayList<>());

        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            if (!map.containsKey(a)) map.put(a, new ArrayList<>());
            if (!map.containsKey(b)) map.put(b, new ArrayList<>());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        int result = bfs();

        bw.write(String.valueOf(result));

        br.close();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new LinkedList();
        q.add(new Node(1, 0));
        visited[1] = true;
        int count = 0;

        while (!q.isEmpty()) {
            Node v = q.poll();
            int value = v.getV();
            int depth = v.getDepth();
            count++;

            if (depth >= 2) continue; // 친구의 친구부터 방문 안함
            List<Integer> friends = map.get(value);
            for (int friend : friends) {
                if (visited[friend]) continue;
                q.add(new Node(friend, depth + 1));
                visited[friend] = true;
            }
        }
        return count - 1;
    }

}