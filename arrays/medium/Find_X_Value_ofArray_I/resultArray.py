from typing import List
class Solution:
    def resultArray(self, nums: List[int], k: int) -> List[int]:
        result = [0] * k
        ending = [0] * k

        for num in nums:
            value = num % k
            current = [0] * k

            # Start the new subarray at this num.
            current[value] += 1

            # Extend every subarray ending at the previous number.
            for remainder, count in enumerate(ending):
                if count:
                    new_remainder = (remainder * value) % k
                    current[new_remainder] += count

            for remainder, count in enumerate(current):
                result[remainder] += count

            ending = current

        return result


# # test solution 
# solution = Solution()
# nums = [1,2,3,4,5]
# k = 3
# print(solution.resultArray(nums, k))


# I need first understand How do we generate "all contiguous subarrays"? and implement brute force solution 
# import math 
# subarrays = []
# nums = [1,2,3,4,5]
# k = 3
# result = [0] * k 
# for i in range(len(nums)):
#     product = 1 # this will reset the product to 1 each time we start new i 
#     for j in range(i, len(nums)):
#         product *= nums[j]
#         remainder = product % k 
#         result[remainder] += 1
# print(result)
# #result =[0, 0, 0] = [2, 1, 1]Output: [9,2,4]
# for i in range(len(nums)):
#     for j in range(i, len(nums)):
#             subarrays.append(nums[i:j + 1])
# for arr in subarrays:
#     reminder = math.prod(arr) % k 
#     result[reminder] += 1
# print(subarrays)
# print(result)
