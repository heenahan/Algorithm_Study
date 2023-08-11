package org.prgrms;

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] relation = new int[M][2];
        Map<Integer, Integer> map = new HashMap(); // key 상근이의 친구 value 상근이의 친구의 친구
        boolean[] visited = new boolean[M]; // 상근 친구를 표시 depth 1

        for (int i = 0; i < M; i++) {
            relation[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(v -> Integer.parseInt(v)).toArray();

            int friend1 = relation[i][0];
            int friend2 = relation[i][1];

            int min = Math.min(friend1, friend2);
            if (min == 1) {
                // 여기 상근이 존재
                visited[i] = true;
                if (friend1 != 1) {
                    map.put(friend1, 0);
                }

                if (friend2 != 1) {
                    map.put(friend2, 0);
                }
            }
        }

        boolean[] ofFriend = new boolean[M + 1];

        for (int i = 0; i < M; i++) {
            if (!visited[i]) {

                int friend1 = relation[i][0];
                int friend2 = relation[i][1];

                if (map.containsKey(friend1) && !map.containsKey(friend2) && !ofFriend[friend2]) {
                    ofFriend[friend2] = true;
                    map.put(friend1, map.get(friend1) + 1);
                }

                if (map.containsKey(friend2) && !map.containsKey(friend1) && !ofFriend[friend1]) {
                    ofFriend[friend1] = true;
                    map.put(friend2, map.get(friend2) + 1);
                }

            }
        }
        // 상근이 친구
        int answer = map.size();

        // 친구의 친구
        for (int value : map.values()) {
            if (value >= 1) answer += value;
        }

        System.out.println(answer);

    }
    
}
