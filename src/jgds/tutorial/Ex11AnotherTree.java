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
public class Ex11AnotherTree {
        public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex11AnotherTree.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            //number of levels
//            int iterations = 12;            
            Path2D.Double poly2 = new Path2D.Double();
            poly2.moveTo(0, 0);
            poly2.lineTo(.25, 2);
            poly2.lineTo(.5, 2);
            poly2.lineTo(.35, 0);
            poly2.lineTo(0, 0);
            poly2.closePath();
            GArea tr = new GArea(poly2, 2);

            Struct top = new Struct("top");
            Struct temp;
            Ref r;
            int i;            
            ArrayList<Struct> t = new ArrayList<Struct>();
            t.add(new Struct("t0", tr));
            for (i = 1; i < 12; i++) {
                temp = new Struct("t" + i);
                r = new Ref(t.get(i - 1), .25, 2);
                r.setMag(.75);
                r.setAngle(355);
                temp.add(r);
                r = new Ref(t.get(i - 1), .2, 1.7);
                r.setMag(.44);
                r.setAngle(60);
                temp.add(r);
                r = new Ref(t.get(i - 1), .4, 1);
                r.setMag(.37);
                r.setAngle(300);
                temp.add(r);
                r = new Ref(t.get(0), 0, 0);
                temp.add(r);
                t.add(temp);
            }
            top.add(new Ref(t.get(i - 1), 0, 3));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }   
}
