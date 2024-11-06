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
public class WebSVG_Circle extends WebSVG_Shape {

	

	
	/**
	 * 
	 * @param front
	 * @param stroke
	 * @param center
	 * @param r
	 * @param isBoundingBoxUpdating
	 * @return
	 */
	public static WebSVG_Circle createCircle(S8WebFront front, SVG_Stroke stroke,
			SVG_Vector center, double r, boolean isBoundingBoxUpdating) {
		return createCircle(front, stroke, center.getX(), center.getY(), r, isBoundingBoxUpdating);
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
	public static WebSVG_Circle createCircle(S8WebFront branch, SVG_Stroke stroke,
			double xc, double yc, 
			double r,
			boolean isBoundingBoxUpdating) {
		WebSVG_Circle circle = new WebSVG_Circle(branch);
		circle.setStroke(stroke);
		circle.setCenter(xc, yc);
		circle.setRadius(r);
		circle.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
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
	public static WebSVG_Circle createRound(S8WebFront branch, SVG_Stroke stroke, SVG_Fill fill,
			SVG_Vector center,double r,
			boolean isBoundingBoxUpdating) {
		WebSVG_Circle shape = new WebSVG_Circle(branch);
		shape.setStroke(stroke);
		shape.setFill(fill);
		shape.setCenter(center);
		shape.setRadius(r);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}


	public WebSVG_Circle(S8WebFront front) {
		super(front, "/WebSVG_Circle");
	}

	
	/**
	 * 
	 * @param point
	 */
	public void setCenter(SVG_Vector point) {
		vertex.outbound().setFloat32ArrayField("center", new float[] {
				(float) point.getX(), (float) point.getY()
		});
	}
	

	public void setCenter(float x0, float y0) {
		vertex.outbound().setFloat32ArrayField("center", new float[] { x0, y0});
	}
	
	public void setCenter(float[] coordinates) {
		vertex.outbound().setFloat32ArrayField("center", coordinates);
	}
	
	public void setCenter(double x0, double y0) {
		setCenter(new float[] { (float) x0, (float) y0});
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
