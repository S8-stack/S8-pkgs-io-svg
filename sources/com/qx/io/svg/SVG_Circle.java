package com.qx.io.svg;

import java.io.IOException;
import java.io.StringWriter;






public class SVG_Circle extends SVG_Shape{
	
	public ViewBoxUpdateType type = ViewBoxUpdateType.Contained;
	
	protected double xCenter, yCenter;

	protected double r;

	/**
	 * 
	 */
	public SVG_Circle() {
		super();
	}


	public SVG_Circle(String style, double cx, double cy, double r) {
		super(style);
		this.xCenter = cx;
		this.yCenter = cy;
		this.r = r;
	}

	


	@Override
	public void updateBoundingBox(ViewBox viewBox){
		switch(type){

		case Center : 
			viewBox.updateBoundingBox(xCenter, yCenter);
			break;

		case Contained : 
			viewBox.updateBoundingBox(xCenter-r, yCenter-r);
			viewBox.updateBoundingBox(xCenter-r, yCenter+r);
			viewBox.updateBoundingBox(xCenter+r, yCenter-r);
			viewBox.updateBoundingBox(xCenter+r, yCenter+r);
			break;

		default :
			break;

		}
	}


	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		double adjusted_cx = viewBox.xTransform(xCenter);
		double adjusted_cy = viewBox.yTransform(yCenter);
		double adjusted_r = viewBox.scale(r);
		// example :  <circle cx="100" cy="50" r="40" class="truc"/>
		writer.append("<circle");
		printStyleBlock(writer);
		writer.append(" cx=\""+adjusted_cx+"\" cy=\""+adjusted_cy+"\" r=\""+adjusted_r+"\"/>\n");
	}


}
