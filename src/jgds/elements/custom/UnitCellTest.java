/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.elements.custom;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;

public class UnitCellTest {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("unitCellTest.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();
            
            Struct topCell = new Struct("top") ;

            UnitCell unitCell = new UnitCell("unit_cell", 1) ;
            unitCell.setParams(4e-3, 20e-3, 40e-3, 20e-3, 8e-3, 8e-3, 10e-3);
            
            topCell.add(new Ref(unitCell, 0.0, 0.0));

            lib.add(new Ref(topCell, 0.0, 0.0));
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
