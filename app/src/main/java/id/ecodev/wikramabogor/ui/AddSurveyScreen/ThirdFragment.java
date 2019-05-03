package id.ecodev.wikramabogor.ui.AddSurveyScreen;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import id.ecodev.wikramabogor.Model.Category;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends BaseFragment implements BlockingStep,AddSurveyView {


    public ThirdFragment() {
        // Required empty public constructor
    }


    AddSurveyPresenter addSurveyPresenter;

    Button btnVerif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_third, container, false);

        btnVerif = view.findViewById(R.id.btnVerif);
        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyActivity.stepperLayout.setCurrentStepPosition(3);
            }
        });

        return view;
    }


    private void init() {

    }

    private void setResult() {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSuccessLoadCategories(List<Category> categories) {

    }

    @Override
    public void onSuccessCreateObjek(String result) {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void getMessage(String message) {

    }

    @Override
    public void getHttp(String http) {

    }
}
