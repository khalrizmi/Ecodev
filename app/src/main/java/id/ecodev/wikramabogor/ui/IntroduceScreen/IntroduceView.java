package id.ecodev.wikramabogor.ui.IntroduceScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.Slide;
import id.ecodev.wikramabogor.base.BaseView;

public interface IntroduceView extends BaseView {
    void onSuccessLoad(List<Slide> slides);
}
