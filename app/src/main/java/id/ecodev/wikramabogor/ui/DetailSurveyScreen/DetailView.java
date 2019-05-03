package id.ecodev.wikramabogor.ui.DetailSurveyScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.base.BaseView;

public interface DetailView extends BaseView {

    void onSuccessLoadObjek(List<Objek> objeks);

}
