import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	static int[] min = null;
	static int[] max = null;
	static boolean[] visited;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Main T = new Main();
		
		int k = Integer.parseInt(br.readLine());
		String[] inequality = br.readLine().split(" ");
		
		visited = new boolean[10];
		nums = new int[k + 1];
		
		T.backTracking(0, k, inequality);
		
		String minOfString = Arrays.stream(min).mapToObj(n -> String.valueOf(n)).collect(Collectors.joining(""));
		String maxOfString = Arrays.stream(max).mapToObj(n -> String.valueOf(n)).collect(Collectors.joining(""));
		
		bw.write(maxOfString + "\n" + minOfString);
		
		br.close();
		bw.close();
	}
	
	public void backTracking(int n, int k, String[] inequality) {
		if (n == k + 1) {
			// 처음에 만족한 배열이 가장 작음
			if (min == null) min = Arrays.copyOf(nums, nums.length);
			// 가장 마지막에 만족한 배열이 가장 큼
			max = Arrays.copyOf(nums, nums.length);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (!visited[i]) { // 방문하지 않았고
				int before = n == 0 ? -1 : nums[n - 1];
				// 0번째이거나 부등호를 만족했을 때
				if (n == 0 || isRightInequality(before, i, inequality[n - 1])) {
					visited[i] = true;
					nums[n] = i;
					backTracking(n + 1, k, inequality);
				}
				visited[i] = false;
			}
		}
	}
	
	private boolean isRightInequality(int num1, int num2, String inequality) {
		int max = Math.max(num1, num2);
		if (inequality.equals(">")) {
			return max == num1;
		}
		return max == num2;
	}
	
}