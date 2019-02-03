package jgds.elements;

import org.jfree.chart.annotations.XYAnnotation;

import JGDS2.GDS2Element;

public abstract class AbstractElement {

	protected String name ;
	public abstract GDS2Element getElement() ;
	public abstract XYAnnotation getAnnotation() ;
}
