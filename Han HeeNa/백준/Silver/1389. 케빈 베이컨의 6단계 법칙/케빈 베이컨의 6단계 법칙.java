import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static boolean[][] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        graph = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            String[] input2 = br.readLine().split(" ");
            int a = Integer.parseInt(input2[0]);
            int b = Integer.parseInt(input2[1]);

            graph[a][b] = true; graph[b][a] = true;
        }
        int min = Integer.MAX_VALUE; int idx = 0;
        for (int i = 1; i <= n; i++) {
            visited = new int[n + 1];
            bfs(i);
            int kevin = 0;
            for (int v : visited) kevin += v - 1;
            if (min > kevin) {
                min = kevin; idx = i;
            }
        }

        bw.write(String.valueOf(idx));

        br.close(); bw.close();
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start); visited[start] = 1;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 1; i <= n; i++) {
                if (graph[v][i] && visited[i] <= 0) {
                    q.add(i);
                    visited[i] = visited[v] + 1;
                }
            }
        }
    }
}