package id.ecodev.wikramabogor.ui.WebsiteScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.WebsiteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebsitePresenter<V extends WebsiteView> extends BasePresenter {

    V websiteView;

    public WebsitePresenter(V websiteView) {
        this.websiteView = websiteView;
    }

    public void deleteWebsite(String id)
    {
        websiteView.onShow();
        apiClass.deleteWebsite(id).enqueue(new Callback<WebsiteResponse>() {
            @Override
            public void onResponse(Call<WebsiteResponse> call, Response<WebsiteResponse> response) {
                websiteView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    websiteView.onSuccessDeleteWebsite(response.body().getStatus());
                    websiteView.onHide();
                }
            }

            @Override
            public void onFailure(Call<WebsiteResponse> call, Throwable t) {
                websiteView.onError(t.getMessage());
                websiteView.onHide();
            }
        });
    }
}
