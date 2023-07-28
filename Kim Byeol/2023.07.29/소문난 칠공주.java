import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int[] x = {0, 0, -1, 1};
    static int[] y = {1, -1, 0, 0};
    static int[] batchX = new int[25];
    static int[] batchY = new int[25];
    static char[][] batch;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        batch = new char[5][5];
        for (int i = 0; i < 5; i++) {
            batch[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 25; i++) {
            batchX[i] = i / 5;
            batchY[i] = i % 5;
        }

        combination(new int[7], 0, 0, 7);

        System.out.println(ans);

    }

    public static void combination(int[] comb, int index, int depth, int seven) {
        if (seven == 0) {
            bfs(comb);
            return;
        }

        if (depth == 25) {
            return;
        }

        comb[index] = depth;
        combination(comb, index + 1, depth + 1, seven - 1); // 선택
        combination(comb, index, depth + 1, seven); // 선택 안함
    }

    public static void bfs(int[] comb) {
        // 7명의 자리는 가로나 세로로 반드시 인접해 있어야 한다.
        // 이다솜파는 적어도 4명 이상
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[7];

        visited[0] = true;
        q.add(comb[0]);
        int dsCount = 0;
        int connect = 1;

        while (!q.isEmpty()) {

            int studentLocation = q.poll();

            char student = batch[batchX[studentLocation]][batchY[studentLocation]];

            if (student == 'S') {
                dsCount++;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 7; j++) {
                    if (!visited[j] && batchX[studentLocation] + x[i] == batchX[comb[j]]
                            && batchY[studentLocation] + y[i] == batchY[comb[j]]) {
                        visited[j] = true;
                        q.add(comb[j]);
                        connect++;

                    }
                }
            }
        }

        if (connect == 7 && dsCount >= 4) {
            ans++;
        }

    }

}
