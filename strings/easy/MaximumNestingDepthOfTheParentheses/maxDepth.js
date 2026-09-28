/**
 * @param {string} s
 * @return {number}
 */
var maxDepth = function(s) {
    let current = 0, max = 0; 
    for (let i = 0; i < s.length; i++){
        if (s[i] === "("){
            current++;
            if (current > max){
                max = current;
        }
        }
        if (s[i] === ")"){
            current--;
        }
    }
    return max;
};


// test cases for the solution 
console.log(maxDepth("(1+(2*3)+((8)/4))+1"));
console.log(maxDepth("(1)+((2))+(((3)))"));   
console.log(maxDepth("()(())((()()))"));     
// the time complexity is O(n) where n is the length of string and space complexity is O(1) 
