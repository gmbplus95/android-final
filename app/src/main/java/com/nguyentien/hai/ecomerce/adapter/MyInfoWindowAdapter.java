package com.nguyentien.hai.ecomerce.adapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nguyentien.hai.ecomerce.R;

/**
 * Created by laptop88 on 31-May-17.
 */

public class MyInfoWindowAdapter implements InfoWindowAdapter{
    private Activity context;
    private Bitmap btmp;
    public MyInfoWindowAdapter(Activity context,Bitmap result)
    {
        this.context=context;
        this.btmp=result;
    }
    @Override
    public View getInfoContents(Marker arg0) {
// Getting view from the layout file info_window_layout
        View v = this.context.getLayoutInflater().inflate(R.layout.custom_info, null);

// Getting the position from the marker
        LatLng latLng = arg0.getPosition();

// Getting reference to the TextView to set latitude
        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
// Getting reference to the TextView to set longitude
        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
        ImageView img_nuceshop=(ImageView) v.findViewById(R.id.img_nuceshop);
// Setting the latitude
        tvLat.setText("Vĩ độ: " + latLng.latitude);
// Setting the longitude
        tvLng.setText("Kinh độ: "+ latLng.longitude);
        tvTitle.setText("Cửa hàng: "+ arg0.getTitle());
        img_nuceshop.setImageBitmap(btmp);
        return v;
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

}
