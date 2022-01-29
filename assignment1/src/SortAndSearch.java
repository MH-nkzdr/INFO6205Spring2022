import java.util.*;

// Minxi Han
// NUID: 002954176
public class SortAndSearch {

	public static void main(String[] args) {

		// 75. Sort Colors
		int[] colors = {2, 0, 2, 1, 1, 0};
		System.out.println(Arrays.toString(sortColors(colors)));

		// 229. Majority Element II
		int[] elements = {3, 2, 3};
		System.out.println(majorityElement(elements));

		// 274. H-Index
		int[] citations = {3,0,6,1,5};
		System.out.println(hIndex(citations));

		// 349. Intersection of Two Arrays
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2, 2};
		System.out.println(Arrays.toString(intersection(nums1, nums2)));

		// 658. Find K Closest Elements
		int[] arr = {1,2,3,4,5};
		int k = 4;
		int x = 3;
		System.out.println(findClosestElements(arr, k, x));

		// 692. Top K Frequent Words
		String[] wordlist = {"i","love","leetcode","i","love","coding"};
		int topK = 2;
		System.out.println(topKFrequent(wordlist, topK));

		// 767. Reorganize String
		String S = "aab";
		System.out.println(reorganizeString(S));

		// 791. Custom Sort String
		String order = "cba";
		String string = "abcd";
		System.out.println(customSortString(order, string));

		// 969. Pancake Sorting
		int[] pancake = {3, 2, 4, 1};
		System.out.println(pancakeSort(pancake));

		// 1636. Sort Array by Increasing Frequency
		int [] nums = {1,1,2,2,2,3};
		System.out.println(Arrays.toString(frequencySort(nums)));
	}

	// 75. Sort Colors
	private static int[] sortColors(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int curr = 0;
		while (curr <= right) {
			if (nums[curr] == 2) {
				swap(nums, curr, right);
				right--;
			} else if (nums[curr] == 0) {
				swap(nums, curr, left);
				curr++;
				left++;
			} else {
				curr++;
			}
		}
		return nums;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}


	// 229. Majority Element II
	public static List<Integer> majorityElement(int[] nums) {
		if (nums == null || nums.length == 0)
			return new ArrayList<Integer>();

		HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
		for (int j : nums) {
			if (cnt.containsKey(j)) {
				cnt.put(j, cnt.get(j) + 1);
			} else {
				cnt.put(j, 1);
			}
		}

		List<Integer> res = new ArrayList<>();
		for (int num : cnt.keySet()) {
			if (cnt.get(num) > nums.length / 3) {
				res.add(num);
			}
		}
		return res;
	}


	// 274. H-Index
	public static int hIndex(int[] citations) {
		Arrays.sort(citations);
		int h = 0;
		int i = citations.length - 1;
		while (i >= 0 && citations[i] > h) {
			i--;
			h++;
		}
		return h;
	}


	// 349. Intersection of Two Arrays
	public static int[] intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
			return new int[0];
		}

		Set<Integer> seen = new HashSet<>();
		Set<Integer> retSet = new HashSet<>();
		for (int i : nums1) {
			seen.add(i);
		}

		for (int j : nums2) {
			if (seen.contains((j))) {
				retSet.add(j);
			}
		}

		int[] retArr = new int[retSet.size()];
		int index = 0;
		for (int i : retSet) {
			retArr[index++] = i;
		}
		return retArr;
	}


	// 658. Find K Closest Elements
	public static List<Integer> findClosestElements(int[] arr, int k, int x) {
		int l = 0;
		int r = arr.length-k;
		List<Integer> res = new ArrayList<>();
		while (l < r) {
			int mid = (l+r)/2;
			if (x-arr[mid] > arr[mid+k]-x) {
				l=mid+1;
			} else{
				r=mid;
			}
		}
		for (int i = l; i < l+k; i++) {
			res.add(arr[i]);
		}
		return res;
	}


	// 692. Top K Frequent Words
	public static List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> cnt = new HashMap<String, Integer>();
		for (String word : words) {
			cnt.put(word, cnt.getOrDefault(word, 0) + 1);
		}

		List<String> res = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
			res.add(entry.getKey());
		}

		Collections.sort(res, (word1, word2) -> cnt.get(word1) == cnt.get(word2) ?
				word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1));
		return res.subList(0, k);
	}


	// 767. Reorganize String
	public static String reorganizeString(String S) {
		if (S == null || S.length() <= 0) {
			return "";
		}

		char[] chars = S.toCharArray();
		int[] letters = new int[26];
		int length = S.length();
		for (int i = 0; i < length; i++) {
			letters[chars[i] - 'a']++;
		}

		int threshold = (length + 1) >> 1;
		int max = 0;
		int maxIndex = 0;
		int curCount;
		for (int i = 0; i < 26; i++) {
			curCount = letters[i];
			if (max < curCount) {
				max = curCount;
				maxIndex = i;
				if (curCount > threshold) {
					return "";
				}
			}
		}

		char[] resArr = new char[length];
		int curIndex = 0;
		while (letters[maxIndex]-- > 0) {
			resArr[curIndex] = (char) (maxIndex + 'a');
			curIndex += 2;
		}

		for (int i = 0; i < 26; i++) {
			while (letters[i]-- > 0) {
				if (curIndex >= length) {
					curIndex = 1;
				}
				resArr[curIndex] = (char) (i + 'a');
				curIndex += 2;
			}
		}
		return new String(resArr);
	}


	// 791. Custom Sort String
	public static String customSortString(String order, String s) {
		StringBuilder sb = new StringBuilder();
		int [] cnt = new int[26];
		for (char i : s.toCharArray())
			cnt[i-'a']++;

		for (int i = 0; i < order.length(); i++) {
			for (int j = 0; j < cnt[order.charAt(i)-'a']; j++) {
				sb.append(order.charAt(i));
			}
			cnt[order.charAt(i)-'a'] = 0;
		}

		for (int i = 0; i < 26; i++) {
			if (cnt[i] != 0) {
				for (int j = 0; j < cnt[i]; j++) {
					sb.append((char)(i+'a'));
				}
			}
		}
		return sb.toString();
	}

	// 969. Pancake Sorting
	public static List<Integer> pancakeSort(int[] arr) {
		List<Integer> ret = new ArrayList();
		int n = arr.length;
		while (n > 0) {
			for (int i = 0; i < n; i++) {
				if (arr[i] == n){
					flips(arr,i+1);
					flips(arr,n);
					ret.add(i+1);
					ret.add(n);
					break;
				}
			}
			n--;
		}
		return ret;
	}

	public static void flips(int[] arr, int i){
		int l = 0;
		int r = i-1;
		while (l <= r){
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			l++;
			r--;
		}
	}

	// 1636. Sort Array by Increasing Frequency
	public static int[] frequencySort(int[] nums) {
		Map<Integer, Integer> hashmap = new HashMap<>();
		int length = nums.length;
		for (int i = 0; i < length; i++) {
			hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
		for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
			pq.add(new int[]{entry.getValue(), entry.getKey()});
		}
		int[] ret = new int[length];
		int index = 0;
		while (!pq.isEmpty()){
			int[] ele = pq.poll();
			for (int i = 0; i < ele[0]; i++) {
				ret[index++] = ele[1];
			}
		}
		return ret;
	}
}