package id.ecodev.wikramabogor.ui.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.config.ApplicationConfig;
import id.ecodev.wikramabogor.ui.IntroduceScreen.IntroduceActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        super.setHideActionBar();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent detail = new Intent(getApplicationContext(), IntroduceActivity.class);
                detail.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(detail);
            }
        }, ApplicationConfig.SPLASHSCREEN_DELAY);
    }
}
