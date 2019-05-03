package id.ecodev.wikramabogor.ui.IntroduceScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.SlideResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroducePresenter<IntroView extends IntroduceView> extends BasePresenter {

    IntroView introView;

    public IntroducePresenter(IntroView introView) {
        this.introView = introView;
    }

    public void loadSlider()
    {
        introView.onShow();
        apiClass.getSliders().enqueue(new Callback<SlideResponse>() {
            @Override
            public void onResponse(Call<SlideResponse> call, Response<SlideResponse> response) {
                introView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    introView.onSuccessLoad(response.body().getSlides());
                    introView.onHide();
                }
            }

            @Override
            public void onFailure(Call<SlideResponse> call, Throwable t) {
                introView.onError(t.getMessage());
                introView.onHide();
            }
        });
    }
}
