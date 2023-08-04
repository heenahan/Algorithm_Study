mport java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NC = br.readLine().split(" ");
        int n = Integer.parseInt(NC[0]);
        int c = Integer.parseInt(NC[1]);

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(v -> Integer.parseInt(v))
                .toArray();

        map = new LinkedHashMap<>();

        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        // 값 가져오기
        List<Integer> list = new ArrayList<>();
        for (int value : map.values()) {
            list.add(value);
        }

        Collections.sort(list, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (int l : list) {
            String answer = findValue(l);
            sb.append(answer);
        }

        System.out.println(sb.toString().trim());
        
    }

    static String findValue(int value) {
        String answer = "";
        for (int k : map.keySet()) {
            if (map.get(k) == value) {
                map.remove(k);
                answer = makeString(k, value);
                break;
            }
        }
        return answer;

    }

    static String makeString(int k, int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value; i++) {
            sb.append(k + " ");
        }
        return sb.toString();
    }
}
