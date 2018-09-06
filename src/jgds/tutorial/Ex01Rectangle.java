/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

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
 * Simple example of drawing a 10 micron by 20 micron rectangle
 *
 * @author rob
 */
public class Ex01Rectangle {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex01Rectangle.gds");     // gds file name
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // creating a Cell called top
            Struct top = new Struct("top");
            // draw 10um  by 20um rectangle with layer 4
            // from lower left corner (0,0)um to upper right corner (10, 20)um
            top.add(new Rect(0.0, 0.0, 10.0, 20.0, 4));
            // Description of Rect
            // Rect(xLL, yLL, xUR, yUR, GDS Layer)
            // where LL = lower left and UR = upper right
            // xLL, yLL, xUR, yUR are doubles, GDS Layer is an int
            //
            // add cell top to the library
            lib.add(new Ref(top, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
