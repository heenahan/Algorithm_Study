import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> index = new HashMap<>();

        String[] nc = br.readLine().split(" ");
        int n = Integer.parseInt(nc[0]);
        int c = Integer.parseInt(nc[1]);
        String[] nums = br.readLine().split(" ");

        for (int i = 0; i < nums.length; i++) {
            int num = Integer.parseInt(nums[i]);
            count.put(num, count.getOrDefault(num, 0) + 1);
            index.put(num, index.getOrDefault(num, i));
        }

        int[] arr = count.keySet().stream()
                .sorted(Comparator.comparing(count::get)
                .reversed()
                .thenComparing(index::get))
                .mapToInt(i -> i)
                .toArray();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < count.get(arr[i]); j++) {
                bw.write(arr[i] + " ");
            }
        }

        br.close();
        bw.close();
    }

}