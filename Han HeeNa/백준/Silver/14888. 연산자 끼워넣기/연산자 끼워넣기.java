import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {

    static String[] operator = { "+", "-", "*", "/" };
    // 연산자 별 사칙연산
    static Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>(){{
        put("+", (a, b) -> { return a + b; });
        put("-", (a, b) -> { return a - b; });
        put("*", (a, b) -> { return a * b; });
        put("/", (a, b) -> { return a / b; });
    }};
    static String[] formula;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        // 피연산자
        int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        // 연산자 개수
        int[] operatorNum = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        // 연산자들
        formula = new String[nums.length - 1];
        T.backTracking(0, nums, operatorNum);

        bw.write(String.format("%d\n%d", max, min));

        br.close();
        bw.close();
    }

    public void backTracking(int n, int[] nums, int[] operatorNum) {
        if (n == formula.length) {
            // 수식 모두 지정했으면 계산
            int calc = nums[0];
            for (int i = 0; i < formula.length; i++) {
                // 맵에서 가져와서 계산
                var fuc = map.get(formula[i]);
                calc = fuc.apply(calc, nums[i + 1]);
            }
            // 최대 최소 교체
            min = Math.min(min, calc);
            max = Math.max(max, calc);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 연산자가 사용 가능하다면 
            if (operatorNum[i] > 0) {
                formula[n] = operator[i];
                operatorNum[i]--;
                backTracking(n + 1, nums, operatorNum);
                operatorNum[i]++;
            }
        }
    }

}
