package jgds.other;

import java.awt.BasicStroke;
import java.awt.geom.Path2D;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import JGDS2.GArea;
import JGDS2.GDSWriter;
import JGDS2.Lib;
import JGDS2.Rect;
import JGDS2.Ref;
import JGDS2.Struct;
import mathLib.util.Conversions;
import mathLib.util.Units;

public class CurvedRingWg {
	
	double ringRadius ; 
	double ringWidth ;
	double gap ;
	double busWidth ;
	double busRadius ;
	double theta ;
	
	String file ;
	
	public CurvedRingWg(
			double ringRadius, 
			double ringWidth ,
			double gap , 
			double busWidth ,
			double busRadius ,
			double theta
			) {
		this.ringRadius = ringRadius ;
		this.ringWidth = ringWidth ;
		this.gap = gap ;
		this.busWidth = busWidth ;
		this.busRadius = busRadius ;
		this.theta = theta ;
	}
	
	public void setFile(String file) {
		this.file = file ;
	}
	
	public void createGDS() {
		try {
			FileOutputStream fileOUT;
			File f = new File(file);
			fileOUT = new FileOutputStream(f);
			DataOutputStream dO = new DataOutputStream(fileOUT);
			GDSWriter g = new GDSWriter(dO);
			Lib lib = new Lib();
			
			Struct topcell = new Struct("top_cell") ;
			
			// creating the ring

			double startAngleDegree = 0; // for a full ring, set it to 361 or higher
			double endAngleDegree = 362;
			
			double width_ring = ringWidth ;
			double radius = ringRadius ;

			Struct ring = createWg("ring", 0, 0, width_ring, radius, startAngleDegree, endAngleDegree);
			topcell.add(new Ref(ring, 0, 0));
			
			double xc = -radius ;
			double yc = 0 ;
			
			// creating bus waveguide
			
			double width_wg = busWidth ;
			double radius_bus = busRadius ;
			double theta = this.theta ; // degree
			double d = gap ;
			
			double x0 = xc ;
			double y0 = yc - radius - width_ring/2.0 - d - width_wg/2.0 ;

			Struct curvedWg1 = createWg("c1", x0, y0, width_wg, yc-y0, -90, -90+theta/2.0);
			topcell.add(new Ref(curvedWg1, 0, 0));
			
			Struct curvedWg2 = createWg("c2", x0, y0, width_wg, yc-y0, -90, -90-theta/2.0);
			topcell.add(new Ref(curvedWg2, 0, 0));
			
			double t = Conversions.angle(theta/2.0, Units.degree, Units.radian) ;
			double x1 = x0 + (yc-y0)*Math.sin(t) ;
			double y1 = y0 + (yc-y0)*(1-Math.cos(t)) ;
			
			Struct curvedWg3 = createWg("c3", x1, y1, width_wg, radius_bus, 90+theta/2.0, 90);
			topcell.add(new Ref(curvedWg3, 0, 0));
			
			Struct curvedWg4 = createWg("c4", (2*xc-x1), y1, width_wg, radius_bus, 90-theta/2.0, 90);
			topcell.add(new Ref(curvedWg4, 0, 0));
			
			double x2 = x1 + radius_bus*Math.sin(t) ;
			double y2 = y1 + radius_bus*(1-Math.cos(t)) ;
			
			Struct wg5 = new Struct("wg5");
			wg5.add(new Rect(x2, y2-width_wg/2.0, x2+5, y2+width_wg/2.0, 1));
			topcell.add(new Ref(wg5, 0, 0));
			
			Struct wg6 = new Struct("wg6");
			wg6.add(new Rect(2*xc-x2, y2-width_wg/2.0, 2*xc-x2-5, y2+width_wg/2.0, 1));
			topcell.add(new Ref(wg6, 0, 0));
			
			lib.add(new Ref(topcell, 0, 0));
			lib.GDSOut(g);
			System.out.println(" Saved to " + f.getAbsolutePath());
		} catch (IOException eOutput) {
			eOutput.printStackTrace();
		}
		System.out.println("done");
	}
	
    static Struct createWg(String name, double x0, double y0, double width, double radius, double startAngleDegree, double endAngleDegree) {
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
    	return new Struct(name, area) ;
    }
}
