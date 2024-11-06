package com.s8.pkgs.io.svg.web.shapes;

import java.util.List;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.elements.shapes.path.SVG_PathElement;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;

/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Path extends WebSVG_Shape {

	

	/**
	 * 
	 * @param branch
	 * @param stroke
	 * @param fill
	 * @param isBoundingBoxUpdating
	 * @param elements
	 * @return
	 */
	public static WebSVG_Path create(S8WebFront branch, SVG_Stroke stroke, SVG_Fill fill,
			boolean isBoundingBoxUpdating,
			SVG_PathElement... elements) {
		WebSVG_Path shape = new WebSVG_Path(branch);
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		shape.setElements(elements);
		return shape;
	}


	public WebSVG_Path(S8WebFront branch) {
		super(branch, "/WebSVG_Path");
	}
	
	
	/**
	 * 
	 * @param point
	 */
	public void setElements(SVG_PathElement[] elements) {
		vertex.outbound().setExplicitSerializableArrayField("elements", elements);
	}
	
	
	/**
	 * 
	 * @param point
	 */
	public void setElements(List<SVG_PathElement> elements) {
		vertex.outbound().setExplicitSerializableArrayField("elements", elements);
	}



}
