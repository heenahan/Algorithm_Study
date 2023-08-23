import java.io.*;
import java.util.*;

class XY {
    private int x; private int y;

    public XY(int x, int y) {
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

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int n;
    static int[][] country;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        country = new int[n][n];
        for (int i = 0; i < n; i++) {
            country[i] = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
        boolean[][] visited = new boolean[n][n];
        int island = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (country[i][j] > 0 && !visited[i][j]) {
                    boundIsland(i, j, island, visited);
                    island++;
                }
            }
        }
        int[][] bridges; int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 섬이라면
                if (country[i][j] > 0) {
                    bridges = new int[n][n];
                    min = Math.min(min, bridge(i, j, country[i][j], bridges) - 2);
                }
            }
        }

        bw.write(String.valueOf(min));

        br.close(); bw.close();
    }

    static void boundIsland(int i, int j, int island, boolean[][] visited) {
        Queue<XY> q = new LinkedList<>();
        visited[i][j] = true;
        country[i][j] = island;
        q.add(new XY(i, j));

        while (!q.isEmpty()) {
            XY v = q.poll();
            int x = v.getX(); int y = v.getY();

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k]; int ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny] || country[nx][ny] <= 0) continue;
                country[nx][ny] = island;
                visited[nx][ny] = true;
                q.add(new XY(nx, ny));
            }
        }
    }

    static int bridge(int i, int j, int island, int[][] bridges) {
        Queue<XY> q = new LinkedList<>();
        bridges[i][j] = 1;
        q.add(new XY(i, j));

        while (!q.isEmpty()) {
            XY v = q.poll();
            int x = v.getX(); int y = v.getY();

            // 바다가 아니고 다른 섬일때
            if (country[x][y] > 0 && country[x][y] != island) return bridges[x][y];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k]; int ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (bridges[nx][ny] > 0 || country[nx][ny] == island) continue;
                q.add(new XY(nx, ny));
                bridges[nx][ny] = bridges[x][y] + 1;
            }
        }
        return Integer.MAX_VALUE;
    }
}