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
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.styles.SVG_Style;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Polygon;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;



/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Polygon extends SVG_Shape {
	
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
	public static SVG_Polygon create(SVG_Style style, double[] coordinates, boolean isBoundingBoxUpdating) {
		SVG_Polygon polygon = new SVG_Polygon(style);
		polygon.setCoordinates(coordinates);
		polygon.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return polygon;
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
	public static SVG_Polygon create(SVG_Stroke stroke, SVG_Fill fill, double[] coordinates, boolean isBoundingBoxUpdating) {
		SVG_Polygon polygon = new SVG_Polygon();
		polygon.setStroke(stroke);
		polygon.setFill(fill);
		polygon.setCoordinates(coordinates);
		polygon.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return polygon;
	}
	
	
	/**
	 * 
	 * @param branch
	 * @param coordinates
	 * @return
	 */
	public static SVG_Polygon create(S8WebFront branch, double[] coordinates) {
		SVG_Polygon line = new SVG_Polygon();
		line.setCoordinates(coordinates);
		return line;
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
	public static SVG_Polygon triangle(SVG_Style style, double x0, double y0, double x1, double y1, double x2, double y2) {
		SVG_Polygon polygon = new SVG_Polygon();
		polygon.setStyle(style);
		polygon.setCoordinates(new double[] { x0, y0, x1, y1, x2, y2});
		polygon.setBoundingBoxRelevant(true);
		return polygon;
	}
	
	
	
	
	/**
	 * following order: {x0, y0, x1, y1, ... , x[n-1], y[n-1]}
	 */
	public @S8Field(name = "coordinates") double[] coordinates;
	
	
	
	/** S8 constructor */
	public SVG_Polygon() { super(); }
	
	/** default */
	public SVG_Polygon(SVG_Style style) { super(style); }
	

	public SVG_Polygon(String className, double[] coordinates){
		super(className);
		this.coordinates = coordinates;
	}

	public SVG_Polygon(String className, SVG_Vector[] points){
		super(className);
		setCoordinates(points);
	}

	

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
			coordinates[2*i + 0] = point.getX();
			coordinates[2*i + 1] = point.getY();
		}
		this.coordinates = coordinates;
	}

	
	@Override
	public WebSVG_Shape createWeb(S8WebFront front) {
		WebSVG_Polygon polygon = new WebSVG_Polygon(front);
		polygon.setCoordinates(coordinates);
		
		applyStyle(polygon);
		applyProperties(polygon);
		return polygon;
	}




	/*
	public SVG_Polygon(String style, double[][] polygon){
		super(style);

		int n = polygon.length;
		this.points = new MathVector2d[n];

		for(int i=0; i<n; i++){
			points[i] = new MathVector2d(polygon[i][0], polygon[i][1]);
		}
	}
	 */

	
	/**
	 * 
	 * @param style
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public SVG_Polygon(String style, double x0, double y0, double x1, double y1, double x2, double y2) {
		super(style);
		setCoordinates(new SVG_Vector[] { new SVG_BaseVector(x0, y0), new SVG_BaseVector(x1, y1), new SVG_BaseVector(x2, y2)});
	}

	
	/**
	 * 
	 * @param style
	 * @param p0
	 * @param p1
	 * @param p2
	 */
	public SVG_Polygon(String style, SVG_Vector p0, SVG_Vector p1, SVG_Vector p2) {
		super(style);
		setCoordinates(new SVG_Vector[] { p0, p1, p2});
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


	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<polygon");
		printAttributes(builder);
		builder.append(" points=\"");
		
		// v
		int nVertices = coordinates.length / 2;
		int offset = 0;
		for(int i=0; i<nVertices; i++){
			builder.append(viewBox.xTransform(coordinates[offset + 0])+",");
			builder.append(viewBox.yTransform(coordinates[offset + 1])+" ");
			offset+=2;
		}
		builder.append("\"/>\n");
	}




	/**
	 * build rectangle!!
	 * @param style
	 * @param x0
	 * @param x1
	 * @param y0
	 * @param y1
	 * @return
	 */
	public static SVG_Polygon rectangle(String style, double x0, double x1, double y0, double y1){
		double[] coordinates = new double[]{ x0, y0, x1, y0, x1, y1, x0, y1};
		return new SVG_Polygon(style, coordinates);
	}



	@Override
	public SVG_Polygon rewrite(SVG_Rewriter transform) {
		int nVertices = coordinates.length / 2;
		SVG_Vector[] transformedVertices = new SVG_Vector[2*nVertices];
		
		int offset = 0;
		for(int i=0; i<nVertices; i++){
			transformedVertices[i] = transform.onPoint(new SVG_BaseVector(
					coordinates[offset + 0], 
					coordinates[offset + 1]));
		}
		return new SVG_Polygon(className, transformedVertices);
	}


	
	
	public static SVG_Polygon triangle(String styleClass, SVG_Vector p0, SVG_Vector p1, SVG_Vector p2) {
		return new SVG_Polygon(styleClass, p0, p1, p2);
	}


}
