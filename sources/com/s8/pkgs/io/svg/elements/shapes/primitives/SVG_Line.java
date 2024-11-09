package com.s8.pkgs.io.svg.elements.shapes.primitives;

import java.io.IOException;

import com.s8.api.annotations.S8Field;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Shape;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Line;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Line extends SVG_Shape{
	

	/**
	 * 
	 * @param branch
	 * @param color
	 * @param solidity
	 * @param thickness
	 * @param xc
	 * @param yc
	 * @param r
	 * @return
	 */
	public static SVG_Line create(SVG_Stroke stroke,
			double x0, double y0, double x1, double y1, boolean isBoundingBoxUpdating) {
		SVG_Line line = new SVG_Line();
		line.setStroke(stroke);
		line.setCoordinates(x0, y0, x1, y1);
		line.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return line;
	}
	
	public static SVG_Line create(SVG_Stroke stroke, double x0, double y0, double x1, double y1) {
		return create(stroke, x0, y0, x1, y1, true);
	}
	
	public static SVG_Line create(SVG_Stroke stroke,
			SVG_Vector p0, SVG_Vector p1, boolean isBoundingBoxUpdating) {
		return create(stroke, p0.getX(), p0.getY(), p1.getX(), p1.getY(), isBoundingBoxUpdating);
	}
	


	public static SVG_Line create(double x0, double y0, double x1, double y1) {
		SVG_Line line = new SVG_Line();
		line.setCoordinates(x0, y0, x1, y1);
		return line;
	}
	
	
	public @S8Field(name = "x0") double x0;
	
	public @S8Field(name = "y0") double y0;
	
	public @S8Field(name = "x1") double x1;
	
	public @S8Field(name = "y1") double y1;
	
	
	
	/** S8 constructor */
	public SVG_Line() { super(); }
	

	public SVG_Line(String className, double x1, double y1, double x2, double y2) {
		super(className);
		this.x0 = x1;
		this.y0 = y1;
		this.x1 = x2;
		this.y1 = y2;
	}
	
	
	public SVG_Line(String className, SVG_Vector point0, SVG_Vector point1) {
		super(className);
		this.x0 = point0.getX();
		this.y0 = point0.getY();
		this.x1 = point1.getX();
		this.y1 = point1.getY();
	}



	public SVG_Line(String className, double[] p1, double[] p2) {
		super(className);
		this.x0 = p1[0]; this.y0 = p1[1];
		this.x1 = p2[0]; this.y1 = p2[1];
	}

	
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(double x0, double y0, double x1, double y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		
	}
	
	/**
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public void setCoordinates(SVG_Vector p0, SVG_Vector p1) {
		this.x0 = p0.getX();
		this.y0 = p0.getY();
		this.x1 = p1.getX();
		this.y1 = p1.getY();
	}
	
	

	/**
	 * following order: {x0, y0, x1, y1}
	 * @param coordinates
	 */
	public void setCoordinates(double[] coordinates) {
		this.x0 = coordinates[0];
		this.y0 = coordinates[1];
		this.x1 = coordinates[2];
		this.y1 = coordinates[3];
	}
	

	@Override
	public WebSVG_Shape createWeb(S8WebFront front) {
		WebSVG_Line line = new WebSVG_Line(front);
		line.setCoordinates(x0, y0, x1, y1);
		
		applyStyle(line);
		applyProperties(line);
		return line;
	}




	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		if(isUpdatingBoundingBox) {
			box.update(x0, y0);
			box.update(x1, y1);
		}
	}


	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(x0);
		double adjusted_y1 = viewBox.yTransform(y0);
		double adjusted_x2 = viewBox.xTransform(x1);
		double adjusted_y2 = viewBox.yTransform(y1);
		
		builder.append("<line");
		printAttributes(builder);
		builder.append(" x1=\""+adjusted_x1+"\" "+
				" y1=\""+adjusted_y1+"\" "+ 
				" x2=\""+adjusted_x2+"\" "+
				" y2=\""+adjusted_y2+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		
		return new SVG_Line(className, transform.onPoint(x0, y0), transform.onPoint(x1, y1));
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
