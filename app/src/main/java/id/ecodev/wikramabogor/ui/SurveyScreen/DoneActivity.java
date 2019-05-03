package id.ecodev.wikramabogor.ui.SurveyScreen;

import android.os.Bundle;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;

import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.adapter.StepperAdapter;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.ui.FragmentSurveyScreen.MapsFragment;
import id.ecodev.wikramabogor.ui.FragmentSurveyScreen.WeatherFragment;

public class DoneActivity extends BaseActivity {

    public static StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        super.setUpActionBar("Kembali");
        String[] titles = new String[]{"Daerah", "Cuaca dan Daerah"};
        Step[]   steps  = new Step[]{
                new MapsFragment(),
                new WeatherFragment(),
        };

        StepperAdapter stepperAdapter = new StepperAdapter(getSupportFragmentManager(),this,titles,steps);

        stepperLayout = findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(stepperAdapter);
    }
}
