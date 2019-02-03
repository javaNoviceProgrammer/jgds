package jgds.elements.positioning;

import ch.epfl.general_libraries.clazzes.ParamName;
import jgds.pdk.AbstractLayerMap;
import jgds.pdk.generic.GeneralLayer;

public class Label {

	AbstractLayerMap[] layerMap = {new GeneralLayer("text", 1, 0)} ;
	String label ;
	Position P ;

	double angle_degree ;
	double size = 0.25 ;

	public Label(
			@ParamName(name="Position") Position P,
			@ParamName(name="Angle (degree)") double angle_degree,
			@ParamName(name="text") String objectName
			){
		this.P = P ;
		this.angle_degree = angle_degree ;
		this.label = objectName ;
	}

	public void setLabel(String newLabel){
		this.label = newLabel ;
	}

	public Label changeLabel(String newLabel){
		return new Label(P, angle_degree, newLabel) ;
	}

	public Label rotate(double angleDegree){
		return new Label(P, angle_degree + angleDegree, label) ;
	}

	public Label translateXY(double DX, double DY){
		Position P1 = P.translateXY(DX, DY) ;
		return new Label(P1, angle_degree, label) ;
	}

	public Label translateXY(Position DP){
		Position P1 = P.translateXY(DP) ;
		return new Label(P1, angle_degree, label) ;
	}


}
