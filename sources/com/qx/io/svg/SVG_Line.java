package com.qx.io.svg;

import java.io.IOException;
import java.io.StringWriter;


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


	public SVG_Line(String style, double x1, double y1, double x2, double y2) {
		super(style);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}




	public SVG_Line(String style, double[] p1, double[] p2) {
		super(style);
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
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(x1);
		double adjusted_y1 = viewBox.yTransform(y1);
		double adjusted_x2 = viewBox.xTransform(x2);
		double adjusted_y2 = viewBox.yTransform(y2);
		
		writer.append("<line");
		printStyleBlock(writer);
		writer.append(" x1=\""+adjusted_x1+"\" "+
				" y1=\""+adjusted_y1+"\" "+ 
				" x2=\""+adjusted_x2+"\" "+
				" y2=\""+adjusted_y2+"\"/>\n");
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
