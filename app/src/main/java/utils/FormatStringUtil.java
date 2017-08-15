package utils;

import java.util.ArrayList;

/**
 * Created by Kyle Wolff on 8/14/17.
 */

public class FormatStringUtil {

	public static String addDelimiter(ArrayList<CharSequence> strings, String delimiter) {

		final StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < strings.size(); i++) {
			stringBuilder.append(strings.get(i));

			if (i < strings.size() - 1) {
				stringBuilder.append(delimiter);
			}
		}

		return stringBuilder.toString();
	}
}
