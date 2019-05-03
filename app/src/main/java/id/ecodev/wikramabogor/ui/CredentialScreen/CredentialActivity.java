package id.ecodev.wikramabogor.ui.CredentialScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.Model.User;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.HomeScreen.HomeActivity;

public class CredentialActivity extends BaseActivity implements CredentialView {

    private static String TAG = CredentialActivity.class.getSimpleName();
    CredentialPresenter presenter;
    GoogleSignInClient mGoogleSignInClient;
    @BindView(R.id.btn_google)
    GoogleSignInButton btnGoogle;
    private static final int RC_SIGN_IN = 1;

    private String imageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creadential);
        ButterKnife.bind(this);
        initGoogle();
        presenter = new CredentialPresenter(this);

    }

    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            imageUser = String.valueOf(account.getPhotoUrl());
            presenter.userLogin(account.getEmail(),account.getDisplayName(),imageUser);
        } catch (ApiException e) {
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            Toast.makeText(getApplicationContext(), "GoogleCancelled :" + e.getMessage() +
//                    " StatusCode : " + e.getStatusCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessLogin(User user) {
        PrefManager prefManager = new PrefManager(this);
        prefManager.setIsLogged(true);
        prefManager.setUserId(user.getId());
        prefManager.setUserEmail(user.getEmail());
        prefManager.setUserName(user.getName());
        prefManager.setUserImage(imageUser);
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
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
