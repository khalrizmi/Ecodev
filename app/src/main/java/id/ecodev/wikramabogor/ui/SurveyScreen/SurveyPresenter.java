package id.ecodev.wikramabogor.ui.SurveyScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.SurveyResponse;
import id.ecodev.wikramabogor.response.WebsiteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyPresenter<V extends SurveyView> extends BasePresenter {

    V surveyView;

    public SurveyPresenter(V surveyView) {
        this.surveyView = surveyView;
    }

    public void loadSurvey(String id)
    {
        surveyView.onShow();
        apiClass.getSurvey(id).enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {
                surveyView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    surveyView.onSuccessLoadSurvey(response.body().getSurveys());
                    surveyView.onHide();
                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                surveyView.onError(t.getMessage());
                surveyView.onHide();
            }
        });
    }

    public void loadWebsite(int id)
    {
        surveyView.onShow();
        apiClass.getWebsite(id).enqueue(new Callback<WebsiteResponse>() {
            @Override
            public void onResponse(Call<WebsiteResponse> call, Response<WebsiteResponse> response) {
                surveyView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    surveyView.onSucessLoadWebsite(response.body().getWebsites());
                    surveyView.onHide();
                }
            }

            @Override
            public void onFailure(Call<WebsiteResponse> call, Throwable t) {
                surveyView.onError(t.getMessage());
                surveyView.onHide();
            }
        });
    }
}
