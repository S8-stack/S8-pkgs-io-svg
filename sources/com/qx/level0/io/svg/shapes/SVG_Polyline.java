package com.qx.level0.io.svg.shapes;

import java.io.IOException;

import com.qx.level0.io.svg.ViewBox;
import com.qx.level0.io.svg.ViewBoxUpdateType;
import com.qx.level0.maths.MathVector2d;



public class SVG_Polyline extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	private boolean isPointsVisible;
	
	private int nPoints;

	private double pointsRadius;

	private double[] coordinates;


	/**
	 * 
	 */
	public SVG_Polyline() {
		super();
	}

	public SVG_Polyline(String className, double[] coordinates){
		super(className);
		this.coordinates = coordinates;
		this.nPoints = coordinates.length/2;
	}
	
	public SVG_Polyline(String className, MathVector2d[] points){
		super(className);
		this.coordinates = SVG_Polygon.toCoordinates(points);
		this.nPoints = points.length;
	}
	

	public SVG_Polyline(String className, double[] coordinates, double pointsRadius){
		super(className);
		this.coordinates = coordinates;
		this.nPoints = coordinates.length/2;
		isPointsVisible = true;
		this.pointsRadius = pointsRadius;
	}
	
	public SVG_Polyline(String className, MathVector2d[] points, double pointsRadius){
		super(className);
		this.coordinates = SVG_Polygon.toCoordinates(points);
		this.nPoints = points.length/2;
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
	 * @param builder
	 * @throws IOException
	 */
	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<polyline");
		printAttributes(builder);
		builder.append(" points=\"");
		int n = coordinates.length/2;
		double x, y;
		for(int i=0; i<n; i++){
			x = viewBox.xTransform(coordinates[2*i+0]);
			y = viewBox.yTransform(coordinates[2*i+1]);
			builder.append(x+",");
			builder.append(y+" ");
		}
		builder.append("\"/>\n");

		if(isPointsVisible){
			for(int i=0; i<n; i++){
				x = viewBox.xTransform(coordinates[2*i+0]);
				y = viewBox.yTransform(coordinates[2*i+1]);
				new SVG_Circle(className, x, y, pointsRadius).print(builder, viewBox);
			}
		}
	}

	@Override
	public SVG_Polyline rewrite(SVG_Rewriter transform) {
		double[] transformedCoordinates = new double[2*nPoints];
		int n = coordinates.length;
		MathVector2d transformedPoint;
		for(int i=0; i<n; i++) {
			transformedPoint = transform.transformPoint(new MathVector2d(coordinates[i], coordinates[i+1]));
			transformedCoordinates[i] = transformedPoint.x;
			transformedCoordinates[i+1] = transformedPoint.y;
		}
		return new SVG_Polyline(className, transformedCoordinates, pointsRadius);
	}
}
