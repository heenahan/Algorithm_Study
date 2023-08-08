import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(v->Integer.parseInt(v)).toArray();

        int K = inputs[0];
        int N = inputs[1];

        List<Integer> lansuns = new ArrayList<>();

        long max = 0;

        for(int i=0;i<K ;i++) {
            String youngsikLan = br.readLine();
            lansuns.add(Integer.parseInt(youngsikLan));
            max = Math.max(max,Integer.parseInt(youngsikLan));
        }
        
        // (가장 작은 길이+ 가장 큰 길이) /2
        long r = max;
        long l = 1;
        long answer =0;
        while(l<= r) {
            long cnt=0;
            long mid = (l+r)/2;

            for(int i=0;i<lansuns.size();i++){
                cnt+= lansuns.get(i)/mid;
            }

            if(cnt >= N) {
                answer = mid;
                l=mid+1;
            }
            else {
                r = mid-1;
            }
        }
        System.out.println(answer);

    }

}
