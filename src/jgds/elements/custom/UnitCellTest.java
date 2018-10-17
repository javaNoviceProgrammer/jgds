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
import JGDS2.Rect;
import JGDS2.Struct;

public class UnitCellTest {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("unitCell.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();
            
            Struct topCell = new Struct("top") ;
            topCell.add(new Rect(0.0, 0.0, 4e-3, 20e-3, 2));
            topCell.add(new Rect(0.0, 44e-3, 4e-3, 44e-3+20e-3, 2));
            topCell.add(new Rect(36e-3, 0.0, 36e-3+4e-3, 20e-3, 2));
            topCell.add(new Rect(36e-3, 44e-3, 36e-3+4e-3, 44e-3+20e-3, 2));
            topCell.add(new Rect(12e-3, 10e-3, 12e-3+4e-3, 10e-3+40e-3, 2));
            topCell.add(new Rect(24e-3, 10e-3, 24e-3+4e-3, 10e-3+40e-3, 2));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
