import java.io.*;
import java.util.*;

public class Main {

    int answer = 0;
    List<int[][]> blank = new ArrayList<>(); // 0 오른쪽, 1 아래, 2 대각선
    List<int[]> direction = new ArrayList<>(); // 가로, 세로, 대각선

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] home = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            for (int j = 0; j < n; j++) home[i][j] = arr[j];
        }

        T.init();
        T.backTracking(n, 0, 1, 0, home);

        bw.write(String.valueOf(T.answer));

        br.close(); bw.close();
    }
    // x, y 현재 위치 direct 방향
    public void backTracking(int n, int x, int y, int direct, int[][] home) {
        if (x == n - 1 && y == n - 1) {
            answer++;
            return;
        }
        int[] available = direction.get(direct);
        for (int av : available) {
            int[][] bl = blank.get(av);
            boolean visited = true;
            for (int[] b : bl) {
                int nx = x + b[0]; int ny = y + b[1];
                // 벽을 넘어가거나 벽이라면
                if (nx >= n || ny >= n || home[nx][ny] == 1) {
                    visited = false;
                    break;
                }
            }
            if (visited) backTracking(n, x + bl[0][0], y + bl[0][1], av, home);
        }
    }

    private void init() {
        // 비어있어야할 곳
        blank.add(new int[][]{ {0, 1} });
        blank.add(new int[][]{ {1, 0} });
        blank.add(new int[][]{ {1, 1}, {0, 1}, {1, 0} });

        // 방향마다 갈 수 있는 방향
        direction.add(new int[]{ 0, 2 }); // 가로 : 가로 대각선
        direction.add(new int[]{ 1, 2 }); // 세로 : 세로 대간선
        direction.add(new int[]{ 0, 1, 2 }); // 대각선 : 모두
    }

}