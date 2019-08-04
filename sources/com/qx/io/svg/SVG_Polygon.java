package com.qx.io.svg;


import java.io.IOException;
import java.io.StringWriter;






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
	
	public SVG_Polygon(String styleClass, String style, double[] coordinates){
		super(styleClass, style);
		this.coordinates = coordinates;
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
		coordinates = new double[0];
		coordinates[0] = x0; coordinates[1] = y0;
		coordinates[2] = x1; coordinates[3] = y1;
		coordinates[4] = x2; coordinates[5] = y2;
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
		int n = coordinates.length;
		for(int i=0; i<n; i++){
			writer.append(viewBox.xTransform(coordinates[2*i+0])+",");
			writer.append(viewBox.yTransform(coordinates[2*i+1])+" ");
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


}
