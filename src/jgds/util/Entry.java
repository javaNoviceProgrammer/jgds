package jgds.util;

import ch.epfl.general_libraries.clazzes.ParamName;

public class Entry extends DataBase {

	double e ;

	public Entry(
			@ParamName(name="Value") double entry
			){
		this.e = entry ;
	}

	public Entry(
			@ParamName(name="Reference to other Objects") String entry,
			@ParamName(name="Multiplier [+/-]", default_="1") double multiplier,
			@ParamName(name="Offset [+/-]", default_="0") double offset
			){
		this.e = objectProperties.get(entry) * multiplier + offset ;
	}

	public double getValue(){
		return e ;
	}

	public static Entry getInstance(double e) {
		return new Entry(e) ;
	}

	public static Entry getInstance(String e) {
		return new Entry(e, 1, 0) ;
	}

	@Override
	public String toString() {
		return e+"" ;
	}

	// operator overload

	public static Entry valueOf(double x) {
		return new Entry(x) ;
	}

	public static Entry valueOf(int x) {
		return new Entry(x) ;
	}

	public static Entry valueOf(float x) {
		return new Entry(x) ;
	}

	public static Entry valueOf(long x) {
		return new Entry(x) ;
	}

	public static Entry valueOf(Entry x) {
		return new Entry(x.getValue()) ;
	}

	// for test
	public static void main(String[] args) {
		Entry e1 = 21 ;
		System.out.println(e1);
	}

}
