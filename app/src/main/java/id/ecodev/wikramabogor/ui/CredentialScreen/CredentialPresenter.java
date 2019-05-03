package id.ecodev.wikramabogor.ui.CredentialScreen;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CredentialPresenter<credentialView extends CredentialView> extends BasePresenter {
    credentialView credentialView;

    public CredentialPresenter(credentialView credentialView) {
        this.credentialView = credentialView;
    }

    public void userLogin(String email,String name,String photo)
    {
        credentialView.onShow();
        apiClass.login(email,name,photo).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                credentialView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    credentialView.onSuccessLogin(response.body().getUser());
                    credentialView.onHide();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                credentialView.onError(t.getMessage());
                credentialView.onHide();
            }
        });
    }
}
