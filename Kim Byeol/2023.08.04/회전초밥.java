import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(v -> Integer.parseInt(v)).toArray();

        int N = arr[0]; // 8
        int kindOf = arr[1]; // 30
        int flat = arr[2]; // 4
        int coupon = arr[3]; // 30

        // 회전초밥 완성
        int[] rotateRice = new int[N + flat];
        for (int i = 0; i < N; i++) {
            String r = br.readLine();
            rotateRice[i] = Integer.parseInt(r);
        }
        int start = 0;
        for (int i = N; i < N + flat; i++) {
            rotateRice[i] = rotateRice[start++];
        }

        List<Integer> eating = new ArrayList<>();

        // 처음 flat-1개의 map 만들어놓기
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < flat-1; i++) {
            map.put(rotateRice[i], map.getOrDefault(rotateRice[i], 0) + 1);
        }

        int startIndex = 0;
        int endIndex= flat-1;
        int answer = 0;
        while (startIndex < N) {
            map.put(rotateRice[endIndex],map.getOrDefault(rotateRice[endIndex],0)+1);
            answer = map.size();
            if (!map.containsKey(coupon)) {
                answer++;
            }
            eating.add(answer);

            int deletedRice = rotateRice[startIndex];
            map.put(deletedRice, map.get(deletedRice) - 1);

            if (map.get(deletedRice) == 0) {
                map.remove(deletedRice);
            }
            startIndex++;
            endIndex++;

        }


        // 가장 큰 수 알아내기
        Collections.sort(eating,Collections.reverseOrder());

        System.out.println(eating.get(0));
    }


}
