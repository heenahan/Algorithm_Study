import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.fill(dp, 1);
        int result=0;

        for(int i = 1; i < n; i++){
            // 입력된 배열 값과 이전의 값들을 비교한다.
            for(int j = 0 ; j < i; j++){
                // 비교한 값보다 큰경우
                if(arr[i] > arr[j]){
                    // 비교한 값의 dp값 + 1, 기존의 dp값 중 큰 값을 저장한다.
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    // 최장 길이인 답을 구하기 위해서 result값에
                    // 지금까지 구한 dp값 중 가장 큰 값을 저장한다.
                }
            }
        }

        result = Arrays.stream(dp).max().orElse(0);
        Stack<Integer> st = new Stack<>() ;

        int targetIndex = result;
        for(int j=n-1;j>=0;j--) {
           if(dp[j]==targetIndex){
               st.push(arr[j]);
               targetIndex--;
           }
        }

        bw.write(result+"\n");
        while(!st.isEmpty()){
            bw.write(st.pop()+" ");
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
