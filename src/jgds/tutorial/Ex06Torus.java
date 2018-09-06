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

/**
 * Drawing a torus using Path2D.Double via 2 for loops to create
 * the inner and outer radius shapes
 * 
 * @author rob
 */
public class Ex06Torus {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex06Torus.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // draw a complete Torus
            double radiusInner = 5.0, radiusOuter = 8.0, sweepAngle = 360.0;
            int numberOfSides = 64, gdsLayer = 4;
            Struct torus = Torus(0, 0, radiusInner, radiusOuter, sweepAngle, numberOfSides, gdsLayer);
            lib.add(new Ref(torus, 0, 0));
            // draw a Torus swept through 110 degrees
            sweepAngle = 110.0;
            Struct torus2 = Torus(0, 0, radiusInner, radiusOuter, sweepAngle, numberOfSides, gdsLayer);
            lib.add(new Ref(torus2, 0, 0));
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    static Struct Torus(double x, double y, double radInner, double radOuter,
            double angle, int NumSides, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(radInner, 0);
        int steps = NumSides;
        double c = (angle * (2 * Math.PI / 360.00)) / NumSides;
        for (int i = 0; i <= steps; i++) {
            poly.lineTo(radInner * Math.cos(i * c), radInner * Math.sin(i * c));
        }
        for (int j = steps; j >= 0; j--) {  //NOTE: Going Backwards to avoid figure 8's
            poly.lineTo(radOuter * Math.cos(j * c), radOuter * Math.sin(j * c));
        }
        poly.closePath();
        GArea v = new GArea(poly, layer);
        Struct lf = new Struct("Torus_" + (int) (radInner * 1000) + "_"
                + (int) (radOuter * 1000) + "_s_" + NumSides + "_a_" + angle, v);
        return lf;
    }
}
