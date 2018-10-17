package jgds.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import jgds.elements.custom.UnitCell;

public class CellWriter {
	
	ArrayList<Struct> structList ;
	String name ;
	
	public CellWriter(String name) {
		this.name = name ;
		structList = new ArrayList<>() ;
	}
	
	public void addStruct(Struct element) {
		structList.add(element) ;
	}
	
	public void writeGDS() {
        try {
            FileOutputStream fileOUT;
            File f = new File(name+".gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);

            Lib lib = new Lib();

            Struct topCell = new Struct(name) ;
            
    		UnitCell unit = new UnitCell("unit", 1) ;
    		unit.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
    		
    		topCell.add(new Ref(unit, 0, 0));
            
            for(Struct s : structList) {
            	topCell.add(new Ref(s, 0.0, 0.0));
            }
            
            lib.add(new Ref(topCell, 0.0, 0.0));

            lib.GDSOut(g);
            
            System.out.println("Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
	}
	
	// test
	public static void main(String[] args) {
		CellWriter writer = new CellWriter("top_cell") ;
        
		UnitCell unit = new UnitCell("unit", 1) ;
		unit.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);

		writer.addStruct(unit);

		writer.writeGDS();
	}

}
