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
 * Drawing circles and ellipses 2 different ways - returning Struct and GArea
 * the methods utilize Path2D.Double to create a closed path
 * 
 * @author rob
 */
public class Ex04CircleEllipse {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex04CircleEllipse.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // Using CircleEllipse method that returns a Struct
            // Drawing a 64 sided - 10um radius circle
            Double radX = 30.00, radY = 30.00;
            int numberOfSides = 1000, gdsLayer = 1;
            Struct circle = CircleEllipse(0, 0, radX, radY, numberOfSides, gdsLayer);
            lib.add(new Ref(circle, 0, 0));
            // Drawing a 64 sided - 10um x 4um ellipse 
            radY = 4.0;
            Struct ellipse = CircleEllipse(0, 0, radX, radY, numberOfSides, gdsLayer);
            lib.add(new Ref(ellipse, 0, 0));
            //
            //
            // Using CircleEllipse method that returns a GArea
            // Drawing a 64 sided - 4um radius circle GArea
            radX = 4.0;
            radY = 4.0;
            GArea circle2 = CircleEllipseArea(0, 0, radX, radY, numberOfSides, gdsLayer);
            //adding GArea to Struct
            Struct circleGArea = new Struct("circleGArea", circle2);        
            lib.add(new Ref(circleGArea, 0, 0));            
            
            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    //
    // method creates a circle and returns a Struct
    //
    static Struct CircleEllipse(double x, double y, double radiusX, double radiusY, int numSides, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(x + radiusX, y);
        int steps = numSides;
        double c = 2 * Math.PI / numSides;
        for (int i = 0; i < steps; i++) {
            poly.lineTo(radiusX * Math.cos(i * c), radiusY * Math.sin(i * c));
        }
        poly.closePath();
        GArea v = new GArea(poly, layer);
        Struct lf = new Struct("CircleEllipse_" + (int) (1000 * radiusX) + "_" + (int) (1000 * radiusY), v);
        return lf;
    }

    //
    // method creates a circle and returns a Struct
    //    
    static GArea CircleEllipseArea(double x, double y, double radiusX, double radiusY, int numSides, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(x + radiusX, y);
        int steps = numSides;
        double c = 2 * Math.PI / numSides;
        for (int i = 0; i < steps; i++) {
            poly.lineTo(radiusX * Math.cos(i * c), radiusY * Math.sin(i * c));
        }
        poly.closePath();
        GArea v = new GArea(poly, layer);
        return v;
    }
}
