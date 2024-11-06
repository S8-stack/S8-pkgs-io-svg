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
public class WebSVG_Polygon extends WebSVG_Shape {
	

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
	public static WebSVG_Polygon create(S8WebFront branch, SVG_Stroke stroke, SVG_Fill fill,
			float[] coordinates, boolean isBoundingBoxUpdating) {
		WebSVG_Polygon polyline = new WebSVG_Polygon(branch);
		polyline.setStroke(stroke);
		polyline.setCoordinates(coordinates);
		polyline.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return polyline;
	}
	
	
	/**
	 * 
	 * @param branch
	 * @param coordinates
	 * @return
	 */
	public static WebSVG_Polygon create(S8WebFront branch, float[] coordinates) {
		WebSVG_Polygon line = new WebSVG_Polygon(branch);
		line.setCoordinates(coordinates);
		return line;
	}
	
	
	/**
	 * 
	 * @param branch
	 */
	public WebSVG_Polygon(S8WebFront branch) {
		super(branch, "/WebSVG_Polygon");
	}
	

	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 * @param coordinates
	 */
	public void setCoordinates(float[] coordinates) {
		vertex.outbound().setFloat32ArrayField("coordinates", coordinates);
	}
	
	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 * @param coordinates
	 */
	public void setCoordinates(double[] coordinates) {
		int n = coordinates.length;
		float[] fcoords = new float[n];
		for(int i = 0; i<n; i++) { fcoords[i] = (float) coordinates[i]; }
		vertex.outbound().setFloat32ArrayField("coordinates", fcoords);
	}
	
	
	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 * @param coordinates
	 */
	public void setCoordinates(SVG_Vector[] points) {
		int nPoints = points.length;
		float[] coordinates = new float[2*nPoints];
		for(int i = 0; i<nPoints; i++) {
			SVG_Vector point = points[i];
			coordinates[2*i + 0] = (float) point.getX();
			coordinates[2*i + 1] = (float) point.getY();
		}
		vertex.outbound().setFloat32ArrayField("coordinates", coordinates);
	}

}
