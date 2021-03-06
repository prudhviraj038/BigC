package app.my.bigc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static app.my.bigc.CommonUtilities.SENDER_ID;
import static app.my.bigc.CommonUtilities.displayMessage;

public class GCMIntentService extends GCMBaseIntentService {

    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
        Log.d("NAME", "registered");
        ServerUtilities.register(context, "tourism", "tourism", registrationId);
        Settings.set_gcmid(context,registrationId);
    }

    /**
     * Method called on device un registred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
        Settings.set_gcmid(context, "-1");
    }

    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String type = intent.getExtras().getString("type","Offer");
        String data_temp = intent.getExtras().getString("data","");
        JSONObject jsonObject = new JSONObject();
        try {
             jsonObject = new JSONObject(data_temp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(type.equals("Offer")) {
            String title= "",message= "",image_url= "",product_id= "",category_id = "";

            try {
                 title = jsonObject.getString("title");
                 message = jsonObject.getString("message");
                 image_url = jsonObject.getString("image");
                 product_id = jsonObject.getString("start_date");
                 category_id = jsonObject.getString("expiry_date");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            displayMessage(context, message);
            generateCustomNotification(type, context, title, message, image_url, product_id, category_id,jsonObject);
        }

      else if(type.equals("Missed_Customer")) {

            String title= "",message= "",image_url= "",product_id= "",category_id = "";

            try {
                category_id = jsonObject.getString("email");
                title = jsonObject.getString("name");
                message = jsonObject.getString("reason");
                image_url = jsonObject.getString("image");
                product_id = jsonObject.getString("phone");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            displayMessage(context, message);
            generateCustomNotification(type, context, title, message, image_url, product_id, category_id,jsonObject);
        }
        else if(type.equals("Welcome")) {

            String title= "",message= "",image_url= "",product_id= "",category_id = "";

            try {
                title = jsonObject.getString("title");
                message = jsonObject.getString("message");
                image_url = jsonObject.getString("image");
                product_id = jsonObject.getString("title");
                 category_id = jsonObject.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            displayMessage(context, message);
            generateCustomNotification(type, context, title, message, image_url, product_id, category_id,jsonObject);
        }
        else{
            displayMessage(context, "welcome");
            generateCustomNotification(type, context, "welcome", "welcome", "welcome", "welcome", "welcome",jsonObject);

        }

    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
       // generateCustomNotification(context, message,image);
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.bigclogo;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, SplashActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
       // notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);

    }


    private static void generateCustomNotification(String type,Context context,String title,String message,String image_url,String product_id,String category_id,JSONObject jsonObject){

        //Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lock);

        //String imageurl = "http://mamacgroup.com//qatar//uploads//products//DayepIMG_20150512_213312516.jpg";
        Bitmap notify_bitmap = getBitmapFromURL(image_url);
        Log.e("image_url",image_url);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setAutoCancel(true)
       . setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.bigclogo).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.bigclogo))
                .setContentText(message);

        NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
        bigPicStyle.bigPicture(notify_bitmap);
        bigPicStyle.setBigContentTitle(title);
        bigPicStyle.setSummaryText(message);
        mBuilder.setStyle(bigPicStyle);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent;
        resultIntent = new Intent(context, SplashActivity.class);
        if(type.equals("Offer"))
        {
          //  resultIntent = new Intent(context, Offer_Screen_Activity.class);
            resultIntent.putExtra("goto","Offer");
            resultIntent.putExtra("data",jsonObject.toString());


        }
        else if(type.equals("Missed_Customer")){
            resultIntent.putExtra("goto","Missed_Customer");
            resultIntent.putExtra("data",jsonObject.toString());
        }
        else if(type.equals("Welcome")){
            resultIntent.putExtra("goto","Offer");
            resultIntent.putExtra("data",jsonObject.toString());
        }
        else{
            resultIntent = new Intent(context, SplashActivity.class);
            resultIntent.putExtra("goto","Offer");
        }
        // The stack builder object will contain an artificial back stack for
        // the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(SplashActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify(100, mBuilder.build());
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}