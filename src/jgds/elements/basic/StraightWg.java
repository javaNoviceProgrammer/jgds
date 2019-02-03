package jgds.elements.basic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Path2D;

import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYLineAnnotation;

import JGDS2.GArea;
import JGDS2.GDS2Element;
import ch.epfl.general_libraries.clazzes.ParamName;
import jgds.elements.AbstractElement;
import jgds.elements.positioning.Port;
import jgds.elements.positioning.ports.TwoPortConfig;
import jgds.pdk.AbstractLayerMap;

public class StraightWg extends AbstractElement {

	double width_um, length_um ;
	Port port1, port2 ;
	AbstractLayerMap layerMap ;

	public StraightWg(
			@ParamName(name="Object Name") String name,
			@ParamName(name="Layer Map") AbstractLayerMap layerMap,
			@ParamName(name="Choose port") TwoPortConfig portsConfig ,
			Port port ,
			@ParamName(name="length (um)") double length_um
			) {
		this.name = name ;
		this.layerMap = layerMap ;
		switch (portsConfig) {
		case port_1:
			this.port1 = port ;
			this.port2 = port1.translateXY(port1.connect().getNormalVec().resize(length_um)).connect() ;
			break;
		case port_2:
			this.port2 = port ;
			this.port1 = port2.translateXY(port2.connect().getNormalVec().resize(length_um)).connect() ;
			break;
		default:
			break;
		}
		this.length_um = length_um ;
		this.width_um = port1.getWidthMicron() ;
	}

	@Override
	public GDS2Element getElement() {
		Path2D.Double p = new Path2D.Double() ;
		p.moveTo(port1.getPosition().getX(), port1.getPosition().getY());
		p.lineTo(port2.getPosition().getX(), port2.getPosition().getY());
		BasicStroke bs = new BasicStroke((float) width_um, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL) ;
		Shape wgShape = bs.createStrokedShape(p) ;
		GArea ga = new GArea(wgShape, layerMap.getLayerNumber()) ;
		return ga ;
	}

	@Override
	public XYAnnotation getAnnotation() {
		double x1 = port1.getPosition().getX() ;
		double y1 = port1.getPosition().getY() ;
		double x2 = port2.getPosition().getX() ;
		double y2 = port2.getPosition().getY() ;
		BasicStroke bs = new BasicStroke((float) width_um, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL) ;
		XYAnnotation annot = new XYLineAnnotation(x1, y1, x2, y2, bs, Color.BLACK) ;
		return annot;
	}

}
