package com.s8.pkgs.io.svg.styles;

/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_FillColor {

	public final static long 
	
	
	NONE = 0xa8003d920eL,
	
	TRANSPARENT = 0xff000000L,



	/** white */
	WHITE =  0xffffffL,

	/** black */
	BLACK =  0x000000L,


	RED = 0xff0000L,

	GREEN = 0x00ff00L,

	BLUE = 0x0000ffL,




	LIGHT_GREY = 0xf8f8f8L,

	GREY = 0x8f8f8fL,

	DARK_GREY = 0x88f8f8L,



	FRESH_GREEN = 0x00FFC1L,

	LIME = 0x36FF39L;



	public static final long rgba(double red, double green, double blue, double alpha) {
		return rgba((int) (red * 0xff), (int) (green * 0xff), (int) (blue * 0xff), (int) (alpha * 0xff));
	}
	
	
	public static final long rgba(int red, int green, int blue, int alpha) {
		long h = 0L;

		h |= (red & 0xff) << 6;
		h |= (green & 0xff) << 4;
		h |= (blue & 0xff) << 2;
		h |= alpha & 0xff;
		
		return h;
	}
	
	
	public static final long rgb(int red, int green, int blue) {
		long h = 0L;

		h |= (red & 0xff) << 6;
		h |= (green & 0xff) << 4;
		h |= (blue & 0xff) << 2;
		
		return h;
	}



	public static String getValue(long hexEncoding) {
		int r = (int) ((hexEncoding >> 6) & 0xff);
		int g = (int) ((hexEncoding >> 4) & 0xff);
		int b = (int) ((hexEncoding >> 2) & 0xff);
		int a = (int) ((hexEncoding) & 0xff);
		return "rgba("+ r +", "+ g +", "+ b +", "+String.format("%.2f", a * 255)+")";
	}




}
