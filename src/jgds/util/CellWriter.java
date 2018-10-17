package jgds.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import jgds.elements.custom.UnitCell;

public class CellWriter {
	
	Struct topCell ;
	
	public CellWriter(String cellName) {
		topCell = new Struct(cellName) ;
	}
	
	public void addStruct(Struct element) {
		topCell.add(new Ref(element, 0.0, 0.0));
	}
	
	public void writeGDS() {
        try {
            FileOutputStream fileOUT;
            File f = new File(topCell.getName()+".gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            lib.add(new Ref(topCell, 0.0, 0.0));
            
            lib.GDSOut(g);
            
            System.out.println(lib.getGrid());
            
            System.out.println("Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
	}
	
	// test
	public static void main(String[] args) {
		CellWriter writer = new CellWriter("test1") ;
        
		UnitCell unit = new UnitCell("unit_cell_test", 1) ;
		unit.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
		writer.addStruct(unit);

		writer.writeGDS();
	}

}
