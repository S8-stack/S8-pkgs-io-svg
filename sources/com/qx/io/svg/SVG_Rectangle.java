package com.qx.io.svg;

import java.io.IOException;
import java.io.StringWriter;


public class SVG_Rectangle extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	protected double x1;

	protected double y1;

	protected double x2;

	protected double y2;






	/**
	 * 
	 */
	public SVG_Rectangle() {
		super();
	}


	public SVG_Rectangle(String styleClass, double x1, double y1, double x2, double y2) {
		super(styleClass);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public SVG_Rectangle(String styleClass, String style, double x1, double y1, double x2, double y2) {
		super(styleClass, style);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}


	public SVG_Rectangle(String style, double[] p1, double[] p2) {
		super(style);
		this.x1 = p1[0];
		this.y1 = p1[1];
		this.x2 = p2[0];
		this.y2 = p2[1];
	}



	@Override
	public void updateBoundingBox(ViewBox viewBox){
		switch(updateType){
		case Contained :
			viewBox.updateBoundingBox(x1, y1);
			viewBox.updateBoundingBox(x2, y2);
			break;

		default: break;
		}
	}


	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(x1);
		double adjusted_y1 = viewBox.yTransform(y1);

		double adjusted_x2 = viewBox.xTransform(x2);
		double adjusted_y2 = viewBox.yTransform(y2);

		double x = adjusted_x1;
		double y = Math.min(adjusted_y1, adjusted_y2);
		double width = Math.abs(adjusted_x2-adjusted_x1);
		double height = Math.abs(adjusted_y2-adjusted_y1);

		writer.append("<rect");
		printStyleBlock(writer);
		writer.append(" x=\""+x+"\" y=\""+y+"\" width=\""+width+"\" height=\""+height+"\"/>\n");
	}


	public void setFirstPoint(double[] p1) {
		this.x1 = p1[0];
		this.y1 = p1[1];
	}


	public void setSecondPoint(double[] p2) {
		this.x2 = p2[0];
		this.y2 = p2[1];
	}


}
