import java.io.*;
import java.util.*;

class Main {

    static class Pot{
        int x;
        int y;
        String color;
        public Pot(int x, int y, String color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    static int[] cx = {0,0,-1,1};
    static int[] cy = {1,-1,0,0};

    static String[][] visited;

    static int[][] map;
    static List<Pot> list;
    static int N;
    static int M;
    static int G;
    static int R;

    static int check = 3;

    static Queue<Pot> q = new LinkedList<>();
    static List<Pot> listG;
    static List<Pot> listR;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        N = inputs[0];
        M = inputs[1];
        G = inputs[2];
        R = inputs[3];
        map = new int[N][M];
        list = new ArrayList<>();

        visited= new String[N][M];

        for(int i=0;i<N;i++) {
            String[] land = br.readLine().split(" ");
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(land[j]);
                if(map[i][j]==2) {
                    list.add(new Pot(i,j,""));
                }

            }
        }
        makeG(0,0,"");
        System.out.println(max);
    }

    static void makeG(int location, int start , String answer) {
         if(G == location) {
             boolean[] spreads = new boolean[list.size()];
             listG = makeToken(answer,spreads,"G");
             makeR(0,0, "", spreads);
         }

         for(int i=start;i< list.size();i++) {
             answer += i + " ";
             makeG(location + 1, i+1, answer);
             answer = answer.substring(0, answer.length() - 2);
         }
    }

    static void makeR(int location, int start , String answer, boolean[] spreads) {
        if(R== location) {
            listR = makeToken(answer,spreads,"R");
            initQueue();
            makeVisited();
            makeSpring();
        }

        for(int i=start;i< list.size();i++) {
            if(!spreads[i]) {
                answer += i + " ";
                makeR(location + 1, i + 1, answer,spreads);
                answer = answer.substring(0, answer.length() - 2);
            }
        }
    }

    static void makeSpring(){
        int cnt= 0;
        int[][] Map = new int[N][M];
        check=3;
        while(!q.isEmpty()) {
            int size = q.size();
            check++;
            for(int i=0;i<size;i++) {
                Pot p = q.poll();

                if(Map[p.x][p.y]==3) continue;

                for(int j=0;j<4;j++) {
                    int X = p.x + cx[j];
                    int Y = p.y + cy[j];

                    // 범위를 벗어난 경우
                    if(X<0 || X>=N || Y< 0 || Y>=M ) {
                        continue;
                    }

                    //꽃이거나 호수이거나 이미 방문한 색이 경우
                    if(map[X][Y] == 0 || Map[X][Y] == 3 || p.color.equals(visited[X][Y])){
                        continue;
                    }

                    // 비어 있거나
                    if(visited[X][Y]==null || visited[X][Y].isEmpty()) {
                        visited[X][Y] = p.color;
                        Map[X][Y] = check;
                        q.offer(new Pot(X,Y, p.color));
                    // 혹은 색이 다르고 
                    }else {
                        //같은 레벨에서 진행한 경우
                        if(Map[X][Y]==check) {
                            Map[X][Y] = 3;
                            cnt++;
                        }
                    }
                }
            }
        }
        max= max<cnt ? cnt: max;
    }

    static List<Pot> makeToken(String answer, boolean[] spreads, String color) {
        String[] tokens = answer.split(" ");
        List<Pot> pots = new ArrayList<>();
        for(String token : tokens) {
            int index = Integer.parseInt(token);
            if(color=="G") {
                spreads[index] = true;
            }
            Pot p = list.get(index);
            p.color=color;
            pots.add(p);
        }
        return pots;
    }

    static void makeVisited() {
        visited = new String[N][M];
        int size = q.size();

        for(int i=0;i<size;i++) {
            Pot pot = q.poll();
            visited[pot.x][pot.y]= pot.color;
            q.add(pot);
        }
    }

    static void initQueue() {
        for(Pot p : listG) {
            q.add(p);
        }
        for(Pot p: listR) {
            q.add(p);
        }
    }
}
