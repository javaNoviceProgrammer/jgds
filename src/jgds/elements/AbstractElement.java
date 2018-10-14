package jgds.elements;

import JGDS2.Struct;

public abstract class AbstractElement {

	String name ;

	public abstract Struct getStructure() ;
	public abstract void rotate(double x, double y, double angleDegree) ;
}
