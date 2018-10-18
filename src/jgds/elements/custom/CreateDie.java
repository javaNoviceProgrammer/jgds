package jgds.elements.custom;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;

public class CreateDie {

	public static void main(String[] args) {
		/*
		 * put the following code in a loop to automatically generate all the dies with
		 * different unit cells
		 */
		try {
			//**************************************
			// setting the name of the output gds file
			//**************************************
			String name = "die.gds";

			//**************************************
			// setting up the gds writer
			//**************************************
			FileOutputStream fileOUT;
			File f = new File(name);
			fileOUT = new FileOutputStream(f);
			DataOutputStream dO = new DataOutputStream(fileOUT);
			GDSWriter g = new GDSWriter(dO);
			Lib lib = new Lib();

			//**************************************
			// instantiate a unit cell
			//**************************************
			String unitCellName = "unit_cell";
			int layerNumber = 1;

			UnitCell unitCell = new UnitCell(unitCellName, layerNumber);
			unitCell.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
			
			//**************************************
			// create a die and add the unit cell
			//**************************************
			String dieName = "die1";
			int rows = 8000; // number of rows
			int columns = 6000; // number of columns
			double dx = 80e-3; // units in micron
			double dy = 80e-3; // units in micron

			Die die = new Die(dieName);
			die.setParams(unitCell, rows, columns, dx, dy);

			// *******************************
			//
			// ADD OTHER STRUCTURES HERE TO THE DIE...
			//
			// use polygon for drawing complex shapes
			// *******************************
			
			int polyLayer = 2 ;
			
			Polygon p = new Polygon("p1", polyLayer) ;
			p.start(5e-3, 5e-3); // units are in micron
			p.addVertex(5e-3, 20e-3);
			p.addVertex(13e-3, 15e-3);
			p.draw();
			
			die.add(new Ref(p, columns/2 * dx, rows/2 * dy));
			
			//**************************************
			// add the die to the file and save it
			//**************************************
			lib.add(new Ref(die, 0, 0));

			lib.GDSOut(g);
			System.out.println(" Saved to " + f.getAbsolutePath());
		} catch (IOException eOutput) {
			eOutput.printStackTrace();
		}
		System.out.println("done");
	}

}
