import java.io.*;
import java.util.*;

class Node {
    private int x; private int y; // 좌표

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] input1 = br.readLine().split(" ");
            int homeX = Integer.parseInt(input1[0]); int homeY = Integer.parseInt(input1[1]);
            Node home = new Node(homeX, homeY);

            List<Node> convenience = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                String[] input2 = br.readLine().split(" ");
                int x = Integer.parseInt(input2[0]); int y = Integer.parseInt(input2[1]);
                convenience.add(new Node(x, y));
            }
            String[] input3 = br.readLine().split(" ");
            int rockX = Integer.parseInt(input3[0]); int rockY = Integer.parseInt(input3[1]);
            Node rock = new Node(rockX, rockY);

            boolean possible = bfs(home, convenience, rock);
            if (possible) bw.write("happy");
            else bw.write("sad");
            bw.newLine();
        }

        br.close(); bw.close();
    }

    static boolean bfs(Node home, List<Node> convenience, Node rock) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[convenience.size()];
        q.add(home);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX(); int y = v.getY();

            int rockX = rock.getX(); int rockY = rock.getY(); // 락 페스티벌 갈 수 있는지
            int manhattan = Math.abs(x - rockX) + Math.abs(y - rockY); // 맨해튼 거리
            if (drinkBeer(manhattan) <= 20) return true;

            // 편의점에 들려야 하는 경우
            for (int i = 0; i < convenience.size(); i++) {
                Node next  = convenience.get(i);
                int nx = next.getX(); int ny = next.getY();
                int manhattanC = Math.abs(x - nx) + Math.abs(y - ny); // 맨해튼 거리
                // 방문했거나 방문하지 못하는 경우
                if (visited[i] || drinkBeer(manhattanC) > 20) continue;
                visited[i] = true;
                q.add(next);
            }
        }
        return false;
    }

    static int drinkBeer(int manhattan) {
        int beer = manhattan / 50;
        if (manhattan % 50 > 0) return beer + 1;
        return beer;
    }
}