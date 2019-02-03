package jgds.elements.basic;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.Path2D;

import JGDS2.GArea;
import JGDS2.GDS2Element;
import ch.epfl.general_libraries.clazzes.ParamName;
import jgds.elements.AbstractElement;
import jgds.elements.positioning.Port;
import jgds.elements.positioning.ports.TwoPortConfig;
import jgds.pdk.AbstractLayerMap;

public class StraightWg extends AbstractElement {

	double width, length ;
	Port port1, port2 ;
	AbstractLayerMap layerMap ;

	public StraightWg(
			@ParamName(name="Object Name") String name,
			@ParamName(name="Layer Map") AbstractLayerMap layerMap,
			@ParamName(name="Choose port") TwoPortConfig portsConfig ,
			Port port ,
			@ParamName(name="length (um)") double length
			) {
		this.name = name ;
		this.layerMap = layerMap ;
		switch (portsConfig) {
		case port_1:
			this.port1 = port ;
			this.port2 = port1.translateXY(port1.connect().getNormalVec().resize(length)).connect() ;
			break;
		case port_2:
			this.port2 = port ;
			this.port1 = port2.translateXY(port2.connect().getNormalVec().resize(length)).connect() ;
			break;
		default:
			break;
		}
		this.length = length ;
		this.width = port1.getWidthMicron() ;

		saveProperties() ;
	}

	private void saveProperties() {
		// ports
		objectPorts.put(name+".port1", port1) ;
		objectPorts.put(name+".port2", port2) ;
		// properties
		objectProperties.put(name+".width", width) ;
		objectProperties.put(name+".length", length) ;
		// elements
		allElements.put(name, this) ;
	}

	@Override
	public GDS2Element getElement() {
		Path2D.Double p = new Path2D.Double() ;
		p.moveTo(port1.getPosition().getX(), port1.getPosition().getY());
		p.lineTo(port2.getPosition().getX(), port2.getPosition().getY());
		BasicStroke bs = new BasicStroke((float) width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL) ;
		Shape wgShape = bs.createStrokedShape(p) ;
		GArea ga = new GArea(wgShape, layerMap.getLayerNumber()) ;
		return ga ;
	}

}
