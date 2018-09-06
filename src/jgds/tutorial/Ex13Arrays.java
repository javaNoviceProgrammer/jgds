/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.Array;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Rect;
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
public class Ex13Arrays {
        public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex13Arrays.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("top");
            Struct oneRectangle = new Struct("singleRectangle");
            oneRectangle.add(new Rect(0.0, 0.0, 2.0, 4.0, 44));
            Struct arrayV1 = new Struct("arrayV1");
            arrayV1.add(new Array(oneRectangle, 0.0, 0.0, 4, 5, 
                    (10.0 * 4), (1.0 * 4), (4.0 * 5), (8.0 * 5), 20));
            Struct arrayV2 = new Struct("arrayV2");
            arrayV2.add(new Array(oneRectangle, 0.0, 0.0, 4, 5, 
                    (8.0 * 4), (2.0 * 4), (5.0 * 5), (10.0 * 5), 0));            
            top.add(new Ref(arrayV1, 0, 0));
            top.add(new Ref(arrayV2, 60, 0));
            lib.add(new Ref(top, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }    
}
