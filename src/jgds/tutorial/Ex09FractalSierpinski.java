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
import java.awt.geom.Path2D;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class Ex09FractalSierpinski {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex09FractalSierpinski.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            //number of Sierpinski levels
            int iterations = 10;
            //creating a triangle structure
            Path2D.Double poly = new Path2D.Double();
            poly.moveTo(0, 0);
            poly.lineTo(1, 2);
            poly.lineTo(2, 0);
            poly.lineTo(0, 0);
            poly.closePath();
            GArea va = new GArea(poly, 2);

            Struct top = new Struct("top");
            ArrayList<Struct> v = new ArrayList();
            v.add(new Struct("v0", va));
            Struct temp;
            Ref r;
            int i;
            for (i = 1; i < iterations; i++) {
                temp = new Struct("v" + i);
                r = new Ref(v.get(i - 1), 0, 0);
                r.setMag(.5);
                temp.add(r);
                r = new Ref(v.get(i - 1), .5, 1);
                r.setMag(.5);
                temp.add(r);
                r = new Ref(v.get(i - 1), 1, 0);
                r.setMag(.5);
                temp.add(r);
                v.add(temp);
            }
            top.add(new Ref(v.get(i - 1), 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
