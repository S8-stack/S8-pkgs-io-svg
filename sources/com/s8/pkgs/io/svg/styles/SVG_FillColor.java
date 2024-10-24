package com.s8.pkgs.io.svg.styles;

import com.s8.build.js.JS_Enum;
import com.s8.build.js.JS_CodeGenerator;

/**
 * 
 * @author pierreconvert
 *
 */
public enum SVG_FillColor implements JS_Enum {

	NONE(0x00, "none", 0xff0000, 0),
	
	

	/** white */
	WHITE(0x01, "white", 0xffffff, 1),
	
	/** black */
	BLACK(0x02, "black", 0x000000, 1),
	
	

	
	
	RED(0x12, "red", 0xff0000, 1),
	
	GREEN(0x13, "green", 0x00ff00, 1),
	
	BLUE(0x14, "blue", 0x0000ff, 1),
	
	
	
	
	LIGHT_GREY(0x13, "grey", 0xf8f8f8, 1),
	
	GREY(0x13, "grey", 0x8f8f8f, 1),
	
	DARK_GREY(0x13, "grey", 0x88f8f8, 1),
	
	
	
	FRESH_GREEN(0x22, "fresh green", 0x00FFC1, 1),
	
	LIME(0x23, "lime", 0x36FF39, 1);


	
	/**
	 * code : WebFront
	 */
	public final int code;
	
	
	/**
	 * 
	 */
	public final String name;
	
	
	public final int hexEncoding;

	public final double alpha;

	
	private SVG_FillColor(int code, String name, int hexEncoding, double alpha) {
		this.code = code;
		this.name = name;
		this.hexEncoding = hexEncoding;
		this.alpha = alpha;
	}
	
	


	@Override
	public boolean isDefault() {
		return code == BLACK.code;
	}





	@Override
	public int getKey() {
		return code;
	}





	@Override
	public String getValue() {
		int r = (hexEncoding >> 4) & 0xff;
		int g = (hexEncoding >> 2) & 0xff;
		int b = (hexEncoding) & 0xff;
		return "rgba("+ r +", "+ g +", "+ b +", "+String.format("%.2f", alpha)+")";
	}





	@Override
	public String getComment() {
		return name;
	}
	
	
	
	public static void JS_generateFunction(JS_CodeGenerator gen) {
		gen.appendEnumByCodeFunc(
				"Normalized fill colors", 
				"WebSVG.getFillColorByCode", 
				SVG_FillColor.values());
	}

	
}
