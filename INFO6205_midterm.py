#Minxi Han

#Question 1:
class Solution:
    def missingCompoment(self, nums, lower, upper):
        new_nums = [lower-1] + nums + [upper+1]
        box = []
        for i in range(len(new_nums)-1):
            if new_nums[i+1] - new_nums[i] == 2:
                box.append(str(new_nums[i+1] - new_nums[i]))
            elif new_nums[i+1] - new_nums[i] > 2:
                box.append(str(new_nums[i]+1) + "->" + str(new_nums[i+1]-1))

        return box



# #Question 2:
class ListNode:
    def _init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def addTwoLinkedList(self, l1, l2):
        dummy = ListNode(0)
        new_head = dummy
        carry = 0
        while l1 and l2:
            x = l1.val if l1 else 0
            y = l2.val if l2 else 0

            total = x+y+carry
            val = total%10
            carry = total//10

            new_head.next = ListNode(val)
            new_head = new_head.next

            if l1:
                l1 = l1.next

            if l2:
                l2 = l2.next
        
        if carry:
            new_head.next = ListNode(carry)

        return dummy.next



#Question 3:
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def constructTree(self, preorder, inorder):
        val = preorder[0]
        mid = inorder.index(val)

        root = TreeNode(val)
        root.left = self.constructTree(preorder[1:mid+1], inorder[:mid])
        root.right = self.constructTree(preorder[mid+1:], inorder[mid+1:])

        return root
    


#Question 4:
class Solution:
    def nonOverlappingIntervals(self, intervals):
        if not intervals:
            return intervals

        stack = []
        intervals.sort(key=lambda x:x[0])

        for intv in intervals:
            if not stack or stack[-1][-1] < intv[0]:
                stack.append(intv)
            else:
                stack[-1][-1] = max(stack[-1][-1], intv[-1])
        
        return stack