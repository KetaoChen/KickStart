# Google Kick Start 2020 Round D (rank 108)

# So happy to solve a segment tree problem in a contest!

**Record Breaker**
Brute force, use a max to record the maximum of prefix.

**Alien Piano** (DP)
1. Be patient to read the problem and understand the problem.
2. For each convert, aliens can start at any pitch.
3. So it is a sequence DP to try the pitch at each position.

**Beauty of Tree** (Tree Multiplication, Inclusive & Exclusive)
1. Change the way of thinking. If we enumerate the combination of two nodes, there must be at least n2 time complexity.
2. We can use post order (DP) to get the number of combos on each root / all possibilities.
3. Use tree multiplication to get the kth ancestor parents.
4. Use inclusive & exclusive to prevent the repeat counting.

**Locked Doors** (Segment Tree)
1. Find that we need to solve this problem in at most nlogn time.
2. Observe that the opened doors must be in one interval, so start thinking about using segment tree.
3. The valid interval should meet some condition. 
   - The start point should be in this interval.
   - The border of the interval should be valid. Since we prefer to open the door with smaller difficulty first, so there is some restrictions.
4. Since we know the range of left boundary, we can use binary search to find the correct left boundary.
   - The left boundary should between (start - k, start).
   - Use the segment tree to track the maximum difficulty of interval.
   - Suppose the left boundary is "left", if the left one is smaller than the maximum of [start, right], and the maximum of the left part is smaller than the right part, which means, we should extend more on the left, because, we prefer to opening the left door to opening maximum door on the right. And vise verse.
5. Then we can find out the interval with length (k-1).
   - We need to open the last door, then there are two situations.
   - One side is already at the boundary, so we need to open the another side.
   - Open the door with the smaller difficulty.
