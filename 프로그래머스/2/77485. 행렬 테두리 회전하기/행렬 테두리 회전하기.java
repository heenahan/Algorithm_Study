import java.util.*;

class Solution {
    
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { 1, 0, -1, 0 };
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] before = new int[rows][columns];
        List<Integer> answer = new ArrayList<>();
        
        int cnt = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                before[i][j] = cnt++;
            }
        }
        for (int[] query : queries) {        
            int[][] next = new int[rows][columns];
            // before을 next에 모두 복사
            for (int i = 0; i < rows; i++) {
                next[i] = Arrays.copyOf(before[i], columns);
            }
            
            int x = query[0] - 1; int y = query[1] - 1; 
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; int ny = y + dy[i];
                while (nx >= query[0] - 1 && nx <= query[2] - 1 && ny >= query[1] - 1 && ny <= query[3] - 1) {
                    min = Math.min(min, before[x][y]);
                    next[nx][ny] = before[x][y];
                    x = nx; y = ny;
                    nx += dx[i]; ny += dy[i];
                }
            }
            answer.add(min);
            before = next;
        }
        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}