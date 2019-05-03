package id.ecodev.wikramabogor.ui.PrepareSurveyScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.base.BaseView;

public interface PrepareView extends BaseView {
    void onSuccessLoadObject(List<Objek> objeks);
}
