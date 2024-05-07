package com.s8.pkgs.io.svg.styles;

import com.s8.build.js.JS_Enum;
import com.s8.build.js.JS_CodeGenerator;

public enum SVG_StrokeThickness implements JS_Enum {


	ONE_TENTH(0x22, "1/10", 0.1),

	ONE_EIGHTH(0x23, "1/8", 1.0/8.0),

	ONE_QUARTER(0x24, "1/4", 0.25),

	ONE_THIRD(0x25, "1/3", 1.0 / 3.0),



	HALF(0x32, "1/2", 0.5),

	TWO_THIRDS(0x33, "2/3", 2.0 / 3.0),

	THREE_QUARTERS(0x34, "3/4", 0.75),



	ONE(0x42, "1", 1.0),

	ONE_AND_ONE_QUARTER(0x43, "1-1/4", 1.25),

	ONE_AND_ONE_THIRD(0x44, "1-1/3", 1.0 + 1.0 / 3.0),

	ONE_AND_A_HALF(0x52, "1-1/2", 1.5),

	ONE_AND_TWO_THIRDS(0x53, "1-2/3", 1 + 2.0/3.0),

	ONE_AND_THREE_QUARTERS(0x54, "1-3/4", 1.75),



	TWO(0x63, "2", 2),

	TWO_AND_A_HALF(0x64, "2-1/2", 2.5),


	THREE(0x82, "3", 3.0),

	FOUR(0x84, "4", 4.0),

	FIVE(0x85, "5", 5.0),

	SIX(0x86, "6", 6.0),

	SEVEN(0x87, "7", 7.0),

	EIGHT(0x88, "8", 8.0),

	NINE(0x89, "9", 9.0),

	TEN(0x092, "10", 10.0);


	public final int code;

	public final String name;

	public final double jsValue;


	private SVG_StrokeThickness(int code, String name, double jsValue) {
		this.code = code;
		this.name = name;
		this.jsValue = jsValue;
	}


	
	/**
	 * 
	 * 
	 * @param builder
	 */
	public static void JS_generateFunction(JS_CodeGenerator gen) {
		gen.appendEnumByCodeFunc(
				"Normalized thicknesses", 
				"WebSVG.getStrokeThicknessByCode", 
				SVG_StrokeThickness.values());
	}



	@Override
	public boolean isDefault() {
		return code == ONE.code;
	}



	@Override
	public int getKey() {
		return code;
	}



	@Override
	public String getValue() {
		return String.format("%.3f", jsValue);
	}



	@Override
	public String getComment() {
		return name() + " - " + name;
	}

	

	
}
