import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = inputs[0];
        int K = inputs[1];

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> map = new HashMap<>();

        int r = 0;
        int l = 0;

        int cnt = 0;
        int max = Integer.MIN_VALUE;

        while (l < N) {
            if (!map.containsKey(arr[l]) || map.get(arr[l]) < K) {
                map.put(arr[l], map.getOrDefault(arr[l], 0) + 1);
                l++;
                cnt++;
                max = max < cnt ? cnt : max;
                continue;
            }

            if (map.get(arr[l]) == K) {
                int removal = arr[l];
                while (r <= l) {
                    map.put(arr[r], map.get(arr[r]) - 1);
                    r++;
                    cnt--;
                    if (arr[r - 1] == removal) break;
                }
            }

        }

        System.out.println(max);
    }

}
