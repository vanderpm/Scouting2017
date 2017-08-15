package utils;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Kyle Wolff on 8/14/17.
 */

public class ViewUtils {

	public static boolean requestFocus(View v, Activity activity) {

		if(v.requestFocus()){
			activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
			return true;
		}

		return false;
	}
}
