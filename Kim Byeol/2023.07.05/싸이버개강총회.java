
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        int answer =0;
        Map<String, Integer> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = br.readLine();
        List<Integer> time = Arrays.stream(inputLine.split(" "))
                .map(v-> makeMinute(v))
                .collect(Collectors.toList());

        int startTime = Integer.valueOf(time.get(0));
        int endTime = Integer.valueOf(time.get(1));
        int qTime = Integer.valueOf(time.get(2));

        String input ="";
        while((input = br.readLine())!=null && !input.isEmpty()){
            String[] man = input.split(" ");
            int manTime = makeMinute(man[0]);
            String manName = man[1];

            if(manTime<=startTime){
                map.put(manName,0);
                continue;
            }

            if(map.containsKey(manName)) {
                if(manTime== endTime){
                    answer++;
                    map.remove(manName);
                    continue;
                }
                if(manTime >endTime && manTime <= qTime){
                    answer++;
                    map.remove(manName);
                }
            }

        }

        System.out.println(answer);

    }

    public static int makeMinute(String time){
        String[] token = time.split(":");
        int T = Integer.parseInt(token[0]);
        int M = Integer.parseInt(token[1]);
        return T*60 + M ;
    }
}
