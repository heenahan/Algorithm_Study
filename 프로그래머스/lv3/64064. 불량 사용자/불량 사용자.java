import java.util.*;

class Solution {
    
    int count = 0;
    Set<Set<String>> set = new HashSet<>();
    boolean[] visited;
    String[] list;
    
    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        list = new String[banned_id.length];
        backTracking(0, user_id, banned_id);
        return set.size();
    }
    
    public void backTracking(int depth, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            Set<String> tmp = new HashSet<>();
            for (String l : list) tmp.add(l);
            set.add(tmp);
            return;
        }
        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;
            if (user_id[i].length() == banned_id[depth].length()) {
                String[] splitUserId = user_id[i].split("");
                String[] splitBannedId = banned_id[depth].split("");
                boolean match = true;
                for (int j = 0; j < splitUserId.length; j++) {
                    if (splitBannedId[j].equals("*")) continue;
                    if (!splitUserId[j].equals(splitBannedId[j])) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    visited[i] = true;
                    list[depth] = user_id[i];
                    backTracking(depth + 1, user_id, banned_id);
                    visited[i] = false;
                }
            }
        }
    }
}