import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int e : enemy) {
            n -= e;
            q.add(e);
            
            while (k > 0 && n < 0) {
                n += q.poll();
                k--;
            }
            if (n < 0) break;
            
            answer++;
        }
        
        return answer;
    }
}