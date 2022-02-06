import java.util.*;

// Minxi Han
// NUID: 002954176
public class SearchingAndSortingContinuity {
	public static void main(String[] args) {
		// 35. Search Insert Position
		int[] nums = {1, 3, 5, 6};
		int target_position = 2;
		System.out.println(searchInsert(nums, target_position));

		// 540. Single Element in a Sorted Array
		int[] sorted_array = {1, 1, 2, 3, 3, 4, 4, 8, 8};
		System.out.println(singleNonDuplicate(sorted_array));

		// 153. Find Minimum in Rotated Sorted Array
		int[] rotated_sorted_array = {4, 5, 6, 7, 0, 1, 2};
		System.out.println(findMin(rotated_sorted_array));

		// 253. Meeting Rooms II
		int[][] rooms = {{0, 30}, {5, 10}, {15, 20}};
		System.out.println(minMeetingRooms(rooms));

		// 347. Top K Frequent Elements
		int[] elements = {1, 1, 1, 2, 2, 3};
		int k = 2;
		System.out.println(Arrays.toString((topKFrequent(elements, k))));

		// 16. 3Sum Closest
		int[] sum_nums = {-1, 2, 1, -4};
		int sum_target = 1;
		System.out.println(threeSumClosest(sum_nums, sum_target));

		// 57. Insert Interval
		int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
		int[] newInterval = {4, 8};
		System.out.println(Arrays.deepToString(insert(intervals, newInterval)));

		// 435. Non-overlapping Intervals
		int[][] overlapping_intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
		System.out.println(eraseOverlapIntervals(overlapping_intervals));

		// 986. Interval List Intersections
		int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
		int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
		System.out.println(Arrays.deepToString(intervalIntersection(A, B)));

		// 18. 4Sum
		int[] four_sum_nums = {1, 0, -1, 0, -2, 2};
		int four_sum_target = 0;
		System.out.println(fourSum(four_sum_nums, four_sum_target));
	}

	// 35. Search Insert Position
	public static int searchInsert(int[] nums, int target) {
		int n = nums.length;
		int lo = 0;
		int hi = n - 1;
		if (target < nums[lo]) {
			return 0;
		} else if (target > nums[hi]) {
			return nums.length;
		}

		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 2);
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return lo;
	}

	// 540. Single Element in a Sorted Array
	public static int singleNonDuplicate(int[] nums) {
		int n = nums.length;
		int lo = 0, hi = n - 1;
		while (lo < hi) {
			int mid = lo + ((hi - lo) >> 2);
			if (mid % 2 != 0)
				mid--;
			if (nums[mid] == nums[mid + 1]) {
				lo = mid + 2;
			} else {
				hi = mid;
			}
		}
		return nums[lo];
	}

	// 153. Find Minimum in Rotated Sorted Array
	public static int findMin(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		while (lo < hi) {
			int mid = lo + ((hi - lo) >> 2);
			if (nums[mid] < nums[hi]) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return nums[lo];
	}

	// 253. Meeting Rooms II
	public static int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		PriorityQueue<Integer> pq = new PriorityQueue();
		pq.add(intervals[0][1]);
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= pq.peek()) pq.poll();
			pq.add(intervals[i][1]);
		}
		return pq.size();
	}

	// 347. Top K Frequent Elements
	public static int[] topKFrequent(int[] nums, int k) {
		int[] ret = new int[k];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
		var pq = new PriorityQueue<Map.Entry<Integer, Integer>>(Comparator.comparingInt(Map.Entry::getValue));
		for (Map.Entry<Integer, Integer> entry : entries) {
			pq.add(entry);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		for (int i = k - 1; i >= 0; i--) {
			ret[i] = Objects.requireNonNull(pq.poll()).getKey();
		}
		return ret;
	}

	// 16. 3Sum Closest
	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int ans = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length; i++) {
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {
				int sum = nums[lo] + nums[hi] + nums[i];
				if (Math.abs(target - sum) < Math.abs(target - ans))
					ans = sum;
				if (sum > target)
					hi--;
				else if (sum < target)
					lo++;
				else
					return ans;
			}
		}
		return ans;
	}

	// 57. Insert Interval
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> ret = new ArrayList<>();
		int i = 0;
		while (i < intervals.length && intervals[i][1] < newInterval[0]) {
			ret.add(intervals[i]);
			i++;
		}

		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
			newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			i++;
		}

		ret.add(newInterval);
		while (i < intervals.length) {
			ret.add(intervals[i]);
			i++;
		}
		return ret.toArray(new int[ret.size()][2]);
	}

	// 435. Non-overlapping Intervals
	public static int eraseOverlapIntervals(int[][] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		int remove = 0;
		int flag = intervals[0][1];
		for (int i = 1; i < intervals.length; i++) {
			if (flag > intervals[i][0]) {
				remove++;
				flag = Math.min(flag, intervals[i][1]);
			} else flag = intervals[i][1];
		}
		return remove;
	}

	// 986. Interval List Intersections
	public static int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> ret = new ArrayList();
		int i = 0, j = 0;
		while (i < A.length && j < B.length) {
			int lo = Math.max(A[i][0], B[j][0]);
			int hi = Math.min(A[i][1], B[j][1]);
			if (lo <= hi) ret.add(new int[]{lo, hi});
			if (A[i][1] < B[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		return ret.toArray(new int[ret.size()][]);
	}

	// 18. 4Sum
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return ret;
		}
		Arrays.sort(nums);
		int length = nums.length;
		for (int i = 0; i < length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}
			if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
				continue;
			}
			for (int j = i + 1; j < length - 2; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}
				if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
					continue;
				}
				int lo = j + 1;
				int hi = length - 1;
				while (lo < hi) {
					int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
					if (sum == target) {
						ret.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
						lo++;
						while (lo < hi && nums[lo] == nums[lo - 1]) {
							lo++;
						}
						hi--;
						while (lo < hi && nums[hi] == nums[hi + 1]) {
							hi--;
						}
					} else if (sum < target) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}
		return ret;
	}
}
