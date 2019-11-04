package com.qx.level0.io.svg;

import java.io.IOException;
import java.io.StringWriter;

import com.qx.level0.maths.MathVector2D;

public class SVG_Text extends SVG_Shape {

	

	public ViewBoxUpdateType type = ViewBoxUpdateType.Contained;
	
	protected double x;

	protected double y;
	
	protected String text;

	/**
	 * 
	 */
	public SVG_Text() {
		super();
	}


	public SVG_Text(String styleClass, double x, double y, String text) {
		super(styleClass);
		this.x = x;
		this.y = y;
		this.text=text;
	}
	
	public SVG_Text(String styleClass, String style, double x, double y,String text) {
		super(styleClass, style);
		this.x = x;
		this.y = y;
		this.text=text;
	}
	
	public SVG_Text(String styleClass, MathVector2D point, String text) {
		super(styleClass);
		this.x = point.x;
		this.y = point.y;
		this.text=text;
	}


	@Override
	public void updateBoundingBox(ViewBox viewBox){
		//int a=text.toCharArray().length;
		viewBox.updateBoundingBox(x, y);
		//viewBox.updateBoundingBox(x+(viewBox.xMax-viewBox.xMin)/200*a, y);
		//viewBox.updateBoundingBox(x, y+(viewBox.yMax-viewBox.yMin)/50);
	}

	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		//exemple 
		//<text x="250" y="150" font-family="Verdana" font-size="55" fill="blue" >
		//Hello, out there
		//</text>
		double adjusted_x= viewBox.xTransform(x);
		double adjusted_y= viewBox.yTransform(y);
		writer.append("<text");
		printStyleBlock(writer);
		writer.append(" x=\""+fm(adjusted_x)+"\" y=\""+fm(adjusted_y)+"\">\n"+text+"\n</text>\n");
	}
	
	
}
