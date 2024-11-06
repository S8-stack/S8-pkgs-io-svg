package com.s8.pkgs.io.svg.elements.shapes.primitives;

import java.io.IOException;

import com.s8.api.annotations.S8Field;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Shape;
import com.s8.pkgs.io.svg.maths.SVG_BaseVector;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Polyline;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;



/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Polyline extends SVG_Shape {


	/**
	 * 
	 * @param branch
	 * @param stroke
	 * @param coordinates
	 * @param isBoundingBoxUpdating
	 * @return
	 */
	public static SVG_Polyline create(S8WebFront branch, SVG_Stroke stroke, double[] coordinates, boolean isBoundingBoxUpdating) {
		SVG_Polyline polyline = new SVG_Polyline();
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
	public static SVG_Polyline create(S8WebFront branch, SVG_Stroke stroke, SVG_Vector[] points, boolean isBoundingBoxUpdating) {
		SVG_Polyline polyline = new SVG_Polyline();
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
	public static SVG_Polyline create(S8WebFront branch, double[] coordinates) {
		SVG_Polyline line = new SVG_Polyline();
		line.setCoordinates(coordinates);
		return line;
	}
	
	
	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 */
	public @S8Field(name = "coordinates") double[] coordinates;
	
	
	private boolean isPointsVisible;
	
	private double pointsRadius;
	
	
	/** S8 constructor */
	public SVG_Polyline() { super(); }
	

	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 * @param coordinates
	 */
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 * @param coordinates
	 */
	public void setCoordinates(SVG_Vector[] points) {
		int nPoints = points.length;
		double[] coordinates = new double[2*nPoints];
		for(int i = 0; i<nPoints; i++) {
			SVG_Vector point = points[i];
			coordinates[2*i + 0] = (float) point.getX();
			coordinates[2*i + 1] = (float) point.getY();
		}
		this.coordinates = coordinates;
	}

	@Override
	public WebSVG_Shape createWeb(S8WebFront front) {
		WebSVG_Polyline polyline = new WebSVG_Polyline(front);
		polyline.setCoordinates(coordinates);
		
		applyStyle(polyline);
		applyProperties(polyline);
		return polyline;
	}
	

	
	/**
	 * 
	 * @param className
	 * @param coordinates
	 */
	public SVG_Polyline(String className, double[] coordinates){
		super(className);
		this.coordinates = coordinates;
	}
	
	
	public SVG_Vector[] getPoints() {
		int nVertices = coordinates.length/2;
		SVG_Vector[] vertices = new SVG_Vector[nVertices];
		int i=0;
		for(int index=0; index<nVertices; index++) {
			vertices[index] = new SVG_BaseVector(coordinates[i++], coordinates[i++]);
		}
		return vertices;
	}
	
	
	/**
	 * 
	 * @param className
	 * @param points
	 */
	public SVG_Polyline(String className, SVG_Vector[] points){
		super(className);
		setCoordinates(points);
	}
	

	/**
	 * 
	 * @param className
	 * @param coordinates
	 * @param pointsRadius
	 */
	public SVG_Polyline(String className, double[] coordinates, double pointsRadius){
		super(className);
		this.coordinates = coordinates;
		this.isPointsVisible = true;
		this.pointsRadius = pointsRadius;
	}
	
	
	/**
	 * 
	 * @param className
	 * @param points
	 * @param pointsRadius
	 */
	public SVG_Polyline(String className, SVG_Vector[] points, double pointsRadius){
		super(className);
		setCoordinates(coordinates);
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
	}

	


	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		if(isUpdatingBoundingBox) {
			int nVertices = coordinates.length / 2;
			int offset = 0;
			for(int i=0; i<nVertices; i++){
				box.update(coordinates[offset + 0], coordinates[offset + 1]);
				offset+=2;
			}
		}
	}





	/**
	 *  <polyline points="20,20 40,25 60,40 80,120 120,140 200,180" class="er" />
	 * @param builder
	 * @throws IOException
	 */
	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<polyline");
		printAttributes(builder);
		builder.append(" points=\"");
		
		double x, y;
		
		int nVertices = coordinates.length / 2;
		int offset = 0;
		for(int i=0; i<nVertices; i++) {
			x = viewBox.xTransform(coordinates[offset + 0]);
			y = viewBox.yTransform(coordinates[offset + 1]);
			offset += 2;
			builder.append(x+",");
			builder.append(y+" ");
		}
		builder.append("\"/>\n");

		if(isPointsVisible){
			offset = 0;
			for(int i=0; i<nVertices; i++){
				x = viewBox.xTransform(coordinates[offset + 0]);
				y = viewBox.yTransform(coordinates[offset + 1]);
				offset += 2;
				new SVG_Circle(className, x, y, pointsRadius).print(builder, viewBox);
			}
		}
	}

	@Override
	public SVG_Polyline rewrite(SVG_Rewriter transform) {
		int nVertices = coordinates.length / 2;
		SVG_Vector[] transformedVertices = new SVG_Vector[nVertices];
		int offset = 0;
		for(int i=0; i<nVertices; i++) {
			transformedVertices[i] = transform.onPoint(new SVG_BaseVector(coordinates[offset + 0], coordinates[offset + 1]));
			offset += 2;
		}
		return new SVG_Polyline(className, transformedVertices, pointsRadius);
	}
}
