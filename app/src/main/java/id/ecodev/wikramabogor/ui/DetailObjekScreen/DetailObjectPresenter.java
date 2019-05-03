package id.ecodev.wikramabogor.ui.DetailObjekScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.ObjekResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailObjectPresenter<V extends DetailObjectView> extends BasePresenter {

    V detailView;

    public DetailObjectPresenter(V detailView) {
        this.detailView = detailView;
    }

    public void deleteObject(String id)
    {
        detailView.onShow();
        apiClass.deleteObject(id).enqueue(new Callback<ObjekResponse>() {
            @Override
            public void onResponse(Call<ObjekResponse> call, Response<ObjekResponse> response) {
                detailView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    detailView.onSuccessDelete(response.body().getStatus());
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
