package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public static AddPlace addPlacePayload() {
		AddPlace place = new AddPlace();
    	
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setWebsite("website");
		place.setName("Frontline house");
		place.setPhone_number("(+91) 983 893 3937");
		
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
