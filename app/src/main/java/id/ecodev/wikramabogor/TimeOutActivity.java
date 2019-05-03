package id.ecodev.wikramabogor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ecodev.wikramabogor.ui.HomeScreen.HomeActivity;
import id.ecodev.wikramabogor.ui.IntroduceScreen.IntroduceActivity;
import id.ecodev.wikramabogor.ui.PrepareSurveyScreen.PrepareActivity;
import id.ecodev.wikramabogor.ui.SplashScreen.SplashActivity;

public class TimeOutActivity extends AppCompatActivity {


    @BindView(R.id.btnRepeat)
    Button btnRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_out);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnRepeat)
    public void onViewClicked() {
        switch (getIntent().getStringExtra("class")) {
            case "introduce":
                openIntent(new Intent(this, IntroduceActivity.class));
                break;
            case "survey":
                openIntent(new Intent(this, HomeActivity.class));
                break;
            case "website":
                openIntent(new Intent(this, HomeActivity.class));
                break;
            case "prepare":
                openIntent(new Intent(this, PrepareActivity.class));
                break;
            case "secondFragment":
                openIntent(new Intent(this, PrepareActivity.class));
                break;
            default:
                openIntent(new Intent(this, SplashActivity.class));
                break;
        }
    }

    private void openIntent(Intent intent) {
        startActivity(intent);
        finish();
    }
}
