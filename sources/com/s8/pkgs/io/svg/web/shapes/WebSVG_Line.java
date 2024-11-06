package com.s8.pkgs.io.svg.web.shapes;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;

/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Line extends WebSVG_Shape {
	

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
	public static WebSVG_Line create(S8WebFront branch, SVG_Stroke stroke,
			double x0, double y0, double x1, double y1, boolean isBoundingBoxUpdating) {
		WebSVG_Line circle = new WebSVG_Line(branch);
		circle.setStroke(stroke);
		circle.setCoordinates((float) x0, (float) y0, (float) x1, (float) y1);
		circle.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
	}
	
	public static WebSVG_Line create(S8WebFront branch, SVG_Stroke stroke,
			SVG_Vector p0, SVG_Vector p1, boolean isBoundingBoxUpdating) {
		return create(branch, stroke, p0.getX(), p0.getY(), p1.getX(), p1.getY(), isBoundingBoxUpdating);
	}
	


	public static WebSVG_Line create(S8WebFront branch, float x0, float y0, float x1, float y1) {
		WebSVG_Line line = new WebSVG_Line(branch);
		line.setCoordinates(x0, y0, x1, y1);
		return line;
	}
	
	
	public WebSVG_Line(S8WebFront front) {
		super(front, "/WebSVG_Line");
	}
	
	
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(float x0, float y0, float x1, float y1) {
		setCoordinates(new float[] { x0, y0, x1, y1});
	}
	
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(double x0, double y0, double x1, double y1) {
		setCoordinates(new float[] { 
				(float) x0, 
				(float) y0,
				(float) x1, 
				(float) y1});
	}
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(SVG_Vector p0, SVG_Vector p1) {
		setCoordinates(new float[] { 
				(float) p0.getX(), (float) p0.getY(), 
				(float) p1.getX(), (float) p1.getY()});
	}
	
	

	/**
	 * following order: {x0, y0, x1, y1}
	 * @param coordinates
	 */
	public void setCoordinates(float[] coordinates) {
		vertex.outbound().setFloat32ArrayField("coordinates", coordinates);
	}

	
}
