package com.hackover.itroleu_android_client;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends MapActivity implements LocationListener {
	
	private EditText			txted			= null;
	
	private Button				btnSimple		= null;
	
	private Drawable			defaultMarker	= null;
	
	private GeoPoint			point			= null;

	private MapView 			mapView   		= null;
	
//	private MyLocationOverlay 	me				= null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapview);
        
        mapView.setBuiltInZoomControls(true);
//        mapView.setSatellite(true);
        mapView.getController().setCenter(new GeoPoint(44432636, 26103837));
        mapView.getController().setZoom(17);
        
    	// Add a location mark
//		MyLocationOverlay myLocationOverlay = new MyLocationOverlay();
//		List<Overlay> list = mapView.getOverlays();
//		list.add(myLocationOverlay);
        
//        /* Use the LocationManager class to obtain GPS locations */
//
//        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        LocationListener mlocListener = new MyLocationListener();
//        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        
        // Getting locationManager and reflecting changes over map if distance travel by
 		// user is greater than 500m from current location.
 		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 100.0f, this);
 		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 100.0f, this);
 		
// 		mapView.invalidate();
    }
    
//    @Override
//    public void onResume() {
//      super.onResume();
//      
//      me.enableCompass();
//    }
//    
//    @Override
//    public void onPause() {
//      super.onPause();
//      
//      me.disableCompass();
//    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* This method is called when use position will get changed */
	public void onLocationChanged(Location location) {
		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
//			String currentLocation = "Lat: " + lat + " Lng: " + lng;
//			txted.setText(currentLocation);
			point = new GeoPoint((int)(lat * 1000000), (int)(lng * 1000000));
			mapView.getController().animateTo(point);
			List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
	        MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable, this);
	        
	        OverlayItem overlayitem = new OverlayItem(point, "Your Location", "");
	        itemizedoverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedoverlay);
		}
	}
	
	public void onProviderDisabled(String provider) {
		// required for interface, not used
	}
	
	public void onProviderEnabled(String provider) {
		// required for interface, not used
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// required for interface, not used
	}
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}