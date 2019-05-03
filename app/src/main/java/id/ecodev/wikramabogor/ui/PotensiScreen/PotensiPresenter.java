package id.ecodev.wikramabogor.ui.PotensiScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.ObjekResponse;
import id.ecodev.wikramabogor.response.WebsiteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PotensiPresenter<V extends  PotensiView> extends BasePresenter {

    V potensiView;

    public PotensiPresenter(V potensiView) {
        this.potensiView = potensiView;
    }

    public void verifiedObjek(String id, String note)
    {
        potensiView.onShow();
        apiClass.verifObjek(id, note).enqueue(new Callback<ObjekResponse>() {
            @Override
            public void onResponse(Call<ObjekResponse> call, Response<ObjekResponse> response) {
                potensiView.getHttp(Integer.toString(response.code()));

                if (response.isSuccessful())
                {
                    potensiView.onSuccessVerifiedObjek(response.body().getStatus());
                    potensiView.onHide();
                }

            }

            @Override
            public void onFailure(Call<ObjekResponse> call, Throwable t) {
                potensiView.onError(t.getMessage());
                potensiView.onHide();
            }
        });
    }

    public void addWebsite(String title,String url,String member_id, String objek_id)
    {
        potensiView.onShow();
        apiClass.addWebsite(title,url,member_id,objek_id).enqueue(new Callback<WebsiteResponse>() {
            @Override
            public void onResponse(Call<WebsiteResponse> call, Response<WebsiteResponse> response) {
                potensiView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    potensiView.onSuccessAddWebsite(response.body().getStatus());
                    potensiView.onHide();
                }
            }

            @Override
            public void onFailure(Call<WebsiteResponse> call, Throwable t) {
                potensiView.onError(t.getMessage());
                potensiView.onHide();
            }
        });
    }
}
