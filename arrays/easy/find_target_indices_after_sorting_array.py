class Solution(object):
    def targetIndices(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        smaller = 0 
        equal = 0 
        
        for num in nums:
            if num < target:
                smaller += 1 
            elif num == target:
                equal += 1 
        return list(range(smaller, smaller + equal))# this will return for us the indicies 


test = Solution()
print(test.targetIndices([1, 3, 2, 4, 4], 4))
print(test.targetIndices([1, 3, 2, 5], 6))
