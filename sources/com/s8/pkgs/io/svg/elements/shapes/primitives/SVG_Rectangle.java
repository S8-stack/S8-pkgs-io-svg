package com.s8.pkgs.io.svg.elements.shapes.primitives;

import java.io.IOException;

import com.s8.api.annotations.S8Field;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Shape;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.maths.SVG_Vector;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Rectangle;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Rectangle extends SVG_Shape{
	

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
	public static SVG_Rectangle create(SVG_Stroke stroke, SVG_Fill fill,
			double x, double y, double width, double height, 
			boolean isBoundingBoxUpdating) {
		SVG_Rectangle shape = new SVG_Rectangle();
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setTopLeftCorner(x, y);
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}
	
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
	public static SVG_Rectangle createRound(SVG_Stroke stroke, SVG_Fill fill,
			double x, double y, double width, double height, double radius,
			boolean isBoundingBoxUpdating) {
		SVG_Rectangle shape = new SVG_Rectangle();
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setTopLeftCorner(x, y);
		shape.setWidth(width);
		shape.setHeight(height);
		shape.setRadius(radius);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}
	
	
	
	public @S8Field(name = "top-left-corner-x") double xTopLeftCorner;

	public @S8Field(name = "top-left-corner-y") double yTopLeftCorner;

	public @S8Field(name = "width") double width;

	public @S8Field(name = "height") double height;
	
	public @S8Field(name = "corner-radius") double radius = -1;


	
	/** S8 constructor */
	public SVG_Rectangle() { super(); }
	



	public SVG_Rectangle(String className, double x1, double y1, double x2, double y2) {
		super(className);
		this.xTopLeftCorner = x1;
		this.yTopLeftCorner = y1;
		this.width = x2 - x1;
		this.height = y1 - y2;
	}


	public SVG_Rectangle(String className, double[] p1, double[] p2) {
		super(className);
		this.xTopLeftCorner = p1[0];
		this.yTopLeftCorner = p1[1];
		this.width = p2[0] - p1[0];
		this.height = p1[1] - p2[1];
	}




	
	/**
	 * 
	 * @param point
	 */
	public void setTopLeftCorner(SVG_Vector point) {
		xTopLeftCorner = point.getX();
		yTopLeftCorner = point.getY();
	}
	

	public void setTopLeftCorner(float x, float y) {
		xTopLeftCorner = x;
		yTopLeftCorner = y;
	}
	
	
	public void setTopLeftCorner(double x, double y) {
		xTopLeftCorner = x;
		yTopLeftCorner = y;
	}
	
	
	/**
	 * 
	 * @param value
	 */
	public void setWidth(float value) {
		this.width = value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setWidth(double value) {
		this.width = value;
	}
	
	
	/**
	 * 
	 * @param value
	 */
	public void setHeight(float value) {
		this.height = value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setHeight(double value) {
		this.height = value;
	}
	
	
	/**
	 * 
	 * @param radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public WebSVG_Shape createWeb(S8WebFront front) {
		WebSVG_Rectangle rectangle = new WebSVG_Rectangle(front);
		rectangle.setTopLeftCorner(xTopLeftCorner, yTopLeftCorner);
		rectangle.setWidth(width);
		rectangle.setHeight(height);
		if(radius > 0) { rectangle.setRadius(radius); }
		
		applyStyle(rectangle);
		applyProperties(rectangle);
		
		return rectangle;
	}
	

	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		if(isUpdatingBoundingBox) {
			box.update(xTopLeftCorner, yTopLeftCorner);
			box.update(xTopLeftCorner + width, yTopLeftCorner - height);
		}
	}


	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		double adjusted_x1 = viewBox.xTransform(xTopLeftCorner);
		double adjusted_y1 = viewBox.yTransform(yTopLeftCorner);

		double adjusted_x2 = viewBox.xTransform(xTopLeftCorner + width);
		double adjusted_y2 = viewBox.yTransform(yTopLeftCorner - height);

		double x = adjusted_x1;
		double y = Math.min(adjusted_y1, adjusted_y2);
		double width = Math.abs(adjusted_x2-adjusted_x1);
		double height = Math.abs(adjusted_y2-adjusted_y1);

		builder.append("<rect");
		printAttributes(builder);
		builder.append(" x=\""+x+"\" y=\""+y+"\" width=\""+width+"\" height=\""+height+"\"/>\n");
	}


	public void setFirstPoint(double[] p1) {
		this.xTopLeftCorner = p1[0];
		this.yTopLeftCorner = p1[1];
	}



	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		throw new RuntimeException("Not implemented yet");
	}


}
