package com.hackover.itroleu_android_client;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

public class SearchActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.xml.searchable);
	    handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
	    setIntent(intent);
	    handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
//	      doMySearch(query);
	    }
	}
//	public void doMySearch(String query) {
//    	Geocoder geo = new Geocoder(getApplicationContext(),
//                Locale.getDefault());
//        try {
//            List<Address> addresses = geo.getFromLocationName(query, 5);
//            if (addresses.size() > 0) {
//                GeoPoint point = new GeoPoint((int) (addresses.get(0)
//                        .getLatitude() * 1E6), (int) (addresses.get(0)
//                        .getLongitude() * 1E6));
//
//                mapView.getController().animateTo(point);
//                mapView.getController().setZoom(17);
//                OverlayItem overlayitem = new OverlayItem(point, "Your Location", null);
//    	        itemizedoverlay.addOverlay(overlayitem);
//    	        mapOverlays.add(itemizedoverlay);
//
//            } else {
//                AlertDialog.Builder adb = new AlertDialog.Builder(
//                        this);
//                adb.setTitle("Wrong address");
//                adb.setMessage("Address not found.");
//                adb.setPositiveButton("Close", null);
//                adb.show();
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//	@Override
//	public boolean onSearchRequested() {
//	     Bundle appData = new Bundle();
//	     appData.putBoolean(MainActivity.JARGON, true);
//	     startSearch(null, false, appData, false);
//	     return true;
//	 }
	
}

