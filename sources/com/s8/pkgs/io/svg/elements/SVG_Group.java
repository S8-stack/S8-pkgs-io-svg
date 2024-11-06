package com.s8.pkgs.io.svg.elements;

import java.io.IOException;
import java.util.List;

import com.s8.api.annotations.S8Field;
import com.s8.api.annotations.S8ObjectType;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.web.WebSVG_Element;
import com.s8.pkgs.io.svg.web.WebSVG_Group;


/**
 * 
 * @author pierreconvert
 *
 */
@S8ObjectType(name = "com.s8.pkgs.ui.websvg.model.WebSVG_GroupModel")
public class SVG_Group extends SVG_Element {
	
	
	public static SVG_Group create(List<SVG_Element> elements, boolean isClickable) {
		SVG_Group groupModel = new SVG_Group();
		groupModel.setElements(elements);
		groupModel.setBoundingBoxRelevant(true);
		groupModel.setClickable(isClickable);
		return groupModel;
	}

	
	/**
	 * elements
	 */
	public @S8Field(name = "elements") SVG_Element[] elements;
	

	/**
	 * 
	 */
	public @S8Field(name = "clickable") boolean isClickable;
	
	/**
	 * S8 constructor
	 */
	public SVG_Group() { super(); }
	
	/**
	 * S8 constructor
	 */
	public SVG_Group(SVG_Element[] elements) { 
		super();
		this.elements = elements;
	}
	
	

	public void setElements(List<SVG_Element> elements) {
		int n = elements.size();
		SVG_Element[] array = new SVG_Element[n];
		for(int i = 0; i<n; i++) { array[i] = elements.get(i); }
		this.elements = array;
	}
	
	
	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}



	@Override
	public WebSVG_Element createWeb(S8WebFront front) {
		WebSVG_Group group = new WebSVG_Group(front);
		
		int n = elements.length;
		WebSVG_Element[] array = new WebSVG_Element[n];
		for(int i = 0; i<n; i++) { array[i] = elements[i].createWeb(front); }
		group.setElements(array);
		
		group.setClickable(isClickable);
		
		return group;
	}
	
	
	
	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box){
		for(SVG_Element element : elements){
			element.updateBoundingBox(box);
		}
	}

	
	@Override
	public void print(StringBuilder writer, ViewBox viewBox) throws IOException {
		writer.append("<g");
		/* printAttributes(writer); */
		writer.append(">\n");
		for(SVG_Element element : elements){
			element.print(writer, viewBox);
		}
		writer.append("</g>\n");
	}

	@Override
	public SVG_Group rewrite(SVG_Rewriter transform) {
		int n = elements.length;
		SVG_Element[] transformedShapes = new SVG_Element[n];
		for(int i = 0; i<n; i++) {
			transformedShapes[i] = elements[i].rewrite(transform);
		}
		return new SVG_Group(transformedShapes);
	}


}
