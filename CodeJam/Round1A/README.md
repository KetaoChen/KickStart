# Google Code Jam 2020 Round 1A (rank 1630)

## What I learned
1. Use array to track the next number if we need to remove numbers.
2. Use int[][] instead of boolean[][] to track the state of visiting. Specifically, use the round number to a avoid duplication.

---
**Pattern Matching**
1. Think about what kind of pattern can match. Then realize that all the prefix(before the first *), and all the suffix(after the last *) should match for all the patterns.
2. Get the longest prefix and suffix.
3. Greedy add the mid parts of all the patterns.

**Pascal Walk**
1. The Pascal Triangle is exponential increasing, so we can have very large number at low depth.
2. Calculating the prefix of each row, then we can determine whether it is safe to go to the next level.
3. Choose steps from left, down and right down. 

**Square Dance**
1. There are a lot of round, each round we need to compare cells with their neighbours.
2. Need to find out that the cell will be eliminated this round must be connected to the nodes eliminated in current round, so we can use BFS to simulate each round.
3. Use the first trick, we can find out the neighbours quickly when it becomes sparse. 
4. Use the second trick to avoid add points to the checkList and removeList repeat.


