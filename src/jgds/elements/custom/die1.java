/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.elements.custom;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.Array;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Rect;
import JGDS2.Ref;
import JGDS2.Struct;

/**
 * Constructing S shaped bend using a Bezier curve with Basic Strokes
 * 
 * @author rob
 */
public class die1 {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("die1.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();
            
            Struct topCell = new Struct("top") ;
            Struct unitCell = new Struct("unitCell") ;
            unitCell.add(new Rect(0.0, 0.0, 4e-3, 20e-3, 2));
            unitCell.add(new Rect(0.0, 44e-3, 4e-3, 44e-3+20e-3, 2));
            unitCell.add(new Rect(36e-3, 0.0, 36e-3+4e-3, 20e-3, 2));
            unitCell.add(new Rect(36e-3, 44e-3, 36e-3+4e-3, 44e-3+20e-3, 2));
            unitCell.add(new Rect(12e-3, 10e-3, 12e-3+4e-3, 10e-3+40e-3, 2));
            unitCell.add(new Rect(24e-3, 10e-3, 24e-3+4e-3, 10e-3+40e-3, 2));

            Struct array = new Struct("array") ;
            array.add(new Array(unitCell, 0.0, 0.0, 6000, 8000, 80e-3*6000, 80e-3*8000));
            
            topCell.add(new Ref(array, 0, 0));
            lib.add(new Ref(topCell, 0, 0));
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
