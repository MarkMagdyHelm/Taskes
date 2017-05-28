package mark.com.login.FacebookLogin.Control.DB;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mina on 4/10/2016.
 */
public class Pref {

    public static final String PREFS_NAME = "Registeration_PREFS";
    private static final String FACEBBOK_LOGIN_KEY = "remember_login";
    private static final String ACCESS_TOKEN_KEY = "Access_token";
    private static final String PROFILE_ID_KEY = "profile_id";
    private static final String USERNAME_KEY = "username";
    private static final String IMAGE_URL_KEY = "image_url";


    private Context context;

    public Pref(Context context) {
        this.context = context;
    }

    public void saveLoginRemember(boolean login) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putBoolean(FACEBBOK_LOGIN_KEY, login);
        editor.commit();
    }

    public boolean isLoginRemember() {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(FACEBBOK_LOGIN_KEY, true);
    }

    public void setAccessToken(String token) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(ACCESS_TOKEN_KEY, token);
        editor.commit();
    }

    public String getAccessToken() {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(ACCESS_TOKEN_KEY, "");
    }

    public String getProfileId() {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(PROFILE_ID_KEY, "");
    }



    public void setUserName(String username) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(USERNAME_KEY, username);
        editor.commit();
    }

    public String getUserName() {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(USERNAME_KEY, "");
    }
}
