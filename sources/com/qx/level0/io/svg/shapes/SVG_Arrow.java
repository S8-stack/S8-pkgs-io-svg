package com.qx.level0.io.svg.shapes;



import java.io.IOException;

import com.qx.level0.io.svg.ViewBox;
import com.qx.level0.io.svg.ViewBoxUpdateType;
import com.qx.level0.maths.MathVector2D;





public class SVG_Arrow extends SVG_Shape{

	public ViewBoxUpdateType updateType = ViewBoxUpdateType.Contained;


	protected double x1;

	protected double y1;

	protected double x2;

	protected double y2;

	/**
	 * 
	 */
	public SVG_Arrow() {
		super();
	}


	public SVG_Arrow(String style, MathVector2D point0, MathVector2D point1) {
		super(style);
		this.x1 = point0.x;
		this.y1 = point0.y;
		this.x2 = point1.x;
		this.y2 = point1.y;
	}

	public SVG_Arrow(String style, double x1, double y1, double x2, double y2) {
		super(style);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
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
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		/*	
		double adjusted_x1 = viewBox.transformXCoordinate(x1);
		double adjusted_y1 = viewBox.transformYCoordinate(y1);

		double adjusted_x2 = viewBox.transformXCoordinate(x2);
		double adjusted_y2 = viewBox.transformYCoordinate(y2);

		 */

	}


	
	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		throw new RuntimeException("Not implemented yet");
	}
}
