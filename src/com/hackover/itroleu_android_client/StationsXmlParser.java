package com.hackover.itroleu_android_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;


public class StationsXmlParser {

	private static final String ns = null;
	   
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, "/home/alex/Desktop/stations.xml");
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }
    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List stations = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "stations");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("station")) {
                stations.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }  
        return stations;
    }
	private void skip(XmlPullParser parser) {
		// TODO Auto-generated method stub
		
	}
	private Object readEntry(XmlPullParser parser) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
