# 133. Clone Graph
"""
# Definition for a Node.
class Node(object):
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

import functools


class Solution(object):
    def cloneGraph(self, node):
        """
        :type node: Node
        :rtype: Node
        """
        self.visited = {}
        
        def dfs(node):
            if not node:
                return
            
            if node in self.visited:
                return self.visited[node]
            
            clone = Node(node.val, None)
            self.visited[node] = clone
            
            for new_node in node.neighbors:
                clone.neighbors.append(dfs(new_node))
                
            return self.visited[node]
        
        return dfs(node)
            

# 847. Shortest Path Visiting All Nodes
class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        queue = [(i,1<<i,0) for i in range(n)]
        visited = {(i,1<<i) for i in range(n)}
        
        while queue:
            u, mask, dic = queue.pop(0)
            if mask == (1<<n)-1:
                return dic
            
            for x in graph[u]:
                next_mask = mask | (1<<x)
                if (x, next_mask) not in visited:
                    queue.append((x, next_mask, dic+1))
                    visited.add((x, next_mask))
        
        return 0


# 2065. Maximum Path Quality of a Graph
class Solution:
    def maximalPathQuality(self, values: List[int], edges: List[List[int]], maxTime: int) -> int:
        
        def backtracking(u, past_time, curr_values):
            if u==0:
                self.res = max(self.res, curr_values)
                
            for v, time in graph[u]:
                if past_time+time<=maxTime:
                    if not self.visited[v]:
                        self.visited[v]+=1
                        backtracking(v, past_time+time, curr_values+values[v])
                        self.visited[v]-=1
                    else:
                        backtracking(v, past_time+time, curr_values)
                    
        
        import collections
        graph = collections.defaultdict(list)
        for u,v,t in edges:
            graph[u].append((v,t))
            graph[v].append((u,t))
        
        self.res = 0
        self.visited = [0 for _ in range(len(values))]
        self.visited[0] = 1
        backtracking(0,0,values[0])
        
        return max(self.res, values[0])