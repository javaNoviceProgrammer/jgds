package jgds.pdk.generic;

import ch.epfl.general_libraries.clazzes.ParamName;
import jgds.pdk.AbstractLayerMap;

public class GeneralLayer extends AbstractLayerMap {

	int layerNumber, dataType ;
	String layerName ;

	public GeneralLayer(
			@ParamName(name="Layer Name", default_="general") String layerName,
			@ParamName(name="Layer Number", default_="1") int layerNumber,
			@ParamName(name="Data Type", default_="0") int dataType
			){
		this.layerName = layerName ;
		this.layerNumber = layerNumber ;
		this.dataType = dataType ;
	}

	@Override
	public int getLayerNumber() {
		return layerNumber;
	}

	@Override
	public int getDataType() {
		return dataType;
	}

	@Override
	public String getLayerName() {
		return layerName;
	}

}
