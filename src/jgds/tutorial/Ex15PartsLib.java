/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.GArea;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import JGDS2.Constants;
import PartLib.PartsLib;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rob
 */
public class Ex15PartsLib implements Constants{
        public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex15PartsLib.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("topp");            
            GArea cir = PartsLib.circle(5.0, 64, 44);
            GArea tor = PartsLib.torus(8.0, 10.0, 64, 44);
            Struct vrn = PartsLib.Vern(4, 44, 0.050, 20, "L4", "L44");
            Struct frm = PartsLib.frame(ASML, "BarCode", "Label", "Serif");
            top.add(cir);
            top.add(tor);
            top.add(new Ref(vrn, 100,0));
            top.add(new Ref(frm, 0,0));
            lib.add(new Ref(top, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }    
}
