package com.qx.level0.io.svg.shapes;

import java.io.IOException;

import com.qx.level0.io.svg.ViewBox;
import com.qx.level0.io.svg.ViewBoxUpdateType;
import com.qx.level0.maths.MathVector2d;






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


	public SVG_Circle(String className, MathVector2d center, double r) {
		super(className);
		this.xCenter = center.x;
		this.yCenter = center.y;
		this.r = r;
	}
	
	
	public SVG_Circle(String className, double cx, double cy, double r) {
		super(className);
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
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		double adjusted_cx = viewBox.xTransform(xCenter);
		double adjusted_cy = viewBox.yTransform(yCenter);
		double adjusted_r = viewBox.scale(r);
		// example :  <circle cx="100" cy="50" r="40" class="truc"/>
		writer.append("<circle");
		printAttributes(writer);
		writer.append(" cx=\""+adjusted_cx+"\" cy=\""+adjusted_cy+"\" r=\""+adjusted_r+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		return new SVG_Circle(className, 
				transform.transformPoint(new MathVector2d(xCenter, yCenter)), 
				transform.transformScalar(r));
	}


}
