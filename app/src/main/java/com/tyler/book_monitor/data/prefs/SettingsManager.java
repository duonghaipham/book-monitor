package com.tyler.book_monitor.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
    private static final String APP_SETTINGS = "com.group21.gallery";

    private SettingsManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static int getThemeMode(Context context) {
        return getSharedPreferences(context).getInt("THEME_MODE", 1);
    }

    public static void setThemeMode(Context context, int themeMode) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt("THEME_MODE", themeMode);
        editor.apply();
    }

    public static int getFont(Context context) {
        return getSharedPreferences(context).getInt("FONT", 0);
    }

    public static void setFont(Context context, int font) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt("FONT", font);
        editor.apply();
    }

    public static int getFontSize(Context context) {
        return getSharedPreferences(context).getInt("FONT_SIZE", 18);
    }

    public static void setFontSize(Context context, int fontSize) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt("FONT_SIZE", fontSize);
        editor.apply();
    }

    public static boolean getNavigation(Context context) {
        return getSharedPreferences(context).getBoolean("NAVIGATION", true);
    }

    public static void setNavigation(Context context, boolean navigation) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean("NAVIGATION", navigation);
        editor.apply();
    }
}
