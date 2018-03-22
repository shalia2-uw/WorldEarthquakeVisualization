package gui;

import java.util.ArrayList;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthQuakeCity extends PApplet{

	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;
	
	public void setup(){
		size(1200,700,OPENGL);
		map = new UnfoldingMap(this, 130,50,950,650, new Microsoft.AerialProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLoc = new Location(-37.23f,-74.72f);
		Location alaskaLoc = new Location(60.58f,-148.40f);
		Location sumatraLoc = new Location(-1.55f,97.99f);
		Location japanLoc = new Location(38.32f, 142.36f);
		Location russiaLoc = new Location(56.13f,159.51f);
		
		PointFeature valEq = new PointFeature(valLoc);
		valEq.addProperty("Title", "Chile");
		valEq.addProperty("Magnitude", "9.5");
		valEq.addProperty("Date", "May 22, 1960");
		
		PointFeature alaskaEq = new PointFeature(alaskaLoc);
		alaskaEq.addProperty("Title", "Alaska");
		alaskaEq.addProperty("Magnitude", "9.2");
		alaskaEq.addProperty("Date", "March 28, 1964");
		
		PointFeature sumatraEq = new PointFeature(sumatraLoc);
		sumatraEq.addProperty("Title", "Sumatra");
		sumatraEq.addProperty("Magnitude", "9.1");
		sumatraEq.addProperty("Date", "December 26, 2004");
		
		PointFeature japanEq = new PointFeature(japanLoc);
		japanEq.addProperty("Title", "Japan");
		japanEq.addProperty("Magnitude", "9.1");
		japanEq.addProperty("Date", "March 11, 2011");
		
		PointFeature russiaEq = new PointFeature(russiaLoc);
		russiaEq.addProperty("Title", "Russia");
		russiaEq.addProperty("Magnitude", "9.0");
		russiaEq.addProperty("Date", "November 04, 1952");
				
		ArrayList<PointFeature> bigEqs = new ArrayList<>();
		bigEqs.add(valEq);
		bigEqs.add(alaskaEq);
		bigEqs.add(sumatraEq);
		bigEqs.add(japanEq);
		bigEqs.add(russiaEq);
		ArrayList<Marker> list = new ArrayList<>();
		for(PointFeature eq: bigEqs){
			list.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
		}
		map.addMarkers(list);
		
		int yellow = color(255,255,0);
		for(Marker mk: list){
			mk.setColor(yellow);
		}
	}
	private void drawButton(){
		fill(255,255,255);
		rect(100,100,25,25);
		fill(0,0,0);
		rect(100,150,25,25);
	}
	public void mouseReleased(){
		if(mouseX>100&&mouseX<125&&mouseY>100&&mouseY<125){
			background(255);
		}else if(mouseX>100&&mouseX<125&&mouseY>150&&mouseY<175){
			background(0);
		}
	}
	public void draw(){
		
		map.draw();
		drawButton();		
	}

}
