import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main{
    static Set<String> set = new HashSet<>();
    static boolean[] visited ;
    static int[][] s ;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        s = new int[n][n];
        visited = new boolean[n];

        for (int i=0; i<n ;i++){
            s[i]= Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }

        dfs(n/2,0, 0);

        System.out.println(min);
    }

    static void dfs(int level, int location, int start) {
        if(level == location) {
            min = Math.min(min, makeTeam());
            return;
        }
        // 1 2 == 2 1
        for(int i=start;i<level*2;i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(level,location+1,i+1);
            visited[i] = false;

        }
    }

    static int makeTeam() {
        int teamA = 0;
        int teamB = 0;

        for(int i=0 ; i< visited.length-1;i++) {
            if(visited[i]){
                for(int j=i+1;j<visited.length;j++){
                    if(visited[j]){
                        teamA += s[i][j] + s[j][i];
                    }
                }
            } else{
                for(int j=i+1;j<visited.length;j++){
                    if(!visited[j]){
                        teamB += s[i][j] + s[j][i];
                    }
                }
            }
        }
        return Math.abs(teamA - teamB);
    }
}
