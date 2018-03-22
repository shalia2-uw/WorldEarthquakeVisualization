package gui;

import processing.core.PApplet;
import processing.core.PImage;

public class MyDisplay extends PApplet{

	private static final long serialVersionUID = 1L;
	
	public void setup(){
		size(400, 400);
		PImage img = loadImage("http://cseweb.ucsd.edu/~minnes/palmTrees.jpg","jpg");
		img.resize(0, height);
		image(img,0,0);
	}
	public void draw(){
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/4, height/5, width/5, height/5);
	}
	public int[] sunColorSec(float second) {
		int[] col = new int[3];
		float diff = Math.abs(60-second);
		float ratio = diff/60;
		col[0] = (int)(255*ratio);
		col[1] = (int)(255*ratio);
		col[2] = 0;
		return col;
	}
}
