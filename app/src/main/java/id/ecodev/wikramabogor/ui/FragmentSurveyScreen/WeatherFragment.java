package id.ecodev.wikramabogor.ui.FragmentSurveyScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ecodev.wikramabogor.Model.Main;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.HomeScreen.HomeActivity;
import id.ecodev.wikramabogor.ui.SurveyScreen.DonePresenter;
import id.ecodev.wikramabogor.ui.SurveyScreen.DoneView;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements BlockingStep,DoneView {


    public WeatherFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_sea)
    TextView tvSea;
    @BindView(R.id.tv_city)
    TextView tvCity;

    DonePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_weather, container, false);


        presenter = new DonePresenter(this);

        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_next_quiz)
    public void onBtnNextQuizClicked() {
        PrefManager prefManager = new PrefManager(getActivity());
        String member_id   = Integer.toString(prefManager.getUserId());
        String longtitude  = Double.toString(SurveyActivity.longtitude);
        String latitude    = Double.toString(SurveyActivity.latitude);
        String temperature = tvTemp.getText().toString();
        String sea_level   = tvSea.getText().toString();
        String city        = tvCity.getText().toString();
        String address    = tvAddress.getText().toString();
        presenter.createSurvey(member_id,longtitude,latitude,temperature,sea_level,city,address,SurveyActivity.city,SurveyActivity.state);
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
        presenter.loadWeather(Double.toString(SurveyActivity.latitude),Double.toString(SurveyActivity.longtitude));
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSuccessLoadWeather(Main main) {
        tvTemp.setText(Double.toString(main.getTemp()));
        tvSea.setText(Double.toString(main.getSea_level()));
        tvAddress.setText(SurveyActivity.text_address);
        tvCity.setText(SurveyActivity.place_name  );
    }

    @Override
    public void onSuccessCreateSurvey(String status) {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onShow() {
        super.showLoading(getActivity());
    }

    @Override
    public void onHide() {
        super.dismissLoading();
    }

    @Override
    public void onError(String error) {
        super.showError(error);
    }

    @Override
    public void getMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void getHttp(String http) {
        super.showHttp(http);
    }
}
