# Google Kick Start 2020 Round C (rank 289)

## What I learned (WA)
1. The range of int & long as a counter. (B)
2. Use the range of number to narrow the candidate perfect number. (B)
3. Usage of BIT, single point update and odd & even index sum query. 
4. Time of using HashMap and time of using Array.

---
**Countdown**
1. Two for loop to find the count-down array.

**Stable Wall**
1. The cell on the top is dependent on the cell below it. 
2. Use topological sorting to find the order of building the wall.

**Perfect Subarray** (Spend a long time to optimize. TLE for 1h)
1. Count the number of subarray with some value, so we can use TWO-SUM method.
2. Use a HashMap to count the number of subarray with some prefixSum.
3. Check every perfect square candidate, and add the value to the result.
4. Corner case: When all values are 0, there are (10^5)^2 subarray which are perfect square.
5. Time complexity constant optimization: since the range of sum is smaller than 2*10^7, so the memory space is smaller than 1G. We can use Array instead of HashMap to count the appearance of each prefixSum, which greatly reduce the time cost.

**Candies**
1. Since we are going to do single update and range query, we can use BIT to solve this problem.
2. The range query is A[l] × 1 - A[l+1] × 2 + A[l+2] × 3 - A[l+3] × 4 + A[l+4] × 5 ..., so the intuition is that we can record a multipleSum, which is A[i] * i, and also the normal prefix sum. Then we can get the query range by subtracting these two sums.
3. Then, the odd indexes and even indexes have different sign, so we need to count their sum separately. 
4. Implement BIT, with oddSum, evenSum, oddMulSum, evenMulSum, and update and query. 

