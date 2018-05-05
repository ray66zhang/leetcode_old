package sorting;

/**
 * Medium
 * 
 * What if the citations array is sorted in ascending order? Could you optimize
 * your algorithm?
 *
 */
public class HIndexII {

	public int hIndex(int[] citations) {
		int len = citations.length;
		int index = 0;
		int start = 0;
		int end = len - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (citations[mid] <= (len - mid)) {
				index = Math.max(index, Math.min(citations[mid], (len - mid)));
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return len - start;
	}

	public static void main(String[] args) {
		HIndexII obj = new HIndexII();
		int[] citations = { 100, 100, 100 };
		System.out.println(obj.hIndex(citations));

	}

}
