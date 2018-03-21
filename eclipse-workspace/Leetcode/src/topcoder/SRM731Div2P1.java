package topcoder;

import java.util.*;

/**
 * Hero has an infinite periodic string t. You are given the String s that is
 * the period of Hero's string. For example, if s = "abc", Hero's actual string
 * is t = "abcabcabcabc..." Let n be the length of string s. Hero is now going
 * to use the infinite string t to generate a new n-character string by doing
 * the following steps: He will choose an offset: a non-negative integer x. He
 * will choose the length of a step: a prime number p that is less than n. The
 * new string will consists of the first n characters we can read in the string
 * t if we start at the index x and after each character we move p positions to
 * the right. Formally, Hero's new string will consist of the following
 * characters, in order: t[x], t[x + p], t[x + 2*p], ..., t[x + (n-1)*p]. Find
 * and return the lexicographically smallest string Hero can produce.
 *
 * 
 */
public class SRM731Div2P1 {

	private boolean isPrime(int num) {
		if (num < 2)
			return false;
		if (num == 2)
			return true;
		if (num % 2 == 0)
			return false;
		for (int i = 3; i * i <= num; i += 2)
			if (num % i == 0)
				return false;
		return true;
	}

	public String getmin(String s) {
		char[] chars = s.toCharArray();
		int n = s.length();
		List<String> res = new ArrayList<>();
		for (int x = 0; x < n; x++) {
			for (int p = 2; p < n; p++) {
				if (isPrime(p)) {
					StringBuilder sb = new StringBuilder();
					int cur = x;
					sb.append((char) chars[cur % n]);
					System.out.println(sb.toString() + " " + chars[cur] % n);
					while (sb.length() < n) {
						sb.append(chars[(cur + p) % n]);
						cur += p;
					}
					res.add(sb.toString());
				}
			}
		}
		Collections.sort(res);
		return res.get(0);
	}

	public static void main(String[] args) {
		SRM731Div2P1 obj = new SRM731Div2P1();
		System.out.println(obj.getmin("fsdifyhsoe"));

	}

}
