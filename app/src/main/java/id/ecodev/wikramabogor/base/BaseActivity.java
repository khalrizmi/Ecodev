package id.ecodev.wikramabogor.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.utils.CommonUtils;


public class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    public RecyclerView recyclerView;


    public void setUpActionBar(String title)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    public void setHideActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void showError(String error)
    {
        Log.e("HTTP ERROR : ",error);
    }

    public void showMessage(String message)
    {
        Log.d("MESSAGE : ",message);
    }

    public void showHttp(String http)
    {
        Log.d("HTTP STATUS : ",http);
    }

    public void showLoading()
    {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Tunggu Sebentar..");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        dismissLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void changeStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void dismissLoading()
    {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
//        progressDialog.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
