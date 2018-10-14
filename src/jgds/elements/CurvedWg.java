/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgds.elements;

import java.awt.BasicStroke;
import java.awt.geom.Path2D;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GArea;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Ref;
import JGDS2.Struct;

public class CurvedWg {

    public static void main(String[] args) {
        try {
            FileOutputStream fileOUT;
            File f = new File("curved_wg.gds");
            fileOUT = new FileOutputStream(f);
            DataOutputStream dO = new DataOutputStream(fileOUT);
            GDSWriter g = new GDSWriter(dO);
            Lib lib = new Lib();

            double startAngleDegree = 45 ; // for a full ring, set it to 361 or higher
            double endAngleDegree = 180 ;

            Struct curvedWg = createWg(0, 0, 0.4, 10, startAngleDegree, endAngleDegree) ;
            Ref ref = new Ref(curvedWg, 0, 0, 0, 0) ;
            lib.add(ref);

            lib.GDSOut(g);
            System.out.println(" Saved to " + f.getAbsolutePath());
        } catch (IOException eOutput) {
            eOutput.printStackTrace();
        }
        System.out.println("done");
    }

    static Struct createWg(double x0, double y0, double width, double radius, double startAngleDegree, double endAngleDegree) {
    	Path2D.Double path = new Path2D.Double() ; // this is the path of the center
    	path.moveTo(x0, y0);
    	int numPoints = 500 ;
    	double xCenter = x0 - radius*Math.cos(startAngleDegree*Math.PI/180) ;
    	double yCenter = y0 - radius*Math.sin(startAngleDegree*Math.PI/180) ;
    	for(int i=1; i<=numPoints; i++) {
    		double angleDegree = startAngleDegree + i*(endAngleDegree-startAngleDegree)/numPoints ;
    		double xc = xCenter + radius*Math.cos(angleDegree*Math.PI/180.0) ;
    		double yc = yCenter + radius*Math.sin(angleDegree*Math.PI/180.0) ;
    		path.lineTo(xc, yc);
    	}
    	BasicStroke stroke = new BasicStroke((float) width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL) ;
    	GArea area = new GArea(stroke.createStrokedShape(path), 1) ;
    	return new Struct("Cwg", area) ;
    }

}
