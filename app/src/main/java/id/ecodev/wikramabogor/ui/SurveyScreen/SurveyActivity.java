package id.ecodev.wikramabogor.ui.SurveyScreen;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;

import butterknife.ButterKnife;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.adapter.StepperAdapter;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.ui.AddSurveyScreen.FirstFragment;
import id.ecodev.wikramabogor.ui.AddSurveyScreen.SecondFragment;

public class SurveyActivity extends BaseActivity {
    public static StepperLayout stepperLayout;
    public static double latitude;
    public static double longtitude;
    public static String text_address;
    public static String place_name;
    public static String city;
    public static String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);

        String[] titles = new String[]{"Kategori", "Objek"};
        Step[]   steps  = new Step[]{
                new FirstFragment(),
                new SecondFragment(),
        };

        StepperAdapter stepperAdapter = new StepperAdapter(getSupportFragmentManager(),this,titles,steps);

        stepperLayout = findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(stepperAdapter);

        super.setUpActionBar("Tambah Objek");
        verifyStoragePermissions(this);
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(getApplicationContext(), PrepareActivity.class));
        super.onBackPressed();
    }

    public static void surveyProcces() {
//        f (result.equals("success"))
//        {
//            startActivity(new Intent(getActivity(), PrepareActivity.class));
//        }else if(result.equals("bad_name")){
//            Toast.makeText(getActivity(), "Nama Objek mengandung kata terlarang", Toast.LENGTH_SHORT).show();
//        }else if (result.equals("bad_desc")){
//            Toast.makeText(getActivity(), "Deskripsi Objek mengandung kata terlarang", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//        }
    }

}
