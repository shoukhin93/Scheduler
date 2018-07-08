package test.example.com.devicescheduling.sharedPreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import test.example.com.devicescheduling.Constants;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "devicescheduling";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveAllowedNumber(String number, String name) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (TextUtils.isEmpty(name))
            name = Constants.NO_NAME;
        editor.putString(number, name);
        editor.apply();
    }

    public String getNameFromNumber(String number) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(number, null);
    }

    public boolean isAllowed(String number){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(number, null) != null;
    }
}