package jgds.elements;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;

public class CellWriter {
	
	Struct topCell ;
	
	public CellWriter(String cellName) {
		topCell = new Struct(cellName) ;
	}
	
	public void add(Struct element) {
		topCell.add(new Ref(element, 0.0, 0.0));
	}
	
	public void writeGDS() {
		File f = new File(topCell.getName()+".gds");
		System.out.println(f.getAbsolutePath());
		FileOutputStream fo = null ;
		try {
			fo = new FileOutputStream(f);
			DataOutputStream dO = new DataOutputStream(fo) ;
			GDSWriter g = new GDSWriter(dO) ;
			Lib lib = new Lib() ;
			lib.add(new Ref(topCell, 0.0, 0.0));
			try {
				lib.GDSOut(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// test
	public static void main(String[] args) {
		
	}

}
