import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        Queue<Integer> q = new PriorityQueue<>((o1, o2) -> Integer.compare(Math.abs(o1), Math.abs(o2)) * -1);
        String[] input2  = br.readLine().split(" ");
        for (String s : input2) {
            q.add(Integer.parseInt(s));
        }

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int max = q.peek(); int step = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v > 0) positive.add(v);
            else negative.add(v);

            if (positive.size() >= m) {
                step += positive.get(0) * 2;
                positive = new ArrayList<>();
                continue;
            }
            if (negative.size() >= m) {
                step += negative.get(0) * 2 * -1;
                negative = new ArrayList<>();
            }
        }
        if (!positive.isEmpty()) step += positive.get(0) * 2;
        if (!negative.isEmpty()) step += negative.get(0) * 2 * -1;

        if (max > 0) bw.write(String.valueOf(step - max));
        else bw.write(String.valueOf(step - (max * -1)));
        br.close(); bw.close();
    }
}