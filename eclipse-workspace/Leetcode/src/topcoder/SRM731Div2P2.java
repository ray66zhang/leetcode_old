package topcoder;

import java.math.BigInteger;

/**
 * Hero is going to give a dancing class. Exactly n students already signed up
 * for the class. Hero doesn't know the genders of those students, so he makes
 * the simplifying assumption that each student is either a boy or a girl with
 * equal probability, and that these random choices are mutually independent.
 * 
 * During the class there will be k times when Hero needs to choose one boy and
 * one girl for the demonstration of a new dance move. Hero is happy if he is
 * able to choose k distinct boy-girl pairs for those demonstrations. (The same
 * person can be chosen multiple times, as long as it is always with a different
 * partner.)
 * 
 * Hero now wonders what is the probability that he will be happy. Return "High"
 * if the probability is strictly more than 50%, "Low" if it is strictly less
 * than 50%, and "Equal" if it is exactly 50%. Note that the return value is
 * case-sensitive.
 *
 */
public class SRM731Div2P2 {

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
		SRM731Div2P2 obj = new SRM731Div2P2();
		// System.out.println(obj.checkOdds(1, 1));
		double d = 1;
		for (int i = 1; i < 497; i++) {
			d *= i;
		}
		System.out.println(d);
	}

}
