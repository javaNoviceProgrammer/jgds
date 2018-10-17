package jgds.elements.custom.tests;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import jgds.elements.custom.Die;
import jgds.elements.custom.UnitCell;

public class die1 {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("die1.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();
            
            UnitCell unitCell = new UnitCell("unit_cell", 1) ;
            unitCell.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
            
            Die die = new Die("die1") ;
            die.setParams(unitCell, 8000, 6000, 80e-3, 80e-3);
              
            lib.add(new Ref(die, 0, 0));
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
