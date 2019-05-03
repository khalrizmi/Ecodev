package id.ecodev.wikramabogor.ui.CredentialScreen;

import id.ecodev.wikramabogor.Model.User;
import id.ecodev.wikramabogor.base.BaseView;

public interface CredentialView extends BaseView {
    void onSuccessLogin(User user);
}
