package id.ecodev.wikramabogor.ui.SurveyScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.Survey;
import id.ecodev.wikramabogor.Model.Website;
import id.ecodev.wikramabogor.base.BaseView;

public interface SurveyView extends BaseView {

    void onSuccessLoadSurvey(List<Survey> surveys);
    void onSucessLoadWebsite(List<Website> websites);
}
