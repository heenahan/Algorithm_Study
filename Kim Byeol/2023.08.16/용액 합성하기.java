import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [] An = Arrays.stream(br.readLine().split(" "))
                .mapToLong(v->Long.parseLong(v)).toArray();

        int r = N-1;
        int l = 0 ;

        long min = Integer.MAX_VALUE;
        while(l<r) {
            long S = An[l] + An[r];

            if(S > 0) {
                r--;
                min = checkMin(min, S);
                continue;
            }

            if(S<=0) { 
                l++;
                min = checkMin(min, S);
                continue;
            }
        }

        System.out.println(min);

    }

    static long checkMin(long min, long checkNum) {

        long absMin = Math.abs(min);
        long absCheck = Math.abs(checkNum);

        if(absMin >= absCheck) return checkNum;
        return min;

    }


}

