package id.ecodev.wikramabogor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import id.ecodev.wikramabogor.R;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View windowView;
    private Context context;
    public static String name = null;
    public static String email = null;
    int position;


    public void rendowWindowText(Marker marker,View view){
        TextView infoName  = view.findViewById(R.id.infoName);
        TextView infoEmail = view.findViewById(R.id.emailInfo);

        infoName.setText(name);
        infoEmail.setText(email);

    }

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
        windowView = LayoutInflater.from(context).inflate(R.layout.custom_marker_info,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker,windowView);
        return windowView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return windowView;
    }
}
