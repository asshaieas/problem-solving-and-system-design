# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->
I solved it using brute force approach using two loops. Then we optimzed the solution using segment tree
# Approach
<!-- Describe your approach to solving the problem. -->

# Complexity
- Time complexity:
<!-- Add your time complexity here, e.g. $$O(n)$$ -->
O(n long n) where n is the length of the  nums array
- Space complexity:
<!-- Add your space complexity here, e.g. $$O(n)$$ -->
O(n) also where n is the length of the array nums 
# Code
```python3 []
from typing import List
class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        MOD = 10 ** 9 + 7 
        n = len(nums) 
        
        sm = [0] * (4 * n) # sum of values in each box 
        sq = [0] * (4 * n) # sum of squares in each box 
        lz = [0] * (4 * n) # pending "+v" note (lazy)
        
        def apply(node, length, v):
            sq[node] += 2 * v * sm[node] + v * v * length # the math trick
            sm[node] += v * length
            lz[node] += v 

        def push(node, lo, hi): #pass the pending note to the children
            if lz[node]:
                mid = (lo + hi) // 2 
                apply(2 * node, mid - lo + 1, lz[node])
                apply(2 * node + 1, hi - mid, lz[node])
                lz[node] = 0 
        
        def update(node, lo, hi, l, r): # add 1 to positions l .. r 
            if r < lo or hi < l:
                return 
            if l <= lo and hi <= r:
                apply(node, hi - lo + 1, 1)
                return 
            push(node, lo, hi)
            mid = (lo + hi) // 2
            update(2 * node, lo, mid, l, r)
            update(2 * node + 1, mid + 1, hi, l, r)
            sm[node] = sm[2 * node] + sm[2 * node + 1]
            sq[node] = sq[2 * node] + sq[2 * node + 1]

        last = {}        # value -> last index where we saw it
        ans = 0
        for j, x in enumerate(nums):
            prev = last.get(x, -1)
            update(1, 0, n - 1, prev + 1, j)   # Step 4 rule
            ans += sq[1]                       # root = sum of squares of arr
            last[x] = j

        return ans % MOD
# #test solutioin 
# test = Solution()
# print(test.sumCounts([1, 2, 1]))  # 15
# print(test.sumCounts([2, 2]))     # 3

#brute force approach
# class Solution:
#     def sumCounts(self, nums: List[int]) -> int:
#         MOD = 10**9 + 7 # to reduce the final total 
#         n = len(nums)
#         total = 0 
#         for i in range(n):
#             seen = set() #empty set for each new start 
#             for j in range(i, n):
#                 seen.add(nums[j])
#                 #now count distinct 
#                 count = len(seen)
#                 total += count * count 
#         return total % MOD 
# test = Solution()
# print(test.sumCounts([1, 2, 1]))  # 15
# print(test.sumCounts([2, 2]))     # 3

```
