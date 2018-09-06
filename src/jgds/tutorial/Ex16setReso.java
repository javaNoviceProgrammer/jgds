/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.GDS2;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rob
 */
public class Ex16setReso {
        public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex16setReso.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("top");
            top.add(new Ref(Ex12YetAnotherTree.tree(.02, 5, 14, 10), 6, 16, 0, 4, 0));
            GDS2.setReso(1e-6); //1e-6 microns, i.e. femtometer
            lib.add(new Ref(top, 0, 0));
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }    
}
