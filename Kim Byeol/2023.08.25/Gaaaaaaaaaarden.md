## 접근
><a href="https://www.acmicpc.net/problem/18809">Gaaaaaaaaaarden</a>
이 문제는 여러 개의 조건문이 섞여 있다.
- 2차원의 배열이 주어지는데 이 중에 0은 호수, 1은 배양액을 심지 못하는 곳, 2는 배양액을 심을 수 있는 곳이다.
- 배양액은 1초마다 상하좌우로 흡수되어 퍼진다.
- 배양액의 종류는 2가지이다. 하나는 그린색, 하나는 빨간색이다.
- 배양액을 통해서 꽃을 피울 수 있는 경우는 동시에 빨간색 배양액과 그린색 배양이 만나는 경우이다.
- 또한 꽃을 피울 수 있는 곳에서는 더 이상 배양액이 퍼지지 않는다.
- 배양액은 모두 사용해야 한다.
- 최대로 피울 수 있는 꽃의 개수를 구하면 된다.

## 풀이
- 먼저 어디에 배양액을 심을 수 있는지를 구한다.
  ```java
  for(int i=0;i<N;i++) {
      String[] land = br.readLine().split(" ");
      for(int j=0;j<M;j++) {
            map[i][j] = Integer.parseInt(land[j]);
            if(map[i][j]==2) {
               list.add(new Pot(i,j,""));
            }
         }
  }
  ```
- 배양액을 심 수 있는 곳 중에서 그린색을 심을 수 있는 곳을 먼저 고르고</br>
  여기서 남은 것들 중에서 빨간색을 고른다.</br>
  처음에는 배양액의 개수의 길이를 가진 모든 경우의 수를 구하고</br>
  여기에 차례대로 그린색과 빨간색을 주려고 했는데</br>
  이 방법이 그린색과 빨간색의 모든 경우의 수를 반영하지는 못한다는 것을 깨달았고</br>
  중복을 제거하기 위해서 더 좋은 방법은 결국 각각의 색마다 DFS를 돌리는 것이었다.</br>
  - 그린색 배양액 심기
    ```java
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
    ```
  - 빨간색 배양액 심기
    ```java
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
    ```
 - 이제 심어진 배양액 위치를 저장한 listR, listG를 가지고 Queue를 초기화한다.
   ```java
    static void initQueue() {
        for(Pot p : listG) {
            q.add(p);
        }
        for(Pot p: listR) {
            q.add(p);
        }
    }
   ```
- 이 Queue가지고 BFS를 시작한다.
  ```java
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
  ```
## 배운점
객체를 매개변수로 전달하는 경우 내가 생각하는 방향과 다르게 흘러갈 수 있다.</br>
특히 재귀에서 객체를 매개변수를 전달하는 것은 그 이전을 되돌리는 백트래킹의 작업이 필요하다는 것을 주의해야 한다.</br>
</br>
또한 전역 변수를 가지고 작업하는 경우</br>
이것 또한 데이터가 꼬일 수 있다는 점을 주의해야 한다.</br>
</br>
내 풀이를 가지고 가정해보자.</br>
하나의 그린색 배양액 위치 listAA가 만들어지면</br>
빨간색 배양액 위치 listA, 위치 listB가 만들어진다고 가정한다.</br>
</br>
내가 생각했던 모습은 </br>
listAA(전역변수) <-listA : listAA에 listA를 넣어서 그린색과 빨간색 위치 만들기</br>
listAA(전역변수) <-listB : listAA에 listB를 넣어서 그린색과 빨간색 위치 만들기</br>
</br>
하지만 두번째 차례에서 listAA에는 이미 listA가 넣어져 있어서 데이터가 꼬여있다.</br>

    
  
