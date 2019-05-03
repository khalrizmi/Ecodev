package id.ecodev.wikramabogor.ui.DetailSurveyScreen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.adapter.ObjekAdapter;
import id.ecodev.wikramabogor.base.BaseActivity;

public class DetailSurveyActivity extends BaseActivity implements DetailView,OnMapReadyCallback {

    @BindView(R.id.citySurvey)
    TextView citySurvey;
    @BindView(R.id.addressSurvey)
    TextView addressSurvey;
    @BindView(R.id.objectCount)
    TextView objectCount;
    @BindView(R.id.dateSurvey)
    TextView dateObject;
    @BindView(R.id.recyclerDetail)
    RecyclerView recyclerView;
    @BindView(R.id.detailCountObjek)
    TextView textCount;

    ObjekAdapter objekAdapter;
    GoogleMap mMap;
    Bundle bundle;

    DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_survey);
        ButterKnife.bind(this);
        super.setUpActionBar("Detail Penelusuran");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        bundle = getIntent().getExtras();

        presenter = new DetailPresenter(this);
        presenter.loadObjek(bundle.getString("id"));


        citySurvey.setText(bundle.getString("city"));
        addressSurvey.setText(bundle.getString("address"));
        objectCount.setText("Jumlah Objek : "+bundle.getString("object_count"));
        dateObject.setText(bundle.getString("date"));

        objekAdapter = new ObjekAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(objekAdapter);



        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng tempat = new LatLng(Double.parseDouble(bundle.getString("lat")), Double.parseDouble(bundle.getString("long")));
        mMap.addMarker(new MarkerOptions().position(tempat).title("Tempat Survey"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tempat));
        CameraPosition bogor = new CameraPosition(tempat,
                17f,
                mMap.getCameraPosition().tilt,
                mMap.getCameraPosition().bearing);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(bogor), 4000, null);
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

    @Override
    public void onSuccessLoadObjek(List<Objek> objeks) {
        objekAdapter.replace_data(objeks);
        textCount.setText(String.valueOf(objekAdapter.getItemCount()));
    }
}
