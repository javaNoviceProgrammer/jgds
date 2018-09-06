/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.Const;
import JGDS2.GArea;
import JGDS2.GDS2;
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
public class Ex12YetAnotherTree implements Const {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex12YetAnotherTree.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("top");
            //cell tree11_1843197
            top.add(new Ref(tree(.025, 2.44, 1.5, 12), 6, 4, 0, 4, 0));
            //cell tree11_-458895
            top.add(new Ref(tree(.125, -.675, 2.5, 12), 6, 8, 0, 4, 0));
            //cell tree39_3962446
            top.add(new Ref(tree(.02, 5, 14, 40), 6, 16, 0, 4, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    static Struct tree(double d, double ang, double ratio, int depth) {
        double rd = .25;
        double p = ratio / (ratio + 1);
        Path2D.Double poly3 = new Path2D.Double();
        poly3.moveTo(d / 2, 0);
        poly3.curveTo(d / 2, .5 - rd * Math.cos(ang), .5 - rd * Math.sin(ang), .5 - rd * Math.cos(ang), .5, .5);
        Stroke bs = new BasicStroke((float) d, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, (float) 2.5);
        GArea fa = new GArea(bs.createStrokedShape(poly3), 2);
        ang = ang * 180 / Math.PI;
        Struct s = new Struct("s" + "_" + GDS2.val(d * 11 + ang * 13 + ratio * 17), fa);
        Struct w = new Struct("d0" + "_" + GDS2.val(d * 11 + ang * 13 + ratio * 17), new Ref(s, -(.5 - p) * d, 0, 0, (1 - p), 0));
        w.add(new Ref(s, d * (p - .5), 0, MIRROR, p, 180));
        ArrayList<Struct> n = new ArrayList();
        n.add(w);
        boolean odd;
        int i;
        Struct temp;
        Ref r;
        for (i = 1; i < depth; i++) {
            odd = ((i / 2) * 2 == i) ? false : true;
            temp = new Struct("tree" + i + "_" + GDS2.val(d * 11 + ang * 13 + ratio * 17));
            r = new Ref(n.get(i - 1), -.5 * p + (d * (p - .5)), .5 * p, odd ? MIRROR : 0);
            r.setMag(p);
            r.setAngle(ang + (odd ? 180 : 0));
            temp.add(r);
            r = new Ref(n.get(i - 1), .5 * (1 - p) - (.5 - p) * d, .5 * (1 - p), odd ? 0 : MIRROR);
            r.setMag(1 - p);
            r.setAngle(360 - ang - (odd ? 0 : 180));
            temp.add(r);
            r = new Ref(n.get(0), 0, 0);
            temp.add(r);
            n.add(temp);
        }
        return n.get(i - 1);
    }
}
