package id.ecodev.wikramabogor.helper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.R;

@SuppressLint("ValidFragment")
public class BottomSheetWeb extends BottomSheetDialogFragment {

    String webUrl;

    public BottomSheetWeb(String webUrl) {
        this.webUrl = webUrl;
    }

    @BindView(R.id.bottom_web)
    WebView webView;
    @BindView(R.id.progressweb)
    ProgressBar progressBar;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.layout_bottom_web,null);
        ButterKnife.bind(this,contentView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new AppWebViewClients(progressBar));
        webView.loadUrl(webUrl);

        dialog.setContentView(contentView);
    }

    public class AppWebViewClients extends WebViewClient {


        public AppWebViewClients(ProgressBar progressBar) {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @JavascriptInterface
        public void onUrlChange(String url) {

        }
    }
}
