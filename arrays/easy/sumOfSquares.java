# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->

# Approach
<!-- Describe your approach to solving the problem. -->
I used for loop to go through each element in nums, and if statement to check the special element
# Complexity
- Time complexity:
<!-- Add your time complexity here, e.g. $$O(n)$$ -->
O(n) where n is the length of nums
- Space complexity:
<!-- Add your space complexity here, e.g. $$O(n)$$ -->
O(1) I used only two simple variables and no extra space
# Code
```java []
import java.util.*;

public class Main {
    public static void main(String[] args) {
      int [] nums = {1, 2, 3, 4};
      Solution test = new Solution();
      System.out.println(test.sumOfSquares(nums));
      int[] nums2 = {2, 7, 1, 19, 18, 3};
      System.out.println(test.sumOfSquares(nums2)); // expected: 63

    }
}

class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length; // = 4
        int totalSum = 0;
        for (int i = 1; i <= n; i++){
          // i = 4
          if (n % i == 0){
            int value = nums[i - 1]; // 4
            totalSum += value * value; // 5 + 16 = 21
          }
        }
        return totalSum;
    }
}
```
