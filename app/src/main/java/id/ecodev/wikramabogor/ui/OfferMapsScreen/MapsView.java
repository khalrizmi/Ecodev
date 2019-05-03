package id.ecodev.wikramabogor.ui.OfferMapsScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.CustomModel;
import id.ecodev.wikramabogor.base.BaseView;

public interface MapsView extends BaseView {

    void onSuccessLoadSurveyMaps(List<CustomModel> customModels);

}
