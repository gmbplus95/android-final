package com.nguyentien.hai.ecomerce.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nguyentien.hai.ecomerce.R;
import com.nguyentien.hai.ecomerce.adapter.ImageLoadTask;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.nguyentien.hai.ecomerce.R.id.img_nuceshop;
import static com.nguyentien.hai.ecomerce.R.id.map;

public class CustomMapActivity extends FragmentActivity implements OnMapReadyCallback {
    ProgressDialog myProgress;
    private GoogleMap mMap;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_map);
        myProgress = new ProgressDialog(this);
        myProgress.setTitle("Đang tải Map ...");
        myProgress.setMessage("Vui lòng chờ...");
        myProgress.setCancelable(true);
//Hiển thị Progress Bar
        myProgress.show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        onMapLoaded();
    }

    public void onMapLoaded() {
//Đã tải thành công thì tắt Dialog Progress đi
        myProgress.dismiss();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nuceShop = new LatLng(21.004256, 105.842054);
        MarkerOptions option=new MarkerOptions();
        option.title("NUCE SHOP");
        option.position(nuceShop);
        Marker currentMarker= mMap.addMarker(option);
        currentMarker.showInfoWindow();
        ImageLoadTask imgTask=new ImageLoadTask(this,"http://www.magmanz.com/wp-content/uploads/2015/05/Samsung-Mobile-Shop-_-Central-Latprao-thumb.jpg",mMap,currentMarker);
        imgTask.execute();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nuceShop));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nuceShop,18));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

}