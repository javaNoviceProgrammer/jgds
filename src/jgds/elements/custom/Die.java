package jgds.elements.custom;

import JGDS2.Array;
import JGDS2.Struct;

public class Die extends Struct {
	
	int row, column ;
	double dx, dy ;
	
	public Die(String name) {
		super(name) ;
	}
	
	public void setParameters(Struct unitCell, int row, int column, double dx, double dy) {
		add(new Array(unitCell, 0.0, 0.0, column, row, column*dx, row*dy));
	}

}
