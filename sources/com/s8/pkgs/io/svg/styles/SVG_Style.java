package com.s8.pkgs.io.svg.styles;


/**
 * 
 */
public class SVG_Style {
	

	
	public static SVG_Style createDefault() {
		return new SVG_Style(SVG_Stroke.createDefault(), SVG_Fill.createDefault());
	}
	

	
	/**
	 * 
	 */
	public SVG_Stroke stroke;
	
	
	/**
	 * 
	 */
	public SVG_Fill fill;
	
	public SVG_Style(SVG_Stroke stroke, SVG_Fill fill) {
		super();
		this.stroke = stroke;
		this.fill = fill;
	}
	
	
}
