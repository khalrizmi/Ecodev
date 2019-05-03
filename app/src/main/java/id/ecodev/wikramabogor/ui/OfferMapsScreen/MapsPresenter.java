package id.ecodev.wikramabogor.ui.OfferMapsScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.CustomResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsPresenter<V extends MapsView> extends BasePresenter {

    V mapsView;

    public MapsPresenter(V mapsView) {
        this.mapsView = mapsView;
    }

    public void loadSurveyMaps(String as,String data)
    {
        mapsView.onShow();
        apiClass.getSurveyMaps(as,data).enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                mapsView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    mapsView.onSuccessLoadSurveyMaps(response.body().getLocation());
                    mapsView.onHide();
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                mapsView.onError(t.getMessage());
                mapsView.onHide();
            }
        });
    }
}
