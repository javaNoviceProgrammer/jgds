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
 * Simple example of drawing a 10 micron by 20 micron rectangle then arraying
 * the rectangle into a 4 by 5 array
 *
 * @author rob
 */
public class Ex02RectangleArray {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex02RectangleArray.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // creating a Cell called top
            Struct top = new Struct("top");
            Struct oneRectangle = new Struct("singleRectangle");
            // draw 10um x 20um rectangle with layer 4
            oneRectangle.add(new Rect(0.0, 0.0, 10.0, 20.0, 4));
            // adding a 4 x 5 array of rectangles, starting at (2,2)
            // spaced by 20.0 in x and 25.0um in y
            top.add(new Array(oneRectangle, 2.0, 2.0, 4, 5, (4.0 * 20.0), (5.0 * 25.0)));
            // Description of Array
            // Array (Struct, x, y, Columns, Rows, (Columns * DX), (Rows * DY) )
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