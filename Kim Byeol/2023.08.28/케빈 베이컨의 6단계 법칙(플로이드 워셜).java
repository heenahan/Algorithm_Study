import java.util.*;
import java.io.*;

class Main {

    static int[][] map;
    static int INF=129;
    static int insa;
    static int friends = 120;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        int M = inputs[1];

        map = new int[N+1][N+1];

        init(N);

        for (int i = 0; i < M; i++) {
            int[] relation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int friend1 = relation[0];
            int friend2 = relation[1];

            map[friend1][friend2] = 1;
            map[friend2][friend1] = 1;
        }
        makeFriends(N);
        makeInsa(N);
        System.out.println(insa);

    }

    static void init(int N) {
        for (int i = 1; i <=N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = INF;
            }
        }
    }

    static void makeFriends(int N) {
        for (int m = 1; m <= N; m++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][m] + map[m][j]) {
                        map[i][j] = map[i][m] + map[m][j];
                        //map[j][i] = map[i][j];
                    }
                }
            }
        }
    }

    static void makeInsa(int N) {
        for (int i = 1; i <= N; i++) {
            int relations = 0;
            for (int j = 1; j <= N; j++) {
                relations += map[i][j];
            }
            if (friends > relations) {
                friends = relations;
                insa = i ;
            }
        }
    }
}
