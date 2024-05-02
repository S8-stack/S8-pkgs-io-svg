package com.s8.pkgs.io.svg.styles;

import com.s8.build.js.JS_Enum;
import com.s8.build.js.JS_Generator;

/**
 * 
 * @author pierreconvert
 *
 */
public enum SVG_StrokeColor implements JS_Enum {

	
	
	/** black */
	BLACK(0x02, "black", 0x000000, 1),
	
	/** white */
	WHITE(0x04, "white", 0xffffff, 1),
	
	
	RED(0x12, "red", 0xff0000, 1),
	
	GREEN(0x13, "green", 0x00ff00, 1),
	
	BLUE(0x14, "blue", 0x0000ff, 1),
	
	
	
	
	LIGHT_GREY1(0x21, "light grey 1", 0xF7F7F7, 1),
	
	LIGHT_GREY2(0x22, "light grey 2", 0xDFDFDF, 1),
	
	MID_GREY1(0x23, "mid grey", 0xC7C7C7, 1),
	
	MID_GREY2(0x24, "mid grey", 0xB6B6B6, 1),
	
	GREY(0x26, "grey", 0x8f8f8f, 1),
	
	DARK_GREY(0x27, "dark grey", 0x88f8f8, 1),
	
	
	
	FRESH_GREEN(0x42, "fresh green", 0x00FFC1, 1);


	
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

	
	private SVG_StrokeColor(int code, String name, int hexEncoding, double alpha) {
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
		int r = ((hexEncoding >> 16) & 0xff);
		int g = ((hexEncoding >> 8) & 0xff);
		int b = ((hexEncoding) & 0xff);
		return "rgba("+ r +", "+ g +", "+ b +", "+String.format("%.2f", alpha)+")";
	}





	@Override
	public String getComment() {
		return name;
	}
	
	
	public static void JS_generateFunction(JS_Generator gen) {
		gen.appendEnumByCodeFunc(
				"Normalized stroke colors", 
				"WebSVG.getStrokeColorByCode", 
				SVG_StrokeColor.values());
	}

	
}
