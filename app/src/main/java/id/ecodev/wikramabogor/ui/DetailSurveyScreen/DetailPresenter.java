package id.ecodev.wikramabogor.ui.DetailSurveyScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.ObjekResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter<V extends DetailView> extends BasePresenter {

    V detailView;

    public DetailPresenter(V detailView) {
        this.detailView = detailView;
    }

    public void loadObjek(String id)
    {
        detailView.onShow();
        apiClass.getSurveyObject(id).enqueue(new Callback<ObjekResponse>() {
            @Override
            public void onResponse(Call<ObjekResponse> call, Response<ObjekResponse> response) {
                detailView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    detailView.onSuccessLoadObjek(response.body().getObjeks());
                    detailView.onHide();
                }
            }

            @Override
            public void onFailure(Call<ObjekResponse> call, Throwable t) {
                detailView.onError(t.getMessage());
                detailView.onHide();
            }
        });
    }
}
