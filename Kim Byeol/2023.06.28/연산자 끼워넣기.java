import java.io.*;
import java.util.Arrays;
import java.util.Stack;

class Main {

    static String[] operator = {"+", "-", "*", "/"};
    static int[] intOperand;
    static int[] OperatorCount;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        // 연산자
        String[] nums = br.readLine().split(" ");
        intOperand = Arrays.stream(nums)
                .mapToInt(Integer::parseInt)
                .toArray();
        //피연산자
        String[] operatorVisited = br.readLine().split(" ");
        OperatorCount = Arrays.stream(operatorVisited)
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(count - 1, 0, "");

        System.out.println(max);
        System.out.println(min);

        br.close();
    }

    static void dfs(int level, int location, String answer) {
        if (level == location) {
            int result = makeResult(answer);
            if (result > max) max = result;
            if (result < min) min = result;
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (OperatorCount[i] == 0) continue;
            OperatorCount[i] -= 1;
            dfs(level, location + 1, answer + operator[i]);
            OperatorCount[i] += 1;

        }
    }

    static int makeResult(String operatorOrder) {
        Stack<Integer> stack = new Stack<>();
        stack.add(intOperand[0]);

        for (int i = 0; i < operatorOrder.length(); i++) {
            int firstOperand = stack.pop();
            int secondOperand = intOperand[i + 1];
            switch (operatorOrder.charAt(i)) {
                case '+':
                    stack.add(firstOperand + secondOperand);
                    break;
                case '-':
                    stack.add(firstOperand - secondOperand);
                    break;
                case '*':
                    stack.add(firstOperand * secondOperand);
                    break;
                case '/':
                    stack.add(firstOperand / secondOperand);
                    break;
            }

        }
        return stack.pop();

    }
}
