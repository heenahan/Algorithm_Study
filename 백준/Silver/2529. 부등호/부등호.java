import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    static String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    static List<String> list = new ArrayList<>();
    static List<Long> subAnswer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 모두 달라야 하면
        // 주어진 부등호를 만족하는
        // 최대 최소
        // 부등호 문자의 개수를 나타내는 정수 k
        // k개의 부등호 기호가 하나의 공백을 두고 한 줄에 제시
        // 첫째 줄 : 최대
        // 둘째 줄 : 최소

        // 모든 경우의 수로 숫자를 만들고 만족하는 최소 최댓값을 찾기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        String value = br.readLine();
        String[] valueToken = value.split(" ");

        dfs(count+1,0,"");

        for(String str : list){
            if(checkSubAnswer(valueToken, str)){
                subAnswer.add(Long.parseLong(str));
            }
        }

        Collections.sort(subAnswer);

        String max = Long.toString(subAnswer.get(subAnswer.size()-1));
        String min = Long.toString(subAnswer.get(0));

        if(max.length() < count+1){
            max = "0"+max;
        }

        if(min.length() < count+1){
            min = "0"+min;
        }
        bw.write(max+"\n");
        bw.write(min);

        bw.flush();
        bw.close();

    }

    public static void dfs(int level, int cnt, String answer){
        if(cnt==level){
            list.add(answer);
            return;
        }
        for(int i=0;i<10;i++) {
            String target = numbers[i];
            if(answer.indexOf(target)==-1) {
                answer += numbers[i];
                dfs(level, cnt + 1, answer);
                answer = answer.substring(0, answer.length() - 1);
            }
        }
    }

    public static boolean checkSubAnswer (String[] valueToken, String target){
       boolean tag = true;
       for(int i=0;i<valueToken.length;i++){
           int t1 = target.charAt(i)-'0';
           int t2 = target.charAt(i+1)-'0';
           // 하나 꺼내서 비교
           switch (valueToken[i]){
               case ">" :
                    tag= t1>t2? true : false;
                    break;
               case "<" :
                    tag= t1<t2? true : false;
                    break;
           }

           if(tag==false) {
               return false;
           }
       }
       return true;
    }

}
