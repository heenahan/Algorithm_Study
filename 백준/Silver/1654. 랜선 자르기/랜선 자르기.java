import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] kn = br.readLine().split(" ");
        int k = Integer.parseInt(kn[0]);
        int n = Integer.parseInt(kn[1]);

        int[] lan = new int[k];
        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }
        long max = solution(lan, n);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }

    static long solution(int[] lan, int count) {
        long st = 0; long ed = Integer.MAX_VALUE;
        long mid = (st + ed) / 2 + 1;
        while (ed > st) {
            int sum = 0;
            for (int l : lan) {
                sum += l / mid;
            }
            if (sum >= count) st = mid;
            else ed = mid - 1;
            mid = (st + ed) / 2 + 1;
        }
        return st;
    }
}