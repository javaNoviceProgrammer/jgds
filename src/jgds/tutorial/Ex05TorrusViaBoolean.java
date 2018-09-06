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
 * Building on the previous exercise with identical methods
 * Two circles are created and one is subtracted from the other to create a torus
 * 
 * @author rob
 */
public class Ex05TorrusViaBoolean {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex05TorrusViaBoolean.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // Create 2 circles and subtract them
            //
            Double radX = 10.00, radY = 10.00;
            int numberOfSides = 64, gdsLayer = 4;
            // Step 1. Create a Struct cirle
            Struct cir = CircleEllipse(0, 0, radX, radY, numberOfSides, gdsLayer);
            // Step 2. Create a GArea circle - 2 times bigger
            GArea cir2 = CircleEllipseArea(0, 0, 2 * radX, 2 * radY, numberOfSides, gdsLayer);
            // Step 3. subtract the two to obtain a torrus
            cir2.subtract(cir);
            Struct torus = new Struct("Torrus", cir2);

            lib.add(new Ref(cir, 0, 0));
            lib.add(new Ref(torus, 100, 0));
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    static Struct CircleEllipse(double x, double y, double radiusX, double radiusY, int NumSides, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(x + radiusX, y);
        int steps = NumSides;
        double c = 2 * Math.PI / NumSides;
        for (int i = 0; i < steps; i++) {
            poly.lineTo(radiusX * Math.cos(i * c), radiusY * Math.sin(i * c));
        }
        poly.closePath();
        GArea v = new GArea(poly, layer);
        Struct lf = new Struct("circle_" + (int) (1000 * radiusX) + "_" + (int) (1000 * radiusY), v);
        return lf;
    }

    static GArea CircleEllipseArea(double x, double y, double radiusX, double radiusY, int NumSides, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(x + radiusX, y);
        int steps = NumSides;
        double c = 2 * Math.PI / NumSides;
        for (int i = 0; i < steps; i++) {
            poly.lineTo(radiusX * Math.cos(i * c), radiusY * Math.sin(i * c));
        }
        poly.closePath();
        GArea v = new GArea(poly, layer);
        return v;
    }
}
