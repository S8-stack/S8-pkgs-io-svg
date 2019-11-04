package com.qx.level0.io.svg;

import java.io.IOException;
import java.io.StringWriter;

import com.qx.level0.maths.MathVector2D;



public class SVG_PolyLine extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	private boolean isPointsVisible;

	private double pointsRadius;

	private double[] coordinates;


	/**
	 * 
	 */
	public SVG_PolyLine() {
		super();
	}

	public SVG_PolyLine(String styleClass, double[] coordinates){
		super(styleClass);
		this.coordinates = coordinates;
	}
	
	public SVG_PolyLine(String styleClass, MathVector2D[] points){
		super(styleClass);
		this.coordinates = SVG_Polygon.toCoordinates(points);
	}
	
	public SVG_PolyLine(String styleClass, String style, double[] coordinates){
		super(styleClass, style);
		this.coordinates = coordinates;
	}
	
	public SVG_PolyLine(String styleClass, String style, MathVector2D[] points){
		super(styleClass, style);
		this.coordinates = SVG_Polygon.toCoordinates(points);
	}

	public SVG_PolyLine(String styleClass, double[] coordinates, double pointsRadius){
		super(styleClass);
		this.coordinates = coordinates;
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
	}
	
	public SVG_PolyLine(String styleClass, MathVector2D[] points, double pointsRadius){
		super(styleClass);
		this.coordinates = SVG_Polygon.toCoordinates(points);
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
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





	/**
	 *  <polyline points="20,20 40,25 60,40 80,120 120,140 200,180" class="er" />
	 * @param writer
	 * @throws IOException
	 */
	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		writer.append("<polyline");
		printStyleBlock(writer);
		writer.append(" points=\"");
		int n = coordinates.length/2;
		double x, y;
		for(int i=0; i<n; i++){
			x = viewBox.xTransform(coordinates[2*i+0]);
			y = viewBox.yTransform(coordinates[2*i+1]);
			writer.append(x+",");
			writer.append(y+" ");
		}
		writer.append("\"/>\n");

		if(isPointsVisible){
			for(int i=0; i<n; i++){
				x = viewBox.xTransform(coordinates[2*i+0]);
				y = viewBox.yTransform(coordinates[2*i+1]);
				new SVG_Circle(styleClass, x, y, pointsRadius).print(writer, viewBox);
			}
		}
	}
}
