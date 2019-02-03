package jgds.elements;

import JGDS2.GDS2Element;
import jgds.util.DataBase;

public abstract class AbstractElement extends DataBase {

	protected String name ;
	public abstract GDS2Element getElement() ;

}
