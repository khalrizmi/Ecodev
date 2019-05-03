package id.ecodev.wikramabogor.ui.PrepareSurveyScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.ObjekResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreparePresenter<V extends PrepareView> extends BasePresenter {

    V prepareView;

    public PreparePresenter(V prepareView) {
        this.prepareView = prepareView;
    }

    public void loadObjek(String id)
    {
        prepareView.onShow();
        apiClass.getObjek(id).enqueue(new Callback<ObjekResponse>() {
            @Override
            public void onResponse(Call<ObjekResponse> call, Response<ObjekResponse> response) {

                prepareView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    prepareView.onSuccessLoadObject(response.body().getObjeks());
                    prepareView.onHide();
                }
            }

            @Override
            public void onFailure(Call<ObjekResponse> call, Throwable t) {
                prepareView.onError(t.getMessage());
                prepareView.onHide();
            }
        });
    }
}
