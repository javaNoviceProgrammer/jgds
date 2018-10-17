package jgds.elements.custom;

import java.awt.geom.Path2D;

import JGDS2.GArea;
import JGDS2.Struct;

public class Polygon extends Struct {
	
	Path2D.Double path ;
	int layer ;

	public Polygon(String name, int layer) {
		super(name);
		this.layer = layer ;
		path = new Path2D.Double() ;
	}
	
	public void setStart(double x0, double y0) {
		path.moveTo(x0, y0);
	}
	
	public void addVertex(double x, double y) {
		path.lineTo(x, y);
	}
	
	public void draw() {
		path.closePath();
		
		createPolygon();
	}
	
	private void createPolygon() {
		GArea area = new GArea(path, layer) ;
		this.add(area);
	}

}
