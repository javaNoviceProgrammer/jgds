package jgds.util;

import java.util.Map;

import ch.epfl.general_libraries.utils.SimpleMap;
import jgds.elements.AbstractElement;
import jgds.elements.positioning.Port;

public class DataBase {

	public static Map<String, Port> objectPorts = new SimpleMap<String, Port>() ;
	public static Map<String, Double> objectProperties = new SimpleMap<String, Double>() ;
	public static Map<String, AbstractElement> allElements = new SimpleMap<String, AbstractElement>() ;

	public static void clearDataBase() {
		objectPorts.clear();
		objectProperties.clear();
		allElements.clear();
	}
}
