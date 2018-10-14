package jgds.layout;

import jgds.elements.AbstractElement;

public class CreateNewCell {

	String cellName ;
	AbstractElement[] elements ;

	public CreateNewCell(
			String cellName,
			AbstractElement[] elements
			) {
		this.cellName = cellName ;
		this.elements = elements ;
	}

}
