package id.ecodev.wikramabogor.ui.OfferScreen;

import android.os.Bundle;

import java.util.List;

import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;

public class OfferActivity extends BaseActivity implements OfferView {

    OfferPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        presenter = new OfferPresenter(this);

    }

    @Override
    public void onSuccessLoadOffer(List<Object> objects) {

    }

    @Override
    public void onShow() {
        super.showLoading();
    }

    @Override
    public void onHide() {
        super.dismissLoading();
    }

    @Override
    public void onError(String error) {
        super.showError(error);
    }

    @Override
    public void getMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void getHttp(String http) {
        super.showHttp(http);
    }
}
