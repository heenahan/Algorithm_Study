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

        int left = 0;
        int right = 0;

        int cnt = 0;
        int max = Integer.MIN_VALUE;

        while (right < N) {
            if (!map.containsKey(arr[right]) || map.get(arr[right]) < K) {
                map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
                right++;
                cnt++;
                max = max < cnt ? cnt : max;
                continue;
            }

            if (map.get(arr[right]) == K) {
                int removal = arr[right];
                while (left <= right) {
                    map.put(arr[left], map.get(arr[left]) - 1);
                    left++;
                    cnt--;
                    if (arr[left - 1] == removal) break;
                }
            }

        }

        System.out.println(max);
    }

}
