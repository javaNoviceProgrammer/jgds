/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.Const;
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
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class Ex10CurvedTree implements Const {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex10CurvedTree.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            //number of levels
            int iterations = 12;
            Path2D.Double poly3 = new Path2D.Double();
            poly3.moveTo(0.0625, 0);
            poly3.curveTo(.0625, .25, .5, .25, .5, .5);
            Stroke bs = new BasicStroke((float) .125, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, (float) 2.5);
            GArea fa = new GArea(bs.createStrokedShape(poly3), 2);
            Struct s = new Struct("s", fa);
            Struct d = new Struct("d0", new Ref(s, 0, 0));
            d.add(new Ref(s, 0, 0, MIRROR, 180));
            ArrayList<Struct> n = new ArrayList();
            n.add(d);

            Struct top = new Struct("top");
            Struct temp;
            Ref r;
            int i;
            for (i = 1; i < iterations; i++) {
                temp = new Struct("d" + i);
                r = new Ref(n.get(i - 1), -.5, .5);
                r.setMag(.5);
                temp.add(r);
                r = new Ref(n.get(i - 1), .5, .5);
                r.setMag(.5);
                temp.add(r);
                r = new Ref(n.get(0), 0, 0);
                temp.add(r);
                n.add(temp);
            }
            top.add(new Ref(n.get(i - 1), 4, 4));


            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
