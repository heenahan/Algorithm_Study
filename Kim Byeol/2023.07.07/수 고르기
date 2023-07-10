import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int MIN = Integer.MAX_VALUE;

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] array = new int[n];
        //사용자로부터 수열을 입력받는다.
        for(int i=0;i<n;i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        // 정렬 작은수부터 큰수
        Arrays.sort(array);

        //투포인터의 끝
        int en=0;
        //투포인터 알고리즘을 이용하여 순회한다.
        for(int st=0;st<n;st++){
            while( en <= n-1 && array[en]-array[st]<m) en++;
            if(en == n) break;
            MIN=Math.min(MIN,array[en]-array[st]);
        }

        bw.write(Integer.toString(MIN));
        bw.flush();
        bw.close();
        br.close();
    }
}
