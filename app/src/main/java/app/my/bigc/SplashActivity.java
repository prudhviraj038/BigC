package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by sriven on 6/7/2016.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        ImageView imageView=(ImageView)findViewById(R.id.splash);
        imageView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, SplashScreen.class);
                intent.putExtra("goto",getIntent().getStringExtra("goto"));
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}
