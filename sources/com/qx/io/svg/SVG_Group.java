package com.qx.io.svg;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SVG_Group extends SVG_Shape{

	protected List<SVG_Shape> shapes;


	/**
	 * 
	 */
	public SVG_Group() {
		super();
	}
	
	
	/**
	 * 
	 */
	public SVG_Group(String style) {
		super();
		this.styleClass = style;
	}
	
	
	public void add(SVG_Shape shape){
		if(shapes==null){
			shapes = new ArrayList<SVG_Shape>();
		}
		shapes.add(shape);
	}


	@Override
	public void updateBoundingBox(ViewBox viewBox){
		for(SVG_Shape shape : shapes){
			shape.updateBoundingBox(viewBox);
		}
	}

	
	@Override
	public void print(StringWriter writer, ViewBox viewBox) throws IOException {
		writer.append("<g");
		printStyleBlock(writer);
		writer.append(">\n");
		for(SVG_Shape shape : shapes){
			shape.print(writer, viewBox);
		}
		writer.append("</g>\n");
	}


}
