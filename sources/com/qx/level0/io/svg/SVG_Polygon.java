package com.qx.level0.io.svg;


import java.io.IOException;
import java.io.StringWriter;

import com.qx.level0.maths.MathVector2D;






public class SVG_Polygon extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	/**
	 * x0, y0, x1, y1, x2, y2, ...
	 */
	private double[] coordinates;




	/**
	 * 
	 */
	public SVG_Polygon() {
		super();
	}



	public SVG_Polygon(String styleClass, double[] coordinates){
		super(styleClass);
		this.coordinates = coordinates;
	}
	
	public SVG_Polygon(String styleClass, MathVector2D[] points){
		super(styleClass);
		this.coordinates = toCoordinates(points);
	}
	
	public SVG_Polygon(String styleClass, String style, double[] coordinates){
		super(styleClass, style);
		this.coordinates = coordinates;
	}
	
	public SVG_Polygon(String styleClass, String style, MathVector2D[] points){
		super(styleClass, style);
		this.coordinates = toCoordinates(points);
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

	public SVG_Polygon(String style, double x0, double y0, double x1, double y1, double x2, double y2) {
		super(style);
		coordinates = new double[6];
		coordinates[0] = x0; coordinates[1] = y0;
		coordinates[2] = x1; coordinates[3] = y1;
		coordinates[4] = x2; coordinates[5] = y2;
	}
	
	public SVG_Polygon(String style, MathVector2D p0, MathVector2D p1, MathVector2D p2) {
		super(style);
		coordinates = new double[6];
		coordinates[0] = p0.x; coordinates[1] = p0.y;
		coordinates[2] = p1.x; coordinates[3] = p1.y;
		coordinates[4] = p2.x; coordinates[5] = p2.y;
	}



	@Override
	public void updateBoundingBox(ViewBox viewBox){

		switch(updateType){
		case Contained :
			int n = coordinates.length/2;
			for(int i=0; i<n; i++){
				viewBox.updateBoundingBox(coordinates[2*i+0], coordinates[2*i+1]);	
			}
			break;
		default : break;
		}

	}


	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		writer.append("<polygon");
		printStyleBlock(writer);
		writer.append(" points=\"");
		int n = coordinates.length/2;
		int index=0;
		for(int i=0; i<n; i++){
			writer.append(viewBox.xTransform(coordinates[index++])+",");
			writer.append(viewBox.yTransform(coordinates[index++])+" ");
		}
		writer.append("\"/>\n");
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


	public static double[] toCoordinates(MathVector2D[] points) {
		int n = points.length;
		double[] coordinates = new double[2*n];
		int index=0;
		MathVector2D point;
		for(int i=0; i<n; i++) {
			point = points[i];
			coordinates[index++] = point.x;
			coordinates[index++] = point.y;
		}
		return coordinates;
	}

}
