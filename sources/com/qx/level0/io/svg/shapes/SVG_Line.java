package com.qx.level0.io.svg.shapes;

import java.io.IOException;

import com.qx.level0.io.svg.ViewBox;
import com.qx.level0.io.svg.ViewBoxUpdateType;
import com.qx.level0.maths.MathVector2D;


/**
 * 
 * @author pc
 *
 */
public class SVG_Line extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	/**
	 * points of the line
	 */
	protected double x1, y1, x2, y2;




	/**
	 * 
	 */
	public SVG_Line() {
		super();
	}


	public SVG_Line(String className, double x1, double y1, double x2, double y2) {
		super(className);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	public SVG_Line(String className, MathVector2D point0, MathVector2D point1) {
		super(className);
		this.x1 = point0.x;
		this.y1 = point0.y;
		this.x2 = point1.x;
		this.y2 = point1.y;
	}




	public SVG_Line(String className, double[] p1, double[] p2) {
		super(className);
		this.x1 = p1[0]; this.y1 = p1[1];
		this.x2 = p2[0]; this.y2 = p2[1];
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
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(x1);
		double adjusted_y1 = viewBox.yTransform(y1);
		double adjusted_x2 = viewBox.xTransform(x2);
		double adjusted_y2 = viewBox.yTransform(y2);
		
		builder.append("<line");
		printAttributes(builder);
		builder.append(" x1=\""+adjusted_x1+"\" "+
				" y1=\""+adjusted_y1+"\" "+ 
				" x2=\""+adjusted_x2+"\" "+
				" y2=\""+adjusted_y2+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		
		return new SVG_Line(className, 
				transform.transformPoint(new MathVector2D(x1, y1)),
				transform.transformPoint(new MathVector2D(x2, y2)));
	}


	/*
	public void setFirstPoint(double[] p1) {
		this.p1 = new MathVector2d(p1);
	}


	public void setSecondPoint(double[] p2) {
		this.p2 = new MathVector2d(p2);
	}
	*/

}
