package id.ecodev.wikramabogor.ui.OfferMapsScreen;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.Model.CustomModel;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.helper.BottomSheetDialog;

public class MapsSurveyActivity extends BaseActivity implements OnMapReadyCallback, MapsView,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;


    @BindView(R.id.spinnerMaps)
    MaterialSpinner spinner;

    private int ZOOM_LEVEL = 14;
    private int TILT_LEVEL = 0;
    private int BEARING_LEVEL = 0;

    MapsPresenter presenter;
    List<CustomModel> customModels = new ArrayList<>();

    String city, place_name;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    List<Marker> myMarker = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_survey);
        ButterKnife.bind(this);
        super.setUpActionBar("Map Penelusuran");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        spinner.setItems("Seluruh Daerah","Provinsi Saya","Sekitar Saya");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (item.equals("Provinsi Saya")) {
                    presenter.loadSurveyMaps("province",place_name);
                    ZOOM_LEVEL = 13;
                }else if(item.equals("Sekitar Saya")){
                    presenter.loadSurveyMaps("around",city);
                    ZOOM_LEVEL = 14;
                }else{
                    presenter.loadSurveyMaps("all","halo");
                }
            }
        });

        presenter = new MapsPresenter(this);
        presenter.loadSurveyMaps("all","halo");
    }

    private void getMyLocation(){
        try{
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                getAddress(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            }else{
                Toast.makeText(MapsSurveyActivity.this,
                        "Nyalakan GPS",
                        Toast.LENGTH_LONG).show();
            }
        } catch (SecurityException e){
            Toast.makeText(MapsSurveyActivity.this,
                    "SecurityException:\n" + e.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            city = addresses.get(0).getFeatureName();
            place_name = addresses.get(0).getAdminArea();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0;i < myMarker.size(); i++)
                {
                    if (marker.equals(myMarker.get(i)))
                    {
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(customModels.get(i));
                        bottomSheetDialog.show(getSupportFragmentManager(),bottomSheetDialog.getTag());
                    }
                }
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(customModels.get(m));
//                bottomSheetDialog.show(getSupportFragmentManager(),bottomSheetDialog.getTag());
                return true;
            }
        });
    }

    private void initMarker(List<CustomModel> listData) {
        myMarker = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            LatLng location = new LatLng(Double.parseDouble(listData.get(i).getLatitude()), Double.parseDouble(listData.get(i).getLongtitude()));
            MarkerOptions marker = new MarkerOptions().position(location).title(listData.get(i).getPlace_name()).snippet("This is destination");
            Marker markers = mMap.addMarker(marker);
            myMarker.add(markers);
            if (i == 0) {
                LatLng position = new LatLng(Double.parseDouble(listData.get(0).getLatitude()), Double.parseDouble(listData.get(0).getLongtitude()));
                CameraPosition camPos = new CameraPosition(position, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));

            }
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
        mMap.clear();
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
    public void onSuccessLoadSurveyMaps(List<CustomModel> customModels) {
        mMap.clear();
        this.customModels = new ArrayList<>();
        this.customModels = customModels;
        initMarker(customModels);
//        for (int i = 0; i < customModels.size(); i++) {
//            CustomModel customModel = customModels.get(i);
//            createMarker(customModel);
//            if (i == 0) {
//                LatLng position = new LatLng(Double.parseDouble(customModel.getLatitude()), Double.parseDouble(customModel.getLongtitude()));
//                CameraPosition camPos = new CameraPosition(position, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
//                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
//            }
//        }


    }

    private void createMarker(CustomModel customModel)
    {
        LatLng position = new LatLng(Double.parseDouble(customModel.getLatitude()), Double.parseDouble(customModel.getLongtitude()));
        MarkerOptions options = new MarkerOptions().position(position);
        mMap.addMarker(options);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getMyLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,
                "onConnectionSuspended: " + String.valueOf(i),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}
