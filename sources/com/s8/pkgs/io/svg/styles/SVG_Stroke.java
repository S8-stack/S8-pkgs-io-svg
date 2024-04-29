package com.s8.pkgs.io.svg.styles;

/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Stroke {
	
	
	public static SVG_Stroke create(
			SVG_StrokeSolidity solidity, 
			SVG_StrokeThickness thickness, 
			SVG_StrokeColor color) {
		
		return new SVG_Stroke(solidity, thickness, color);
	}
	

	
	/**
	 * 
	 */
	public final SVG_StrokeSolidity solidity;
	
	
	/**
	 * 
	 */
	public final SVG_StrokeThickness thickness;
	
	
	/**
	 * 
	 */
	public final SVG_StrokeColor color;


	
	/**
	 * 
	 * @param solidity
	 * @param thickness
	 * @param color
	 */
	public SVG_Stroke(SVG_StrokeSolidity solidity, SVG_StrokeThickness thickness, SVG_StrokeColor color) {
		super();
		this.solidity = solidity;
		this.thickness = thickness;
		this.color = color;
	}
	
	
	
}
