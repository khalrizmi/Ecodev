package id.ecodev.wikramabogor.ui.OfferScreen;

import id.ecodev.wikramabogor.base.BasePresenter;

public class OfferPresenter<V extends OfferView> extends BasePresenter {

    V offerview;

    public OfferPresenter(V offerview) {
        this.offerview = offerview;
    }



}
