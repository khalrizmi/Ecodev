package id.ecodev.wikramabogor.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import java.util.ArrayList;
import java.util.List;

import id.ecodev.wikramabogor.Model.Survey;
import id.ecodev.wikramabogor.Model.Website;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyPresenter;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyView;
import id.ecodev.wikramabogor.ui.WebsiteScreen.WebsitePresenter;
import id.ecodev.wikramabogor.ui.WebsiteScreen.WebsiteView;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.Holder> implements WebsiteView,SurveyView {


    List<Website> websites = new ArrayList<>();
    Context context;
    View view;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    WebsitePresenter websitePresenter = new WebsitePresenter(this);
    SurveyPresenter surveyPresenter = new SurveyPresenter(this);
    Dialog dialog;
    public WebsiteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccessDeleteWebsite(String status) {
        PrefManager prefManager = new PrefManager(context);
        surveyPresenter.loadWebsite(prefManager.getUserId());
    }

    @Override
    public void onShow() {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Tunggu Sebentar..");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
    }

    @Override
    public void onHide() {
//        progressDialog.dismiss();
    }

    @Override
    public void onError(String error) {
        Log.d("MESSAGE_ADAPTER",error);
    }

    @Override
    public void getMessage(String message) {
        Log.d("MESSAGE_ADAPTER",message);
    }

    @Override
    public void getHttp(String http) {
        Log.d("MESSAGE_ADAPTER",http);
    }

    @Override
    public void onSuccessLoadSurvey(List<Survey> surveys) {

    }

    @Override
    public void onSucessLoadWebsite(List<Website> websites) {
        replace_data(websites);
//        progressDialog.dismiss();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvUrl,tvDate;
        CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvTitle  = itemView.findViewById(R.id.pageWebsite);
            tvUrl    = itemView.findViewById(R.id.urlWebsite);
            tvDate   = itemView.findViewById(R.id.dateWebsite);
            cardView = itemView.findViewById(R.id.cardObject);
        }
    }

    @NonNull
    @Override
    public WebsiteAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_web,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteAdapter.Holder holder, int position) {
        holder.tvTitle.setText(websites.get(position).getTitle());
        holder.tvUrl.setText(websites.get(position).getUrl());
        holder.tvDate.setText(websites.get(position).getCreated_at());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View views = LayoutInflater.from(context).inflate(R.layout.layout_bottom_web,null);

                WebView webView = views.findViewById(R.id.bottom_web);
                progressBar     = views.findViewById(R.id.progressweb);
                ImageView imageView = views.findViewById(R.id.imageClose);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new AppWebViewClients(progressBar));
                webView.loadUrl(websites.get(position).getUrl());

                dialog = new Dialog(context,R.style.AppFullScreenTheme);
                dialog.setContentView(views);
                dialog.show();

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new iOSDialogBuilder(context)
                        .setTitle("Peringatan!")
                        .setSubtitle("Website Di List ini akan di hapus permanen!")
                        .setBoldPositiveLabel(true)
                        .setCancelable(false)
                        .setPositiveListener("Ya",new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                websitePresenter.deleteWebsite(websites.get(position).getId());
                                dialog.dismiss();

                            }
                        })
                        .setNegativeListener("Tidak", new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .build().show();
                return true;
            }
        });
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

    @Override
    public int getItemCount() {
        return websites.size();
    }

    public void replace_data(List<Website> websites)
    {
        this.websites = websites;
        notifyDataSetChanged();
    }
}
