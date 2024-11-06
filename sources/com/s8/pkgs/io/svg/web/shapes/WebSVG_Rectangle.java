package com.s8.pkgs.io.svg.web.shapes;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;

/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Rectangle extends WebSVG_Shape {

	

	/**
	 * 
	 * @param branch
	 * @param color
	 * @param solidity
	 * @param thickness
	 * @param xc
	 * @param yc
	 * @param r
	 * @return
	 */
	public static WebSVG_Rectangle create(S8WebFront branch, SVG_Stroke stroke, SVG_Fill fill,
			double x, double y, double width, double height, 
			boolean isBoundingBoxUpdating) {
		WebSVG_Rectangle shape = new WebSVG_Rectangle(branch);
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setTopLeftCorner(x, y);
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}
	
	/**
	 * 
	 * @param branch
	 * @param color
	 * @param solidity
	 * @param thickness
	 * @param xc
	 * @param yc
	 * @param r
	 * @return
	 */
	public static WebSVG_Rectangle createRound(S8WebFront branch, SVG_Stroke stroke, SVG_Fill fill,
			double x, double y, double width, double height, double radius,
			boolean isBoundingBoxUpdating) {
		WebSVG_Rectangle shape = new WebSVG_Rectangle(branch);
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setTopLeftCorner(x, y);
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setRadius(radius);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}


	public WebSVG_Rectangle(S8WebFront front) {
		super(front, "/WebSVG_Rectangle");
	}

	
	/**
	 * 
	 * @param point
	 */
	public void setTopLeftCorner(SVG_Vector point) {
		vertex.outbound().setFloat32ArrayField("bottomLeftCorner", new float[] {
				(float) point.getX(), (float) point.getY()
		});
	}
	

	public void setTopLeftCorner(float x, float y) {
		vertex.outbound().setFloat32ArrayField("bottomLeftCorner", new float[] { x, y});
	}
	
	public void setTopLeftCorner(float[] coordinates) {
		vertex.outbound().setFloat32ArrayField("bottomLeftCorner", coordinates);
	}
	
	public void setTopLeftCorner(double x0, double y0) {
		setTopLeftCorner(new float[] { (float) x0, (float) y0});
	}
	
	
	/**
	 * 
	 * @param value
	 */
	public void setWidth(float value) {
		vertex.outbound().setFloat32Field("width", value);
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setWidth(double value) {
		vertex.outbound().setFloat32Field("width", (float) value);
	}
	
	
	/**
	 * 
	 * @param value
	 */
	public void setHeight(float value) {
		vertex.outbound().setFloat32Field("height", value);
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setHeight(double value) {
		vertex.outbound().setFloat32Field("height", (float) value);
		
	}
	
	
	/**
	 * 
	 * @param radius
	 */
	public void setRadius(float radius) {
		vertex.outbound().setFloat32Field("radius", radius);
	}
	
	
	/**
	 * 
	 * @param radius
	 */
	public void setRadius(double radius) {
		setRadius((float) radius);
	}




}
