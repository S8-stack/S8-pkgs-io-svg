package com.qx.level0.io.svg;

import java.io.IOException;
import java.io.StringWriter;

public abstract class SVG_Shape {

	/**
	 * inline style definition
	 */
	public String style;

	/**
	 * class reference for style definition
	 */
	public String styleClass;

	
	public SVG_Shape() {
	}


	/**
	 * @param styleClass
	 */
	public SVG_Shape(String styleClass) {
		super();
		this.styleClass = styleClass;
	}
	
	/**
	 * @param style
	 */
	public SVG_Shape(String styleClass, String style) {
		super();
		this.styleClass = styleClass;
		this.style = style;
	}

	public abstract void updateBoundingBox(ViewBox viewBox);


	public abstract void print(StringWriter writer, ViewBox viewBox) throws IOException;



	/**
	 * @return the style
	 */
	public String getStyleClass() {
		return styleClass;
	}


	/**
	 * @param styleClass the style to set
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	public String fm(double value){
		//return FORMAT.format(value);
		return String.valueOf((int) value);
	}
	

	protected void printStyleBlock(StringWriter writer) {
		if(styleClass!=null){
			writer.append(" class=\""+styleClass+"\"");
		}
		if(style!=null){
			writer.append(" style=\""+style+"\"");
		}
	}




}
