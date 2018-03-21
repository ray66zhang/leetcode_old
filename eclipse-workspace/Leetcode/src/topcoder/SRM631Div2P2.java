package topcoder;

import java.math.BigInteger;

public class SRM631Div2P2 {

	public BigInteger factorial2(int n) {
		BigInteger fact = new BigInteger("1");
		for (int i = 1; i <= n; i++) {
			fact = fact.multiply(new BigInteger(i + ""));
		}
		return fact;
	}

	public BigInteger choose2(int n, int k) {
		return (factorial2(n).divide((factorial2(k).multiply(factorial2(n - k)))));
	}

	public String checkOdds(int n, int k) {
		BigInteger happy = new BigInteger("0");
		BigInteger sad = new BigInteger("0");
		for (int i = 0; i <= n; i++) {
			BigInteger t = choose2(n, i);
			long p = i * (n - i);
			if (p < k) {
				sad = sad.add(t);
			} else {
				happy = happy.add(t);
			}
		}

		if (happy.compareTo(sad) > 0)
			return "High";
		else if (happy.compareTo(sad) < 0)
			return "Low";
		else
			return "Equal";
	}

	public static void main(String[] args) {
		SRM631Div2P2 obj = new SRM631Div2P2();
		//System.out.println(obj.checkOdds(1, 1));
		double d = 1;
		for(int i =1; i<497; i++) {
			d *=i;
		}
		System.out.println(d);
	}

}
