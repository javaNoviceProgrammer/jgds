/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.tutorial;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import JGDS2.Text;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rob
 */
public class Ex14TextRenderReso {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("Ex14TextRenderReso.gds");      // change gds Filename
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            Struct top = new Struct("top");
            Text tf1 = new Text("f", 1, 50.0);  // (string,layer,size in um)
            tf1.setFont("Serif");
            tf1.setRenderReso(0.010);
            Struct f1 = new Struct("f1", tf1);
            Text tf2 = new Text("f", 1, 50.0);  // (string,layer,size in um)
            tf2.setFont("Serif");
            tf2.setRenderReso(0.100);
            Struct f2 = new Struct("f2", tf2);
            Text tf3 = new Text("f", 1, 50.0);  // (string,layer,size in um)            
            tf3.setFont("Serif");
            tf3.setRenderReso(1.000);
            Struct f3 = new Struct("f3", tf3);
            top.add(new Ref(f1, 0, 0));
            top.add(new Ref(f2, 25, 0));
            top.add(new Ref(f3, 50, 0));
            lib.add(new Ref(top, 0, 0));

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
