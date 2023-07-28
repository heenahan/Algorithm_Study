## 접근
> <a href="https://www.acmicpc.net/problem/1941"> 소문난 칠공주 </a>

1. 조합을 사용하여 25개 자리 중에서 7개를 선택한다.
2. 선택한 7개의 자리가 모두 인접해 있는가?
3. 인접해 있다면 거기에 이다솜파가 4명 이상인가?

## 실패했던 사유
처음에는 모든 조합에 대해서 만드려고 했는데
생각보다 제한으로 걸 조건들이 너무 많다고 생각했고

풀이를 생각하다 30분이라는 제한 시간이 넘었습니다.

## 풀이
- 25개의 자리를 표현할 방식을 생각해본다.
  
  ![image](https://github.com/byeolhaha/Algorithm_Study/assets/108210958/a470aa2d-d077-4cf9-9153-53ef3452f8f8)

  위 표를 생각하고 행과 열을 분리한다.</br>
  즉 batchX에는 행에 해당하는 값들이 저장된다.</br>
  batchY에는 열에 해당하는 값들이 저장된다.</br>
  ```java
   for(int i=0;i<25;i++){
            batchX[i] = i / 5;
            batchY[i] = i % 5;
  }
  ```
- 조합을 사용하여 25개의 자리 중 7개를 뽑는다.
  ```java
  //public static void main에서의 호출
  combination(new int[7],0,0,7);
  ```
  ```java
   public static void combination (int[] comb, int index, int depth, int seven){
        if(seven==0){
            bfs(comb);
            return;
        }

        if(depth==25) return;

        comb[index] = depth;
        combination(comb, index+1, depth+1, seven-1); // 선택
        combination(comb, index, depth+1, seven); // 선택 안함
    }
  ```
  여기서 조금 헷갈렸는데</br>
  왜 저게 조합이지? 라는 생각이 들었다.</br>

  생각해보면
  comb[0] = 0</br>
  combination(comb[0]인걸 인정, 앞으로 나아감 index+1, depth+1, 자리 하나 사용);</br>
  combination(comb[0]인 걸 인정하지 않음, 그대로 index, depth+1, 자리 사용하지 않음);</br>
  ![image](https://github.com/byeolhaha/Algorithm_Study/assets/108210958/c7bb1e3a-bf0f-4a6e-922f-892dc1fc792f)
  위와 같은 방식으로 25까지 중복되지 않은 수로 7개의 자리를 만든는 것이다.

- 뽑힌 7개의 자리로 BFS를 한다.</br>
  여기서 중요한 조건문은 그래서 인접했냐 인접하지 않았냐이다.</br>
  그 조건문은 아래와 같다. </br>
     ```java
      if (!visited[j] && batchX[studentLocation] + x[i] == batchX[comb[j]]
                            && batchY[studentLocation] + y[i] == batchY[comb[j]])
     ```







