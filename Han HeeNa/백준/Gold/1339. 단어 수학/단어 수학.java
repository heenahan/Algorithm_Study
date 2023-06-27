import java.io.*;
import java.util.*;

public class Main {

    static Map<String, Integer> map;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<String[]> formula = new ArrayList<>();
        Set<String> alphabetSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            // base case에서 매번 split을 호출함으로써 시간초과 발생 -> 입력받자마자 split함
            formula.add(br.readLine().split(""));
            // 알파벳 넣기
            Arrays.stream(formula.get(i))
                    .forEach(alphabetSet::add);
        }

        String[] alphabet = alphabetSet.toArray(String[]::new);
        map = new HashMap<>(); // 맵으로 저장
        visited = new boolean[10];

        backTracking(0, alphabet, formula);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }

    static void backTracking(int n, String[] alphabet, List<String[]> formula) {
        if (n == alphabet.length) { // base case
            int sum = 0;
            for (String[] f : formula) {
                int convert = 0;
                for (String s : f) {
                    convert += map.get(s);
                    if (convert > 0) convert *= 10;
                }
                sum += convert / 10;
            }
            // 최대라면 교체
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                map.put(alphabet[n], i);
                visited[i] = true;
                backTracking(n + 1, alphabet, formula);
                visited[i] = false;
            }
        }
    }

}
