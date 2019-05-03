package id.ecodev.wikramabogor.ui.SurveyScreen;

import id.ecodev.wikramabogor.Model.Main;
import id.ecodev.wikramabogor.base.BaseView;

public interface DoneView extends BaseView {
    void onSuccessLoadWeather(Main main);
    void onSuccessCreateSurvey(String status);
}
