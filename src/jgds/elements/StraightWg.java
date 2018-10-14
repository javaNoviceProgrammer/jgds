/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.elements;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Rect;
import JGDS2.Ref;
import JGDS2.Struct;

public class StraightWg {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("straight_wg.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct wg = new Struct("wg") ;
            wg.add(new Rect(0, 0, 10, 1, 1));
            Ref ref = new Ref(wg, 0, 0, 0, 0) ;
//            Struct top = new Struct("top") ;
//            top.add(ref);
            lib.add(ref);

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

}
