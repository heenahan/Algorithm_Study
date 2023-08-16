import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] solutions = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .sorted() // 정렬
                            .toArray();

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < solutions.length; i++) {
            int key = -solutions[i]; int min = Integer.MAX_VALUE;
            int lb = lowerBound(0, solutions.length, key, solutions);
            // 오른쪽 -1
            if (lb > 0 && lb - 1 != i && Math.abs(min) > Math.abs(solutions[i] + solutions[lb - 1])) {
                min = solutions[i] + solutions[lb - 1];
            }
            // lb -> 같은 용액일 경우 섞으면 안됨
            if (lb != i && lb < solutions.length && Math.abs(min) > Math.abs(solutions[i] + solutions[lb])) {
                min = solutions[i] + solutions[lb];
            }
            // lb + 1
            if (lb < solutions.length - 1 && Math.abs(min) > Math.abs(solutions[i] + solutions[lb + 1])) {
                min = solutions[i] + solutions[lb + 1];
            }
            if (Math.abs(result) > Math.abs(min)) {
                result = min;
            }
        }

        bw.write(String.valueOf(result));

        br.close(); bw.close();
    }
    // key와 같거나 key보다 큰 수 중 가장 작은 수
    static int lowerBound(int st, int ed, long key, int[] arr) {
        int mid;
        while (st < ed) {
            mid = st + (ed - st) / 2;
            if (arr[mid] < key) st = mid + 1;
            else ed = mid;
        }
        return st;
    }

}