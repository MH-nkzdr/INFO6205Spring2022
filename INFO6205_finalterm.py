# Question1
def stringCompression(chars):
    # create left and right pointers
    left, right = 0, 0
    max_length = 1
    # initiate a chars_list to store original chars
    chars_list = list(chars)
    # initiate a compressed_list to store compressed chars, and put chars_list[0] in
    compressed_list = [chars_list[0]]

    while right < len(chars_list):
        # check max_length if the consecutive chars are the same
        if chars_list[left] == chars_list[right]:
            max_length = max(max_length, right-left+1)

        # if get a new char, put the num of last char into the compressed_list
        else:
            if max_length >= 1:
                # put digit in one by one
                for i in str(max_length):
                    compressed_list.append(i)
            # add the new char into chars_list
            compressed_list.append(chars_list[right])
            # move left pointer and reset the max_length
            left = right
            max_length = 1

        right += 1

    # put the amount of last char into the chars_list
    if max_length > 1:
        for i in str(max_length):
            compressed_list.append(i)

    compressed = "".join(compressed_list)

    # return compressed or chars, which has the smaller length
    if len(compressed) <= len(chars):
        return compressed
    return chars



# Question2:
def numIslands(grid):

    if not grid:
        return 0

    def dfs(i, j):
        # if out of index or not a island, directly jump out this dfs
        if (i < 0 or i > m-1) or (j < 0 or j > n-1) or grid[i][j] != "1":
            return

        # set all visited islands as "2" to avoid repeated search
        grid[i][j] = "2"
        # start search for 4-directional step
        dfs(i-1, j)
        dfs(i+1, j)
        dfs(i, j-1)
        dfs(i, j+1)

    m, n = len(grid), len(grid[0])
    islands = 0
    for i in range(m):
        for j in range(n):
            # get into dfs if find a island
            if grid[i][j] == "1":
                islands += 1
                dfs(i, j)

    return islands



# Question3:
def groupAnagrams(strs):

    # initiate a map to store all str be like: {sorted: [str1, str]}
    import collections
    res = collections.defaultdict(list)

    # traversal strs
    for st in strs:
        # put sorted st as key, if a new str with the same key, append into res[key]
        key = "".join(sorted(st))
        res[key].append(st)

    # only need to return dict.values()
    return res.values()



# Question4:
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

def lowestCommonAncestor(root, p, q):

    def dfs(node):
        if not node:
            return node

        if node in (p, q):
            return node

        left_lca = dfs(node.left)
        right_lca = dfs(node.right)

        if left_lca and right_lca:
            return node
        if left_lca:
            return left_lca
        if right_lca:
            return right_lca

    return dfs(root)