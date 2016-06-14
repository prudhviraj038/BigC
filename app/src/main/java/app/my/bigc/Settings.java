package app.my.bigc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

/**
 * Created by Chinni on 22-06-2015.
 */
public class Settings {
   public static final String SERVER_URL    = "http://clients.outlinedesigns.in/bigc/api/";
   static String STORE_ID="store_id";
   static String EMP_ID="emp_id";
   static String TYPE="type";
   static String NAME="name";
   static String S_TYPE="s_type";
   static String S_NAME="s_name";
   public static void set_store(Context context, String area_id,String type,String name) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(STORE_ID, area_id);
      editor.putString(S_TYPE, type);
      editor.putString(S_NAME, name);
      editor.commit();
   }
   public static String get_store(Context context) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
      return sharedPreferences.getString(STORE_ID, "-1");
   }
   public static String get_store_name(Context context) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
      return sharedPreferences.getString(S_NAME, "-1");
   }
   public static void set_emp_id(Context context, String area_id,String type,String name) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(EMP_ID, area_id);
      editor.putString(TYPE, type);
      editor.putString(NAME, name);
      editor.commit();
   }
   public static String get_emp_id(Context context) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
      return sharedPreferences.getString(EMP_ID, "-1");
    //  return  "-1";
   }
   public static   void forceRTLIfSupported(Activity activity)
   {
      SharedPreferences sharedPref;
      sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
      if (sharedPref.getString("lan", "-1").equals("en")) {
         Resources res = activity.getResources();
         // Change locale settings in the app.
         DisplayMetrics dm = res.getDisplayMetrics();
         android.content.res.Configuration conf = res.getConfiguration();
         conf.locale = new Locale("en".toLowerCase());
         res.updateConfiguration(conf, dm);
      }

      else if(sharedPref.getString("lan", "-1").equals("ar")){
         Resources res = activity.getResources();
         // Change locale settings in the app.
         DisplayMetrics dm = res.getDisplayMetrics();
         android.content.res.Configuration conf = res.getConfiguration();
         conf.locale = new Locale("ar".toLowerCase());
         res.updateConfiguration(conf, dm);
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
         }
      }

      else {
         Resources res = activity.getResources();
         // Change locale settings in the app.
         DisplayMetrics dm = res.getDisplayMetrics();
         android.content.res.Configuration conf = res.getConfiguration();
         conf.locale = new Locale("en".toLowerCase());
         res.updateConfiguration(conf, dm);
      }

   }
}
