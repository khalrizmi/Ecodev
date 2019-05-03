package id.ecodev.wikramabogor.ui.DetailObjekScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.ui.HomeScreen.HomeActivity;
import id.ecodev.wikramabogor.ui.PotensiScreen.PotensiActivity;
import id.ecodev.wikramabogor.ui.PrepareSurveyScreen.PrepareActivity;

public class DetailObjectActivity extends BaseActivity implements DetailObjectView {

    @BindView(R.id.imageObject)
    ImageView imageObject;
    @BindView(R.id.nameObject)
    TextView textObject;
    @BindView(R.id.descriptionObject)
    TextView textDesc;
    @BindView(R.id.dateObject)
    TextView textDate;
    @BindView(R.id.btnDetailVerif)
    Button btnVerif;
    @BindView(R.id.cardVerified)
    CardView cardVerified;
    @BindView(R.id.textVerif)
    TextView textVerif;
    @BindView(R.id.btnDetailDelete)
    Button btnDelete;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    Bundle bundle;
    DetailObjectPresenter presenter;
    @BindView(R.id.noteObject)
    TextView noteObject;
    @BindView(R.id.layoutNote)
    LinearLayout layoutNote;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_object);
        ButterKnife.bind(this);

        presenter = new DetailObjectPresenter(this);

        bundle = getIntent().getExtras();
        super.setUpActionBar(bundle.getString("name"));
        String image = bundle.getString("image");
        Picasso.get().load(image).placeholder(R.drawable.placeholder).into(imageObject, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(DetailObjectActivity.this, "Gagal menampilkan gambar", Toast.LENGTH_LONG).show();
            }
        });
        textObject.setText(bundle.getString("name"));
        textDesc.setText(bundle.getString("description"));
        textDate.setText(bundle.getString("date"));

        if (bundle.getString("verified").equals("0")) {
            cardVerified.setCardBackgroundColor(Color.parseColor("#e04141"));
            textVerif.setText("Belum di Verifikasi");
        }

        if (TextUtils.isEmpty(bundle.getString("note"))) {
            layoutNote.setVisibility(View.GONE);
        } else  {
            noteObject.setText(bundle.getString("note"));
            layoutNote.setVisibility(View.VISIBLE);
        }

        if (bundle.get("classname").equals("MapsSurveyActivity")) {
            btnDelete.setVisibility(View.GONE);
        } else {
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent potensi = new Intent(getApplicationContext(), PotensiActivity.class);
                potensi.putExtra("id", bundle.getString("id"));
                potensi.putExtra("name", bundle.getString("name"));
                potensi.putExtra("verified", bundle.getString("verified"));
                potensi.putExtra("classname", bundle.getString("classname"));
                if (btnDelete.getVisibility() == View.GONE) {
                    potensi.putExtra("website", true);
                }
                startActivity(potensi);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new iOSDialogBuilder(DetailObjectActivity.this)
                        .setTitle("Peringatan!")
                        .setSubtitle("Objek yang di hapus tidak bisa dikembalikan lagi!")
                        .setBoldPositiveLabel(true)
                        .setCancelable(false)
                        .setPositiveListener("Ya", new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                presenter.deleteObject(bundle.getString("id"));
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
            }
        });


    }

    @Override
    public void onSuccessDelete(String status) {
        super.showMessage(status);
        if (status.equals("success")) {
            if (bundle.get("classname").equals("DetailSurveyActivity")) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), PrepareActivity.class));
                finish();
            }
        } else {
            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
        }
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
