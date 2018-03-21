package topcoder;

import java.util.*;

public class SRM631Div2P1 {

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
					sb.append((char)chars[cur%n]);
					System.out.println(sb.toString() + " " + chars[cur]%n);
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
		SRM631Div2P1 obj = new SRM631Div2P1();
		System.out.println(obj.getmin("fsdifyhsoe"));

	}

}
