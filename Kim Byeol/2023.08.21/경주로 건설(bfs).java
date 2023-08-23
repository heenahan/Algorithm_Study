import java.util.*;

class Solution {

    static int[] cx = {-1, 1, 0, 0};
    static int[] cy = {0, 0, -1, 1};

    static String[] arrow = {"up", "down", "right", "left"};
    static int min = Integer.MAX_VALUE;

    static class Node {
        String check;
        int x;
        int y;
        int cost;

        public Node(String check, int x, int y, int cost) {
            this.check = check;
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int[][][] visited;

    public int solution(int[][] board) {
        int N = board.length;
        // visited에는 4가지 방향에 대한 정보가 모두 저장되며 따라서 3차원 배열이다.
        visited = new int[N][N][4];
        bfs(board, N);
        
        return min;


    }

    static void bfs(int[][] board, int N) {
        Node start = new Node("start", 0, 0, 0);
        Queue<Node> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Node n = q.poll();


            if (n.x == N - 1 && n.y == N - 1) {
                min = min > n.cost ? n.cost : min;
            }

            for (int i = 0; i < 4; i++) {
                int X = n.x + cx[i];
                int Y = n.y + cy[i];
                // 현재 위치에서 어디의 방향으로 전환되었는가?
                String future = arrow[i];

                if (X < 0 || X >= N || Y < 0 || Y >= N || board[X][Y] == 1) {
                    continue;
                }

                // 처음 시작이거나 이전 방향과 같다면
                if (n.check.equals("start") || n.check.equals(future)) {
                    // 0이거나 혹은 기존에 저장된 해당 방향에 대한 값보다 지금의 값이 작다면 갱신
                    if (visited[X][Y][i] == 0 || visited[X][Y][i] >= n.cost + 100) {
                        visited[X][Y][i] = n.cost + 100;
                        q.add(new Node(future, X, Y, n.cost + 100));
                        continue;
                    }

                }
                // 이전 방향과 같지 않다면
                if (visited[X][Y][i] == 0 || visited[X][Y][i] >= n.cost + 600) {
                    visited[X][Y][i] = n.cost + 600;
                    q.add(new Node(future, X, Y, n.cost + 600));
                }

            }
        }
    }
    
}
