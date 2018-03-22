package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpentancy extends PApplet{
	
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	List<Feature> countries = new ArrayList<>();
	List<Marker> countryMarkers = new ArrayList<>();
	
	private Map<String,Float> readFromCsv(String filename) throws Exception{
		lifeExpByCountry = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line=""; br.readLine();
		while((line = br.readLine())!=null){
			String[] col = line.split(",");
			String val1 = col[1];
			float val2 = Float.parseFloat(col[6]);
			lifeExpByCountry.put(val1, val2);
		}
		br.close();
		return lifeExpByCountry;
	}
	
	private void shadeCountries(){
		for(Marker mk: countryMarkers){
			String countryId = mk.getId();
			if(lifeExpByCountry.containsKey(countryId)){
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp,40,90,10,255);
				mk.setColor(color(255-colorLevel,100,colorLevel));
			}else{
				mk.setColor(color(150,150,150));
			}
		}
	}
	public void setup(){
		size(950,650,OPENGL);
		map = new UnfoldingMap(this, 25, 25, 900, 600, new Google.GoogleTerrainProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		try {
			lifeExpByCountry = readFromCsv("C:\\Users\\Shalini\\Desktop\\Notes\\CourseEra\\Life_Expectancy\\Life_expectancy.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		countries = GeoJSONReader.loadData(this, "C:/Users/Shalini/Desktop/Notes/CourseEra/UCSDUnfoldingMaps/data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	public void draw(){
		map.draw();
	}
	public static void main(String[] args) {
		
	}
}
