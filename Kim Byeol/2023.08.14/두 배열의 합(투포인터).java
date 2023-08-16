import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        long[] A = Arrays.stream(br.readLine().split(" ")). mapToLong(v-> Long.parseLong(v)).toArray();

        int m = Integer.parseInt(br.readLine());
        long[] B = Arrays.stream(br.readLine().split(" ")). mapToLong(v-> Long.parseLong(v)).toArray();

        List<Long> subA = new ArrayList<>();
        for(int i=0;i<n;i++){
            long tmp =0;
            for(int j=i;j<n;j++){
                tmp+=A[j];
                subA.add(tmp);
            }
        }

        List<Long> subB = new ArrayList<>();
        for(int i=0;i<m;i++){
            long tmp =0;
            for(int j=i;j<m;j++){
                tmp+=B[j];
                subB.add(tmp);
            }
        }

        Collections.sort(subA);
        Collections.sort(subB);

        int pointerA = 0;
        int pointerB = subB.size()-1;
        long answer =0;

        while(pointerA < subA.size() && pointerB >=0){
            long targetA = subA.get(pointerA);
            long targetB = subB.get(pointerB);
            long sum = subA.get(pointerA) + subB.get(pointerB);

            long cntA = 0;
            long cntB = 0;

            if(sum == T) {
                while(pointerA < subA.size() && targetA == subA.get(pointerA)){
                    cntA++;
                    pointerA++;
                }

                while(pointerB >= 0 && targetB == subB.get(pointerB)){
                    cntB++;
                    pointerB--;
                }

            }
            answer+= cntA * cntB;

            if(sum> T) {
                pointerB --;
            }

            if(sum < T) {
                pointerA++;
            }

        }

        System.out.println(answer);
    }
}
