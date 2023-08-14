import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        Map<String,Integer> buy = new HashMap<>();
        
        for(String g : gems) {
            set.add(g);
        }
        
        int size = set.size();
        int start =Integer.MIN_VALUE;
        int end=gems.length;
        
        //거꾸로 가기, 최신 것을 호출하기 위해
        for(int i=gems.length-1;i>=0;i--) {
            String viewedBosuck = gems[i];
            buy.put(viewedBosuck,buy.getOrDefault(viewedBosuck,0)+1);
            
            if(start<i)start =i;
            if(end>i) end=i;
            
            while(buy.get(gems[start])>1){
                 buy.put(gems[start],buy.get(gems[start])-1);
                 if(buy.get(gems[start])==0) buy.remove(gems[start]);
                 start--; 
            }
         
            if(buy.size() == size) {
                if(start-end<=min) {
                   min= start-end;
                   answer[0] = end+1;
                   answer[1] = start+1;
                 }
            }
            
        }
        
        return answer;
        
    }
    
}
