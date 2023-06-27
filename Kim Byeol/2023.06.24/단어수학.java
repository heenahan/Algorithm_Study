import java.io.*;
import java.util.*;

public class Main {

    static List<String[]> wordTokenList = new ArrayList<>();
    static String[] usedAlphabet ;
    static boolean[] visited = new boolean[10];
    static Integer max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // N개의 단어로 이루어져 있다
        // 각 단어 알파벳 대문자
        // 대문자를 0부터9까지의 숫자 하나로 바꿔서 N개의 수를 합하는 문제
        // 같은 알파벳은 같은 숫자로
        // 두 개 이상의 알파벳이 같은 숫자로 바뀌어서는 안된다
        // 주어진 단어
        // 몇 개의 알파벳이 등장하는가?
        // 각 알바펫에 숫자를 부여하고
        // 그 합을 최대로 하는 그 값을 정하기


        Set<String> set = new HashSet<>();
        Map<String,Integer> map = new HashMap<>();

        int wordCount = Integer.parseInt(br.readLine());

        for(int i=0;i<wordCount;i++){
            wordTokenList.add(br.readLine().split(""));
            Arrays.stream(wordTokenList.get(i)).forEach(v->set.add(v));
        }

        int level = set.size();
        usedAlphabet = set.toArray(String[]::new);
        dfs(0,level,map);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();

    }

    static void dfs(int location , int level, Map<String , Integer> map){
        if(level==location){
            int sumResult = makeSum(map);
            if(max<sumResult){
                max = sumResult;
            }
            return;
        }

        for(int i=0;i<10;i++){
            if(!visited[i]){
                visited[i]=true;
                map.put(usedAlphabet[location],i);
                dfs(location+1, level,map);
                map.remove(usedAlphabet[location]);
                visited[i]=false;
            }
        }

    }
    static int makeSum(Map<String,Integer> map){
        int sum =0;
        for(String[] tokens : wordTokenList){
            int wordNum = 0;
            for(String token : tokens){
                wordNum*=10;
                wordNum += map.get(token);
            }
            sum+= wordNum;
        }
        return sum;
    }
}

