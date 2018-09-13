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
import JGDS2.Text;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rob
 */
public class Ex08Text {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex08Text.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            // Placing a text string within a cell -> GDS
            String label = "Baym!";
            // setup Text
            Text lb = new Text(label, 1, 50.0);  // (string,layer,size in um)
            lb.setFont("Serif");        // vector font
            lb.setRenderReso(0.100);    // rendering resolution in um
            Struct labelString = new Struct("lb", lb);
            lib.add(new Ref(labelString, 0, 0));
            //
            // Using affine transforms to find center and rotate objects
            // Rotating the above string by -90 degrees around the centroid
            Area a = new Area(lb.getArea());
            Rectangle2D rec = a.getBounds2D();
            a.transform(AffineTransform.getTranslateInstance(-rec.getCenterX(), -rec.getCenterY()));
            a.transform(AffineTransform.getRotateInstance(-Math.PI / 2.0));
            Struct lbl = new Struct("lblRotated", new GArea(a, 2));
            lib.add(new Ref(lbl, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
