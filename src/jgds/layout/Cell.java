package jgds.layout;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import jgds.elements.AbstractElement;
import jgds.elements.basic.StraightWg;
import jgds.elements.positioning.Port;
import jgds.elements.positioning.Position;
import jgds.pdk.generic.GeneralLayer;
import mathLib.util.CustomJFileChooser;

public class Cell {

	String cellName ;
	AbstractElement[] elements ;

	public Cell(
			String cellName,
			AbstractElement... elements
			) {
		this.cellName = cellName ;
		this.elements = elements ;
	}

	public String getName() {
		return cellName ;
	}

	public void saveGds() {
		String cellPath = CustomJFileChooser.path + File.separator + getName() + ".gds" ;
		File file = new File(cellPath) ;
		try {
			FileOutputStream fos = new FileOutputStream(file) ;
			DataOutputStream dos = new DataOutputStream(fos) ;
			GDSWriter g = new GDSWriter(dos) ;
			Lib lib = new Lib() ;
			Struct topCell = new Struct(cellName) ;
			for(AbstractElement x : elements) {
				topCell.add(x.getElement());
			}
			lib.add(new Ref(topCell, 0, 0));
			lib.GDSOut(g);
			System.out.println(" Saved to " + file.getAbsolutePath());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		AbstractElement elem = new StraightWg("wg1", new GeneralLayer("G", 1, 0), new Port(new Position(0,0),
										0.4, 180), 20) ;
		AbstractElement elem2 = new StraightWg("wg2", new GeneralLayer("G", 1, 0), new Port(new Position(0,0),
				0.4, 90), 20) ;
		Cell cell = new Cell("simple_cell", elem, elem2) ;
		cell.saveGds();
	}

}
