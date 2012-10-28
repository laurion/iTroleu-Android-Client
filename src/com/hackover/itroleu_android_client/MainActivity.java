package com.hackover.itroleu_android_client;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class MainActivity extends MapActivity implements LocationListener {

	private EditText txted = null;

	private Button searchButton = null;

	private Drawable defaultMarker = null;

	private GeoPoint point = null;

	private MapView mapView = null;

	private MyItemizedOverlay itemizedoverlay;

	private List<Overlay> mapOverlays;

	private LinearLayout layout;

	// private MyLocationOverlay me = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapView = (MapView) findViewById(R.id.mapview);

		mapView.setBuiltInZoomControls(true);
		// mapView.setSatellite(true);
		mapView.getController().setCenter(new GeoPoint(44432636, 26103837));
		mapView.getController().setZoom(17);

		// overlays
		mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.start_marker);
		itemizedoverlay = new MyItemizedOverlay(drawable, this);

		// Getting locationManager and reflecting changes over map if distance
		// travel by
		// user is greater than 500m from current location.
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 100.0f,
				this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L,
				100.0f, this);

		// mapView.invalidate();
	}

	/** Called when the user touches the button */
	public void searchAddress(View v) {
		// Do something in response to button click
		onSearchRequested();
	}


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
			point = new GeoPoint((int) (lat * 1000000), (int) (lng * 1000000));
			mapView.getController().animateTo(point);

			OverlayItem overlayitem = new OverlayItem(point, "Your Location",
					null);
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
			// mapView.invalidate();
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