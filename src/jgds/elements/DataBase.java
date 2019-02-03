package jgds.elements;

import ch.epfl.general_libraries.utils.SimpleMap;
import jgds.elements.positioning.Port;

import java.util.Map;

public abstract class DataBase {

	public Map<String, Port> objectPorts = new SimpleMap<String, Port>() ;
	public Map<String, Double> objectProperties = new SimpleMap<String, Double>() ;
	public Map<String, AbstractElement> allElements = new SimpleMap<String, AbstractElement>() ;

}
