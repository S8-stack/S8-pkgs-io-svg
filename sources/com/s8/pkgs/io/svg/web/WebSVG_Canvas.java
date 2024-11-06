package com.s8.pkgs.io.svg.web;

import java.util.List;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.ui.carbide.HTML_S8WebNode;


/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Canvas extends HTML_S8WebNode {

	
	/**
	 * 
	 * @param front
	 * @param typeName
	 */
	public WebSVG_Canvas(S8WebFront front) {
		super(front, WebSources.WEBPATH + "/WebSVG_Canvas");
	}
	
	

	/**
	 * 
	 * @param elements
	 */
	public void setElements(List<WebSVG_Element> elements) {
		vertex.outbound().setObjectListField("elements", elements);
	}
	
	
	/**
	 * 
	 * @param elements
	 */
	public void setElements(WebSVG_Element... elements) {
		vertex.outbound().setObjectListField("elements", elements);
	}
	
	
	/**
	 * 
	 * @param width
	 */
	public void setViewport(double width, double height, double marginTop, double marginRight, double marginBottom, double marginLeft){
		setViewportDimensions(width, height);
		setViewportMargins(marginTop, marginRight, marginBottom, marginLeft);
	}
	
	/**
	 * 
	 * @param width
	 */
	public void setViewportDimensions(double width, double height){
		setViewportWidth(width);
		setViewportHeight(height);
	}
	
	
	/**
	 * 
	 * @param width
	 */
	public void setViewportWidth(double width){
		vertex.outbound().setFloat32Field("viewportWidth", (float) width);
	}
	
	
	/**
	 * 
	 * @param height
	 */
	public void setViewportHeight(double height){
		vertex.outbound().setFloat32Field("viewportHeight", (float) height);
	}
	
	
	/**
	 * 
	 * @param margin
	 */
	public void setViewportMargins(double marginTop, double marginRight, double marginBottom, double marginLeft){
		setViewportMarginTop(marginTop);
		setViewportMarginRight(marginRight);
		setViewportMarginBottom(marginBottom);
		setViewportMarginLeft(marginLeft);
	}
	
	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginTop(double margin){
		vertex.outbound().setFloat32Field("viewportMarginTop", (float) margin);
	}
	
	
	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginRight(double margin){
		vertex.outbound().setFloat32Field("viewportMarginRight", (float) margin);
	}
	
	
	
	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginBottom(double margin){
		vertex.outbound().setFloat32Field("viewportMarginBottom", (float) margin);
	}
	
	
	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginLeft(double margin){
		vertex.outbound().setFloat32Field("viewportMarginLeft", (float) margin);
	}
	
	
}
