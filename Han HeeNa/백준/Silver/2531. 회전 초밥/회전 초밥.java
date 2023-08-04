import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int d = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        int c = Integer.parseInt(input[3]);

        int[] sushi = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) sushi[i] = Integer.parseInt(br.readLine());

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }
        for (int i = k; i < n + k; i++) {
            map.put(sushi[i - k], map.get(sushi[i - k]) - 1);
            map.put(sushi[i % n], map.getOrDefault(sushi[i % n], 0) + 1);
            if (map.get(sushi[i - k]) == 0) map.remove(sushi[i - k]);
            if (!map.containsKey(c)) max = Math.max(max, map.size() + 1);
            else max = Math.max(max, map.size());
        }

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}