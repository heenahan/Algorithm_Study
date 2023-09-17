import java.io.*;
import java.util.*;

class Node {
    private int x;
    private int y;
    private int w;

    public Node(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }
}

public class Main {

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int idx = 0;
        while (true) {
            idx++;
            int n = Integer.parseInt(br.readLine());
            if (n <= 0) break;
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            bw.write("Problem " + idx + ": " + dijkstra(n, arr));
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    static int dijkstra(int n, int[][] arr) {
        int[][] weigiht = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(weigiht[i], Integer.MAX_VALUE);
        }
        weigiht[0][0] = arr[0][0];
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(Node::getW));
        q.add(new Node(0, 0, arr[0][0]));
        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();
            int w = v.getW();

            if (weigiht[x][y] < w) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                int nw = arr[nx][ny] + w;
                if (weigiht[nx][ny] > nw) {
                    q.add(new Node(nx, ny, nw));
                    weigiht[nx][ny] = nw;
                }
            }
        }
        return weigiht[n - 1][n - 1];
    }
}