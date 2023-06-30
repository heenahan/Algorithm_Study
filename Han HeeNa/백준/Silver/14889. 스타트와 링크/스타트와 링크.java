import java.io.*;
import java.util.*;

public class Main {

    static int[][] power;
    static boolean[] visited;
    static int[] team;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        power = new int[n][n];
        team = new int[n / 2];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            power[i] = Arrays.copyOf(line, line.length);
        }

        T.backTracking(0, n);
        bw.write(String.valueOf(min));

        br.close();
        bw.close();
    }

    public void backTracking(int depth, int n) {
        if (depth == n / 2) {
            int start = 0;
            int link = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) { // 방문된 것이라면 스타트 팀
                    for (int j = 0; j < n; j++) {
                        if (visited[j]) start += power[i][j];
                    }
                } else { // 방문하지 않았다면 링크 팀
                    for (int j = 0; j < n; j++) {
                        if (!visited[j]) link += power[i][j];
                    }
                }
            }
            min = Math.min(min, Math.abs(start - link));
            return;
        }
        int start_i = depth != 0 ? team[depth - 1] : 0;
        for (int i = start_i; i < n; i++) {
            if (!visited[i]) {
                team[depth] = i;
                visited[i] = true;
                backTracking(depth + 1, n);
                visited[i] = false;
            }
        }
    }

}