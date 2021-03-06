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
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Constructing S shaped bend using a Bezier curve with Basic Strokes
 * 
 * @author rob
 */
public class Ex07BezierCurve {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex07BezierCurve.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // start coordinates
            double x = 0.0, y = 0.0;
            // Control Points for bezier curve
            double cx1 = 10.0, cy1 = 0, cx2 = 10, cy2 = 10;
            // end coordinates
            double x2 = 20.0, y2 = 10.0;
            //line width and gds Layer number
            double width = 1.0;
            int gdsLayer = 4;
            // create bezier Struct - S-Shaped curve starting at (0,0) and ending at (20, 10)
            Struct bezierShape = Bezier(x, y, cx1, cy1, cx2, cy2, x2, y2, width, gdsLayer);
            lib.add(new Ref(bezierShape, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    // Constructing the S shaped bend using bezier curve with Basic Strokes
    static Struct Bezier(double x, double y, double cx1, double cy1, double cx2, double cy2,
            double x2, double y2, double endW, int layer) {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(x, y);
        poly.curveTo(cx1, cy1, cx2, cy2, x2, y2);
        Stroke bs = new BasicStroke((float) endW, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
        GArea v = new GArea(bs.createStrokedShape(poly), layer);
        Struct lf = new Struct("bezier_" + (int) (x * 1000) + "_" + (int) (y * 1000) + "_"
                + (int) (cx1 * 1000) + "_" + (int) (cy1 * 1000) + "_"
                + (int) (cx2 * 1000) + "_" + (int) (cy2 * 1000) + "_"
                + (int) (x2 * 1000) + "_" + (int) (y2 * 1000) + "_" + layer, v);
        return lf;

    }
}
