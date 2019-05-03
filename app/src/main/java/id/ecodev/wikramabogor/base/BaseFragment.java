package id.ecodev.wikramabogor.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import id.ecodev.wikramabogor.utils.CommonUtils;

public class BaseFragment extends Fragment {
    public View view;
    public static String object_data;
    public static String deskripsi_data;
    public static String photo_data;
    public static String category_id;
    public static String category;
    ProgressDialog mProgressDialog;

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

    public void showLoading(Context context)
    {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Tunggu Sebentar..");
//
//
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        dismissLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(context);
    }

    public void dismissLoading()
    {
//        progressDialog.dismiss();
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
