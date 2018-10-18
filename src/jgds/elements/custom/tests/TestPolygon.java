package jgds.elements.custom.tests;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;
import jgds.elements.custom.Polygon;

public class TestPolygon {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("polygon.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();
            
            Struct topCell = new Struct("topcell") ;

            Polygon polygon = new Polygon("perturbation", 1) ;
            polygon.start(0, 0);
            polygon.addVertex(20e-3, 0);
            polygon.addVertex(10e-3, 20e-3);
            polygon.draw();
            
            topCell.add(new Ref(polygon, 0.0, 0.0));

            lib.add(new Ref(topCell, 0, 0));
            
            lib.GDSOut(g);

            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }
}
