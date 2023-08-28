import java.io.*;
import java.util.*;


public class Main{

    static int N ;
    static int[][] arr;

    static int BFS(int F){
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(F);
        int[] ch = new int[N+1];
        int[] result = new int[N+1];
        ch[F]=1;
        while(!Q.isEmpty()){
            int a = Q.poll();
            for(int i=1;i<=N;i++){
                if(arr[a][i]==1 && ch[i]==0){
                    Q.offer(i);
                    ch[i]=1;
                    result[i]=result[a]+1;
                }


            }

        }
        int sum=0;
        for(int x: result){
            sum+=x;
        }

        return sum;

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b]=arr[b][a]=1;
        }

        int min = Integer.MAX_VALUE;
        int answer=0;
        for(int i=1;i<=N;i++){
            int min_ =BFS(i);
            if(min_<min) {
                min =min_;
                answer =i;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }


}
