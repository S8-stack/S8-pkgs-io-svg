package com.s8.pkgs.io.svg.elements.shapes;

import com.s8.api.annotations.S8Field;
import com.s8.api.annotations.S8ObjectType;
import com.s8.pkgs.io.svg.elements.SVG_Element;
import com.s8.pkgs.io.svg.elements.shapes.path.SVG_Path;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Circle;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Line;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Polygon;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Polyline;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Rectangle;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_FillColor;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.styles.SVG_StrokeColor;
import com.s8.pkgs.io.svg.styles.SVG_StrokeSolidity;
import com.s8.pkgs.io.svg.styles.SVG_StrokeThickness;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;

/**
 * 
 * @author pierreconvert
 *
 */
/**
 * 
 */
@S8ObjectType(name = "com.s8.pkgs.ui.websvg.model.shapes.WebSVG_ShapeModel", sub = {
		SVG_Circle.class,
		SVG_Line.class,
		SVG_Path.class,
		SVG_Polygon.class,
		SVG_Polyline.class,
		SVG_Rectangle.class
})
public abstract class SVG_Shape extends SVG_Element {
	

	/**
	 * inline style definition
	 */
	public String style;

	/**
	 * class reference for style definition
	 */
	public String className;
	
	
	
	/**
	 * 
	 */
	private String transform;

	/**
	 * 
	 * @param solidity
	 */
	@S8Field(name = "stroke-solidity")
	public SVG_StrokeSolidity strokeSolidity = SVG_StrokeSolidity.SOLID;
	
	
	@S8Field(name = "stroke-color")
	public SVG_StrokeColor strokeColor = SVG_StrokeColor.NONE;
	
	
	@S8Field(name = "stroke-thickness")
	public SVG_StrokeThickness strokeThickness = SVG_StrokeThickness.ONE;
	

	@S8Field(name = "fill-color")
	public SVG_FillColor fillColor = SVG_FillColor.NONE;

	
	/** S8 constructor */
	public SVG_Shape() { super(); }
	
	
	
	
	/**
	 * 
	 * @param stroke
	 */
	public void setStroke(SVG_Stroke stroke) {
		setStrokeSolidity(stroke.solidity);
		setStrokeThickness(stroke.thickness);
		setStrokeColor(stroke.color);
	}
	
	
	/**
	 * Define Stroke
	 * @param solidity
	 * @param thickness
	 * @param color
	 */
	public void setStroke(SVG_StrokeSolidity solidity, SVG_StrokeThickness thickness, SVG_StrokeColor color) {
		setStrokeSolidity(solidity);
		setStrokeThickness(thickness);
		setStrokeColor(color);
	}
	
	
	/**
	 * 
	 * @param solidity
	 */
	public void setStrokeSolidity(SVG_StrokeSolidity solidity) {
		this.strokeSolidity = solidity;
	}
	
	
	/**
	 * 
	 * @param color
	 */
	public void setStrokeColor(SVG_StrokeColor color) {
		this.strokeColor = color;
	}
	
	
	/**
	 * Style: 
	 * @param thickness
	 */
	public void setStrokeThickness(SVG_StrokeThickness thickness) {
		this.strokeThickness = thickness;
	}
	
	

	public void setFill(SVG_Fill fill) {
		setFillColor(fill.color);
	}


	
	/**
	 * 
	 * @param stroke
	 */
	public void setFillColor(SVG_FillColor color) {
		this.fillColor = color;
	}
	
	
	
	protected void applyStyle(WebSVG_Shape shape) {
		shape.setStroke(strokeSolidity, strokeThickness, strokeColor);
		shape.setFillColor(fillColor);
	}

	
	/**
	 * @param className
	 */
	public SVG_Shape(String className) {
		super();
		this.className = className;
	}
	
	
	public void setStyle(String style) {
		this.style = style;
	}
	
	
	public void appendTransform(String transform) {
		if(transform!=null) {
			if(this.transform!=null) {
				this.transform = this.transform+' '+transform;
			}
			else {
				this.transform = transform;	
			}	
		}
	}


	

	/**
	 * @return the style
	 */
	public String getStyleClass() {
		return className;
	}


	/**
	 * @param classname the style to set
	 */
	public void setClassName(String classname) {
		this.className = classname;
	}


	/**
	 * static helper
	 * @param value
	 * @return
	 */
	public static String fm(double value){
		//return FORMAT.format(value);
		return String.valueOf((int) value);
	}
	

	protected void printAttributes(StringBuilder builder) {
		if(className!=null){
			builder.append(" class=\""+className+"\"");
		}
		
		if(strokeColor != SVG_StrokeColor.NONE) {
			builder.append(" stroke=\""+strokeColor.getValue()+"\"");	
			
			builder.append(" stroke-dasharray=\""+strokeSolidity.getValue()+"\"");

			builder.append(" stroke-width=\""+strokeThickness.getValue()+"\"");			
		}
		
		if(fillColor != SVG_FillColor.NONE) {
			builder.append(" fill=\""+fillColor.getValue()+"\"");		
		}	
		
		if(transform!=null){
			builder.append(" transform=\""+transform+"\"");
		}
	}

}
