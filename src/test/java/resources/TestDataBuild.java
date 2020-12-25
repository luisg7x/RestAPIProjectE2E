package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public static AddPlace addPlacePayload(String name, String languaje, String address) {
		AddPlace place = new AddPlace();
    	
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(languaje);
		place.setWebsite("website");
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("www.peter.com");
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc); //expected location class, so create location class
		
		List<String> types = new ArrayList<String>();
		types.add("shop");
		types.add("school");
		place.setTypes(types); //expected a list
		
		return place;
	}
	
}
