package com.s8.pkgs.io.svg.web.shapes;

import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;


/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Polyline extends WebSVG_Shape {
	

	/**
	 * 
	 * @param branch
	 * @param stroke
	 * @param coordinates
	 * @param isBoundingBoxUpdating
	 * @return
	 */
	public static WebSVG_Polyline create(S8WebFront branch, SVG_Stroke stroke, float[] coordinates, boolean isBoundingBoxUpdating) {
		WebSVG_Polyline polyline = new WebSVG_Polyline(branch);
		polyline.setStroke(stroke);
		polyline.setCoordinates(coordinates);
		polyline.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return polyline;
	}
	
	/**
	 * 
	 * @param branch
	 * @param stroke
	 * @param coordinates
	 * @param isBoundingBoxUpdating
	 * @return
	 */
	public static WebSVG_Polyline create(S8WebFront branch, SVG_Stroke stroke, SVG_Vector[] points, boolean isBoundingBoxUpdating) {
		WebSVG_Polyline polyline = new WebSVG_Polyline(branch);
		polyline.setStroke(stroke);
		polyline.setCoordinates(points);
		polyline.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return polyline;
	}
	
	
	/**
	 * 
	 * @param branch
	 * @param coordinates
	 * @return
	 */
	public static WebSVG_Polyline create(S8WebFront branch, float[] coordinates) {
		WebSVG_Polyline line = new WebSVG_Polyline(branch);
		line.setCoordinates(coordinates);
		return line;
	}
	
	
	/**
	 * 
	 * @param branch
	 */
	public WebSVG_Polyline(S8WebFront branch) {
		super(branch, "/WebSVG_Polyline");
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
