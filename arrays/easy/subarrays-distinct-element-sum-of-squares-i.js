/**
 * @param {number[]} nums
 * @return {number}
 */

// Optimized solution 
var sumCounts = function(nums){
    let total = 0;
    const d = [];
    const last = new Map(); // last position where it appeared 

    for (let j = 0; j < nums.length; j++){
        const x = nums[j];
        const prev = last.has(x) ? last.get(x): -1;

        // now start after the previous 
        for (let i = prev + 1; i < j; i++){
            d[i] += 1;
        }
        d.push(1); // new subarray start with 1

        for (let i = 0; i <= j; i++){
            total += d[i] * d[i];
        }
        last.set(x, j);
    }
    return total;
}

console.log(sumCounts([1, 2, 1])); // 15
console.log(sumCounts([1, 1]));    // 

// var sumCounts = function(nums) {
//     let total = 0;

//     for (let i = 0; i < nums.length; i++){
//         const seen = new Set();

//         for (let j = i; j < nums.length; j++){
//             seen.add(nums[j]);
//             total += seen.size * seen.size;
//         }
//     }
//     return total;
// };

// // test the solution
// console.log(sumCounts([1, 2, 1]));

// solve it from leetcode :https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/?envType=daily-question&envId=2026-10-15

// Complexity analysis: Time complexity is O(n^2) and space complexity is O(n) where n is the len of nums
