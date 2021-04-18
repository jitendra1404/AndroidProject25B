package com.example.androidproject25b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Transformations.map
import com.bumptech.glide.request.transition.BitmapTransitionFactory
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        mMap.addMarker(
            MarkerOptions().position(LatLng(27.0449005, 84.8672171))
                .title("Laptop Marmat Sewa Center")
                .icon(BitmapDescriptorFactory.defaultMarker
                    (BitmapDescriptorFactory.HUE_BLUE)
                )
        )
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom
                (LatLng(27.0449005, 84.8672171),15F),4000,null
        )
        mMap.uiSettings.isZoomControlsEnabled=true
    }
}