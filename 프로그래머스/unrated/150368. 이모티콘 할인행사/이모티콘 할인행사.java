import java.util.*;
import java.util.stream.*;

class Join {
    private int service;
    private int benefit;
    
    public Join(int service, int benefit) {
        this.service = service;
        this.benefit = benefit;
    }
    
    public int getService() { return service; }
    public int getBenefit() { return benefit; }
}

class Solution {
    
    int[] discount = { 10, 20, 30, 40 }; // 할인율은 10, 20, 30, 40 중 하나
    int[] sales; // emoticons[i]의 할인율을 가리킴
    List<Join> list = new ArrayList<>(); 
    
    public int[] solution(int[][] users, int[] emoticons) {
        sales = new int[emoticons.length];
        backTracking(0, users, emoticons);
        list = list.stream()
                    .sorted(Comparator.comparing(Join::getService)
                                    .thenComparing(Join::getBenefit))
                    .collect(Collectors.toList());
        Join j = list.get(list.size() - 1);
        return new int[]{ j.getService(), j.getBenefit() };
    }
    
    private void backTracking(int n, int[][] users, int[] emoticons) {
        if (n == emoticons.length) {
            // 가입자수와 수익을 계산
            int service = 0; int benefit = 0;
            for (int[] user : users) {
                int purchase = 0; boolean isJoin = false;
                for (int i = 0; i < sales.length; i++) {
                    // 해당 이모티콘 할인율 유저 기준보다 높다면 구매
                    if (sales[i] >= user[0]) purchase += (emoticons[i] * (100 - sales[i]) / 100);
                    // 일정 가격 이상이 된다면 구매 취소 후 가입
                    if (purchase >= user[1]) {
                        isJoin = true;
                        break;
                    } 
                }
                if (isJoin) service++;
                else benefit += purchase;
            }
            list.add(new Join(service, benefit));
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            sales[n] = discount[i];
            backTracking(n + 1, users, emoticons);
        }
    }
}