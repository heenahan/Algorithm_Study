import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer,Integer> map = new TreeMap<>();
        List<List<Integer>> list = new ArrayList<>();

        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }

        while(true){
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(inputs[0]==-1) {
                break;
            }
            list.get(inputs[0]).add(inputs[1]);
            list.get(inputs[1]).add(inputs[0]);
        }

        int min = Integer.MAX_VALUE;

        for(int i=1;i<=N;i++) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[N+1];
            visited[i]=true;

            for(int friend: list.get(i)){
                q.offer(friend);
                visited[friend]=true;
            }

            int relation =  findFriends(q,list,visited);
            map.put(i,relation);
            min = min > relation ? relation : min;
        }


        String answer = "";
        int cnt=0;

        for(int key: map.keySet()) {
            if(min == map.get(key)){
                answer+=" "+key;
                cnt++;
            }
        }

        System.out.println(min+" "+cnt);
        System.out.println(answer.trim());

    }

    static int findFriends(Queue<Integer> q,  List<List<Integer>> list,  boolean[] visited ) {
        int level = 0;

        while(!q.isEmpty() ){
            level++;
            int size = q.size();
            for(int i=0;i<size;i++) {
                int pre = q.poll();
                for(int friend : list.get(pre)) {
                    if(!visited[friend]) {
                        q.offer(friend);
                        visited[friend] = true;
                    }
                }
            }
        }

        return level;

    }

}
