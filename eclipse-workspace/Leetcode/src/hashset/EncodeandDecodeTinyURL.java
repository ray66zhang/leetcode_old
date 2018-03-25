package hashset;

import java.util.HashMap;
import java.util.Map;

/**
 * Medium
 * 
 * Note: This is a companion problem to the System Design problem: Design
 * TinyURL. TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need to
 * ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 *
 * Attempted: 1
 * 
 * Percentile: 65.34%
 */
public class EncodeandDecodeTinyURL {

	public int counter = 0;
	public Map<String, String> mapEncode = new HashMap<>();
	public Map<String, String> mapDecode = new HashMap<>();

	public String encode(String longUrl) {
		if (mapEncode.containsKey(longUrl)) {
			return mapEncode.get(longUrl);
		} else {
			String shortUrl = "http://tinyurl.com/" + Integer.toString(counter);
			mapEncode.put(longUrl, shortUrl);
			mapDecode.put(shortUrl, longUrl);
			counter++;
			return shortUrl;
		}
	}

	public String decode(String shortUrl) {
		if (mapDecode.containsKey(shortUrl)) {
			return mapDecode.get(shortUrl);
		} else {
			return "Not Found";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
