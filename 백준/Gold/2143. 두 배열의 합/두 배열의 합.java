import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] arrA = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int m = Integer.parseInt(br.readLine());
        int[] arrB = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arrA[j];
                subA.add(sum);
            }
        }
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += arrB[j];
                subB.add(sum);
            }
        }
        subA = subA.stream().sorted().collect(Collectors.toList());
        subB = subB.stream().sorted().collect(Collectors.toList());
        long answer = 0;
        for (long num : subA) {
            long minus = t - num;
            int ub = upperBound(0, subB.size(), minus, subB);
            int lb = lowerBound(0, subB.size(), minus, subB);
            answer += ub - lb;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    // key를 초과하는 첫번째 값
    static int upperBound(int st, int ed, long key, List<Long> list) {
        int mid;
        while (st < ed) {
            mid = st + (ed - st) / 2;
            if (list.get(mid) <= key) st = mid + 1;
            else ed = mid;
        }
        return st;
    }
    // key와 같거나 key보다 큰 수 중 가장 작은 수
    static int lowerBound(int st, int ed, long key, List<Long> list) {
        int mid;
        while (st < ed) {
            mid = st + (ed - st) / 2;
            if (list.get(mid) < key) st = mid + 1;
            else ed = mid;
        }
        return st;
    }

}