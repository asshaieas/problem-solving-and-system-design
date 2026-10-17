# Intuition
Sorting by (digit sum, value) gives every number exactly one correct position. Following "current index → correct index" splits the array into cycles, and a cycle of length k needs k - 1 swaps.

# Approach
1. Store [digit sum, value, original index] for each number.
2. Sort by digit sum, and by value if the digit sums are equal.
3. Use a visited array to find each cycle and add (cycle length - 1) to the answer.

# Complexity
- Time complexity:
$$O(n \log n)$$, dominated by sorting.

- Space complexity:
$$O(n)$$ for the sorted array and the visited array.
# Code
```java []
import java.util.*;

public class Main {
    public static void main(String[] args) {
    Solution test = new Solution();
    System.out.println(test.minSwaps(new int []{37,100}));
    System.out.print(test.minSwaps(new int []{22,14,33,7}));
    }
}

class Solution {
    // helper function for digit sum
    private int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;

        // now I need to store in array the [digit sum, value, original index]
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }

        // sort by digit sum, tie -> smaller number first
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // now I need to count cycles
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            int cycleLen = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][2];
                cycleLen++;
            }
            swaps += cycleLen - 1;
        }

        return swaps;
    }
}
```
