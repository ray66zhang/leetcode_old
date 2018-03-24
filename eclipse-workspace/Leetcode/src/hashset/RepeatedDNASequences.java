package hashset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Medium
 * 
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * For example,
 * 
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 
 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * Attempted: 1
 * 
 * Percentile: 17.65%
 *
 */
public class RepeatedDNASequences {

	public List<String> findRepeatedDnaSequences(String s) {
		if (s == null || s.length() < 10) {
			return new ArrayList<>();
		}
		List<String> res = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i <= s.length() - 10; i++) {
			String str = s.substring(i, i + 10);
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		for (String key : map.keySet()) {
			if (map.get(key) > 1) {
				res.add(key);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		RepeatedDNASequences obj = new RepeatedDNASequences();
		System.out.println(obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));

	}

}
