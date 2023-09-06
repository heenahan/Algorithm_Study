import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] buildings = new int[n];
        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                int idx = stack.pop();
                cnt += (i - idx - 1);
            }
            stack.push(i); // 인덱스 넣음
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            cnt += (n - idx - 1);
        }
        bw.write(String.valueOf(cnt));

        br.close(); bw.close();
    }
}