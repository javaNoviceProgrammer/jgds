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
 * Diamond created via a 45 degree rotated square This example shows usage of
 * Ref
 *
 * @author rob
 */
public class Ex03Diamond {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex03Diamond.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("top");
            Struct square = new Struct("10umSquare");
            square.add(new Rect(0.0, 0.0, 10.0, 10.0, 4));
            //instancing Structure square at 45 degrees
            top.add(new Ref(square, 0.0, 0.0, 0, 45.0));
            // Ref information:
            // Ref(Struct s, double x, double y)
            // Ref(Struct s, double x, double y, int mirror)
            // Ref(Struct s, double x, double y, int mirror, double angle)
            // Ref(Struct s, double x, double y, int mirror, double mag, double angle)
            //
            lib.add(new Ref(top, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
