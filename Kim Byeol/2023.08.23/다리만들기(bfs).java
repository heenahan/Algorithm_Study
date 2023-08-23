import java.util.*;
import java.io.*;

class Main {

    static int[][] map ;
    static boolean[][] visited;

    static int[] cx = {-1,1,0,0};
    static int[] cy = {0,0,-1,1};

    static int check=2;
    static int answer = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x =x;
            this.y=y;
            this.cnt=cnt;
        }

    }

    public static void main(String[] agrs) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map= new int[N][N];
        visited = new boolean[N][N];

        for(int i=0;i<N;i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        // 섬에 번호 붙이기
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j]==1) {
                    makeIsland(i,j,N);
                }
            }
        }

        // 다리 만들기
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j]>=2) {
                    visited = new boolean[N][N];
                    makeBridge(i,j,N);
                }
            }
        }

        System.out.println(answer);

    }
    static void makeIsland(int x, int y, int N) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0));

        map[x][y] = check;
        while(!q.isEmpty()) {
            Node n = q.poll();

            for(int i=0;i<4;i++) {
                int X = n.x + cx[i];
                int Y = n.y + cy[i];

                if(X<0 || X>=N || Y<0 || Y >=N || visited[X][Y]) {
                    continue;
                }

                if(map[X][Y]==1) {
                    map[X][Y]=check;
                    visited[X][Y]=true;
                    q.offer(new Node(X,Y,0));
                }
            }


        }
        check++;
    }

    static void makeBridge(int x, int y, int N) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0));
        visited[x][y] = true;
        int location = map[x][y];
        while(!q.isEmpty()) {
            Node n = q.poll();

            for(int i=0;i<4;i++) {
                int X = n.x + cx[i];
                int Y = n.y + cy[i];

                if(X<0 || X>=N || Y<0 || Y >=N || visited[X][Y] || map[X][Y]==location) {
                    continue;
                }

                if(map[X][Y]==0) {
                    visited[X][Y]=true;
                    q.offer(new Node(X,Y,n.cnt+1));
                }else{
                    answer = Math.min(answer,n.cnt);
                    return;
                }
            }

        }
    }

}
