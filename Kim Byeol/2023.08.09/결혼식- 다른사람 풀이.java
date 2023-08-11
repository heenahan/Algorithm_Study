import java.util.*;
import java.io.*;

class Main {

    static List<List<Integer>> relations = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            relations.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] friends = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = friends[0];
            int to = friends[1];

            relations.get(from).add(to);
            relations.get(to).add(from);
        }

        dfs(1, 0);

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            if (visited[i]) {
                answer++;
            }
        }

        System.out.println(answer);
    }
    static void dfs(int from, int level) {
        if (level == 2) {
            return;
        }


        for (int to : relations.get(from)) {
            visited[to] = true;
            dfs(to, level + 1);
        }

    }

}
