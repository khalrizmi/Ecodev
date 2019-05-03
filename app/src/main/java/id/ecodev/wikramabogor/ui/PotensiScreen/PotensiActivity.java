package id.ecodev.wikramabogor.ui.PotensiScreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.helper.PrefManager;

public class PotensiActivity extends BaseActivity implements PotensiView {

    private static final String urlBrowser = "https://www.google.com/search?q=";
    private static final String textBtnVerifikasi = "verifikasi & simpan halaman web";
    private static final String textBtnSaveWeb = "simpan halaman web";

    private PotensiPresenter mPresenter;
    private ProgressDialog mProgressDialog;
    private String urlWeb = "";
    private String urlTitle = "";
    private String potensi = "";
    private String potensiId = "";
    private Bundle mBundle;
    private WebViewClient mWebViewClient;

    @BindView(R.id.webViewResult)
    WebView webView;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    @BindView(R.id.btnAddWeb)
    Button btnAddWeb;
    @BindView(R.id.linearHidden)
    LinearLayout linearHidden;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potensi);
        ButterKnife.bind(this);

        mPresenter = new PotensiPresenter(this);

        initSpinner();
//        initWebView();

        checkButton();



    }

    private void checkButton() {
        boolean isWebsite = getIntent().getBooleanExtra("website", false);
        if (isWebsite)
            btnAddWeb.setText(textBtnSaveWeb);
        else
            btnAddWeb.setText(textBtnVerifikasi);
    }

    @Override
    protected void onStart() {
        mBundle = getIntent().getExtras();
        potensi = mBundle.getString("name");
        potensiId = mBundle.getString("id");
        super.setUpActionBar(mBundle.getString("name"));
        super.onStart();
    }

    @Override
    public void onSuccessVerifiedObjek(String status) {
        Log.d("STATUS VERIF", status);
    }

    @Override
    public void onSuccessAddWebsite(String status) {
        Log.d("STATUS", status);
        if (btnAddWeb.getText().toString().toLowerCase().equals(textBtnSaveWeb.toLowerCase())) {
            Toast.makeText(this, "Website telah ditambahkan!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Potensi telah diverifikasi!", Toast.LENGTH_SHORT).show();
        }
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


    private void initSpinner() {
        spinner.setItems("Keyword", "Pengolahan", "Pemanfaatan", "Pengembangan");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position > 0) {
//                    String urlGoogle = urlBrowser + item.toString() + " " + potensi;
//                    webView.loadUrl(urlGoogle);
//                    urlWeb = "";
                    loadWeb(item.toString());
                }
            }
        });
    }

    private void loadWeb(String keyword) {
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        String urlGoogle = urlBrowser + keyword + " " + potensi;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new AppWebClients(mProgressDialog));
        webView.loadUrl(urlGoogle);
        urlWeb = "";
    }

    private class AppWebClients extends WebViewClient {

        ProgressDialog mProgressDialog;

        public AppWebClients(ProgressDialog mProgressDialog) {
            this.mProgressDialog = mProgressDialog;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            urlTitle = view.getTitle();
            urlWeb = view.getUrl();
//            oldUrl = view.getOriginalUrl();
            PotensiActivity.this.showLoading();
            return true;
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            mProgressDialog.dismiss();
            PotensiActivity.this.dismissLoading();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @JavascriptInterface
        public void onUrlChange(String url) {
            Toast.makeText(getApplicationContext(), "Url redirected",Toast.LENGTH_SHORT).show();
            Toast.makeText(PotensiActivity.this, "onUrlChange", Toast.LENGTH_SHORT).show();
        }
    }

    private void initWebView() {
        mWebViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                urlWeb = view.getUrl();
                urlTitle = view.getTitle();
                PotensiActivity.super.showLoading();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                PotensiActivity.super.dismissLoading();
                super.onPageFinished(view, url);
            }
        };

        webView.setWebViewClient(mWebViewClient);
        webView.setVerticalScrollBarEnabled(false);
        webView.loadUrl(urlBrowser);
    }

    private void verifikasiPotensi(String id, String note) {
        //untuk verifikasi potensi
//        if (mBundle.getString("verified").equals("0")) {
//            mPresenter.verifiedObjek(mBundle.getString("id"));
//        }
        mPresenter.verifiedObjek(id, note);
    }

    private void saveWebsite() {
        // menyimpan halaman website
        PrefManager prefManager = new PrefManager(getApplicationContext());
        mPresenter.addWebsite(urlTitle, urlWeb, String.valueOf(prefManager.getUserId()), potensiId);
    }

    private void alertDialogNote() {
        View view = LayoutInflater.from(this).inflate(R.layout.create_note, null, false);
        Button btnBack = view.findViewById(R.id.btnNoteCancel);
        Button btnSave = view.findViewById(R.id.btnNoteSave);
        EditText etNote = view.findViewById(R.id.etNote);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etNote.getText().toString().trim())) {
                    String id = potensiId;
                    String note = etNote.getText().toString();
                    verifikasiPotensi(id, note);
                    saveWebsite();
                } else {
                    etNote.requestFocus();
                    Toast.makeText(PotensiActivity.this, "Silahkan isi catatan terlebih dahulu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.btnAddWeb)
    public void onBtnAddWebClicked() {
        // jika hanya menyimpan halaman web
        if (btnAddWeb.getText().toString().toLowerCase().equals(textBtnSaveWeb.toLowerCase())) {
            saveWebsite();
        } else {
            //untuk verifikasi
            if (urlWeb.equals(urlBrowser) || TextUtils.isEmpty(urlWeb)) {
                Toast.makeText(this, "Silahkan pilih website", Toast.LENGTH_SHORT).show();
            } else {
                alertDialogNote();
            }
        }
    }
}
