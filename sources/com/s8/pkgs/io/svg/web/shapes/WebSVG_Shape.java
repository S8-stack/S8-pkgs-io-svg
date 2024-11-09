package com.s8.pkgs.io.svg.web.shapes;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.styles.SVG_StrokeColor;
import com.s8.pkgs.io.svg.styles.SVG_StrokeSolidity;
import com.s8.pkgs.io.svg.styles.SVG_StrokeThickness;
import com.s8.pkgs.io.svg.web.WebSVG_Element;
import com.s8.pkgs.io.svg.web.WebSources;


public class WebSVG_Shape extends WebSVG_Element {
	
	
	
	public final static String WEBPATH = WebSources.WEBPATH + "/shapes"; 

	public WebSVG_Shape(S8WebFront branch, String typeName) {
		super(branch, WEBPATH + typeName);
	}
	
	
	
	
	/**
	 * 
	 * @param stroke
	 */
	public void setStroke(SVG_Stroke stroke) {
		setStrokeSolidity(stroke.solidity);
		setStrokeThickness(stroke.thickness);
		setStrokeColor(stroke.color);
	}
	
	
	/**
	 * Define Stroke
	 * @param solidity
	 * @param thickness
	 * @param color
	 */
	public void setStroke(SVG_StrokeSolidity solidity, SVG_StrokeThickness thickness, SVG_StrokeColor color) {
		setStrokeSolidity(solidity);
		setStrokeThickness(thickness);
		setStrokeColor(color);
	}
	
	
	/**
	 * 
	 * @param solidity
	 */
	public void setStrokeSolidity(SVG_StrokeSolidity solidity) {
		if(solidity != null) {
			vertex.outbound().setUInt8Field("strokeSolidity", solidity.code);	
		}
	}
	
	
	/**
	 * 
	 * @param color
	 */
	public void setStrokeColor(SVG_StrokeColor color) {
		if(color != null) {
			vertex.outbound().setUInt8Field("strokeColor", color.code);	
		}
	}
	
	
	/**
	 * Style: 
	 * @param thickness
	 */
	public void setStrokeThickness(SVG_StrokeThickness thickness) {
		if(thickness != null) {
			vertex.outbound().setUInt8Field("strokeThickness", thickness.code);	
		}
	}
	
	

	
	/**
	 * 
	 * @param stroke
	 */
	public void setFill(SVG_Fill fill) {
		setFillColor(fill.color);
	}
	
	
	
	/**
	 * 
	 * @param color
	 */
	public void setFillColor(long hexEncoding) {
		vertex.outbound().setUInt64Field("fillColor", hexEncoding);	
	}
	
}
