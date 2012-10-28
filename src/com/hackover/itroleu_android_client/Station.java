package com.hackover.itroleu_android_client;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Station {

	public final int id;
	public final float lat;
	public final float lon;
	public final String name;
	
	private Station(int id, float lat, float lon, String name)
	{
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}
	private Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "station");
	    int id = null;
	    float lat = null;
	    float lon = null;
	    String name = null;
	    
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("title")) {
	            title = readTitle(parser);
	        } else if (name.equals("summary")) {
	            summary = readSummary(parser);
	        } else if (name.equals("link")) {
	            link = readLink(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    return new Entry(title, summary, link);
	}
}
 