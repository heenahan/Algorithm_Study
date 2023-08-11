import java.util.*;

class Solution {
    /**
     * 두 번의 루프를 돈다
     * 첫 번째 - 보석 종류가 몇 개인지 확인
     * 두 번째 - 투포인터 
     * 투포인터 방법 
     * 1. 시작은 0으로 끝을 옮기면서 시작부터 끝까지 모든 보석을 포함하는 것을 찾음
     * 2. 시작을 올리면서 가장 짧은 길이를 찾음
     */
    public int[] solution(String[] gems) {
        int[] answer = { -200000, 200000 };
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        Map<String, Integer> map = new HashMap<>();
        int ed = 0;
        for (int st = 0; st < gems.length; st++) {
            while (ed < gems.length && set.size() > map.size()) {
                map.put(gems[ed], map.getOrDefault(gems[ed], 0) + 1);
                ed++;
            }
            // set, map 사이즈가 같아지면 종료했으므로 길이 update
            if (set.size() == map.size()) {
                if (answer[1] - answer[0] + 1 > ed - st) {
                    answer[0] = st + 1;
                    answer[1] = ed;
                }
            }
            // st부분을 삭제함
            map.put(gems[st], map.getOrDefault(gems[st], 0) - 1);
            if (map.get(gems[st]) == 0) map.remove(gems[st]);
        }
        return answer;
    }
}