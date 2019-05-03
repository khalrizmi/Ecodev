package id.ecodev.wikramabogor.ui.AddSurveyScreen;

import java.util.List;

import id.ecodev.wikramabogor.Model.Category;
import id.ecodev.wikramabogor.base.BaseView;

public interface AddSurveyView extends BaseView {
    void onSuccessLoadCategories(List<Category> categories);
    void onSuccessCreateObjek(String result);
}
