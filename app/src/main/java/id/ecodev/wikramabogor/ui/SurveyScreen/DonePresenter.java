package id.ecodev.wikramabogor.ui.SurveyScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.SurveyResponse;
import id.ecodev.wikramabogor.response.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonePresenter<V extends DoneView> extends BasePresenter {

    V doneView;

    public DonePresenter(V doneView) {
        this.doneView = doneView;
    }

    public void loadWeather(String latitude,String longtitude)
    {
        doneView.onShow();
        apiClass2.getWeather(latitude,longtitude,"fde0a6400c4f5b1a75fd003f75d0af5b","metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                doneView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    doneView.onSuccessLoadWeather(response.body().getMain());
                    doneView.onHide();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                doneView.onError(t.getMessage());
                doneView.onHide();
            }
        });
    }

    public void createSurvey(String member_id, String longtitude, String latitude, String temperature, String sea_level, String place_name,String address,String city,String state){
        doneView.onShow();
        apiClass.createSurvey(member_id,longtitude,latitude,temperature,sea_level,place_name,address,city,state).enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {
                doneView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    doneView.onSuccessCreateSurvey(response.body().getStatus());
                    doneView.onHide();
                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                doneView.onError(t.getMessage());
                doneView.onHide();
            }
        });
    }
}
