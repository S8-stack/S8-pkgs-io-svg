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
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Circle;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;




/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Circle extends SVG_Shape {
	

	
	/**
	 * 
	 * @param front
	 * @param stroke
	 * @param center
	 * @param r
	 * @param isBoundingBoxUpdating
	 * @return
	 */
	public static SVG_Circle createCircle(SVG_Stroke stroke,
			SVG_Vector center, double r, boolean isBoundingBoxUpdating) {
		return createCircle(stroke, center.getX(), center.getY(), r, isBoundingBoxUpdating);
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
	public static SVG_Circle createCircle(SVG_Stroke stroke,
			double xc, double yc, 
			double r,
			boolean isBoundingBoxUpdating) {
		SVG_Circle circle = new SVG_Circle();
		circle.setStroke(stroke);
		circle.setCenter(xc, yc);
		circle.setRadius(r);
		circle.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return circle;
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
	public static SVG_Circle createRound(SVG_Stroke stroke, SVG_Fill fill,
			SVG_Vector center,double r,
			boolean isBoundingBoxUpdating) {
		SVG_Circle shape = new SVG_Circle();
		shape.setStroke(stroke);
		shape.setFill(fill);
		shape.setCenter(center);
		shape.setRadius(r);
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		return shape;
	}

	
	
	



	public @S8Field(name = "center-x") double xCenter;

	public @S8Field(name = "center-y") double yCenter;

	public @S8Field(name = "radius") double radius;

	
	/** S8 constructor */
	public SVG_Circle() { super(); }
	


	public SVG_Circle(String className, SVG_Vector center, double r) {
		super(className);
		this.xCenter = center.getX();
		this.yCenter = center.getY();
		this.radius = r;
	}
	
	
	
	public SVG_Circle(String className, double cx, double cy, double r) {
		super(className);
		this.xCenter = cx;
		this.yCenter = cy;
		this.radius = r;
	}

	
	/**
	 * 
	 * @param point
	 */
	public void setCenter(SVG_Vector point) {
		this.xCenter = point.getX();
		this.yCenter = point.getY();
	}
	
	
	public void setCenter(double x0, double y0) {
		this.xCenter = x0;
		this.yCenter = y0;
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
		WebSVG_Circle circle = new WebSVG_Circle(front);
		circle.setCenter(xCenter, yCenter);
		circle.setRadius(radius);
		
		applyStyle(circle);
		applyProperties(circle);
		
		return circle;
	}
	
	

	



	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		if(isUpdatingBoundingBox) {
			box.update(xCenter - radius, yCenter - radius);
			box.update(xCenter - radius, yCenter + radius);
			box.update(xCenter + radius, yCenter - radius);
			box.update(xCenter + radius, yCenter + radius);
		}
	}


	@Override
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		double adjusted_cx = viewBox.xTransform(xCenter);
		double adjusted_cy = viewBox.yTransform(yCenter);
		double adjusted_r = viewBox.scale(radius);
		// example :  <circle cx="100" cy="50" r="40" class="truc"/>
		writer.append("<circle");
		printAttributes(writer);
		writer.append(" cx=\""+adjusted_cx+"\" cy=\""+adjusted_cy+"\" r=\""+adjusted_r+"\"/>\n");
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		return new SVG_Circle(className, transform.onPoint(xCenter, yCenter), transform.onScalar(radius));
	}


}
