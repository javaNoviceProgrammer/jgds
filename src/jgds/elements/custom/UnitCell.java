package jgds.elements.custom;

import JGDS2.Rect;
import JGDS2.Struct;
import jgds.util.CellWriter;

public class UnitCell extends Struct {
	
	String name ;
	double w, g1, g2, g3, h1, h2, d ;
	int layer ;
	
	public UnitCell(String name, int layer) {
		super(name) ;
		this.name = name ;
		this.layer = layer ;
	}
	
	public void setParams(double w, double h1, double h2, double g1, double g2, double g3, double d) {
		this.w = w ;
		this.h1 = h1 ;
		this.h2 = h2 ;
		this.g1 = g1 ;
		this.g2 = g2 ;
		this.g3 = g3 ;
		this.d =  d ;
		
		constructCell() ;
	}
	
	private void constructCell() {
		add(new Rect(0.0, 0.0, w, h1, layer));
		add(new Rect(0.0, h1+g1, w, h1+g1+h1, layer));
		add(new Rect(w+g2, d, w+g2+w, d+h2, layer));
		add(new Rect(w+g2+w+g3, d, w+g2+w+g3+w, d+h2, layer));
		add(new Rect(3*w+2*g2+g3, 0.0, 4*w+2*g2+g3, h1, layer));
		add(new Rect(3*w+2*g2+g3, h1+g1, 4*w+2*g2+g3, 2*h1+g1, layer));
	}
	
	// test
	public static void main(String[] args) {
		UnitCell unit = new UnitCell("unit_cell_test", 1) ;
		unit.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
		CellWriter writer = new CellWriter("unit_cell_element") ;
		writer.addStruct(unit);
		writer.writeGDS();
	}
	
	

}
