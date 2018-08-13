package loctall.com.childtracking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {
    private static boolean splashLoaded = false;
    private ProgressBar mProgressBar;
    private static final String TAG = "SplashActivity";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: " + String.valueOf(splashLoaded));

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (!splashLoaded) {

            Log.d(TAG, "onCreate: ");
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);

            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.splash_screen);
            mProgressBar = findViewById(R.id.progress);
            new Thread(new Runnable() {
                public void run() {
                    doWork();
                    startApp();
                }
            }).start();

            splashLoaded = true;
        }

    }

    private void doWork() {
        Log.d(TAG, "doWork: ");
        for (int progress = 0; progress < 100; progress += 10) {
            try {
                Thread.sleep(100);
                mProgressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "doWork: " + e.getMessage());
            }
        }
    }

    private void startApp() {
        Log.d(TAG, "startApp: ");
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }


//         public final static int THEME_BLUE = 1;
//          public final static int THEME_GREEN = 2;


}

