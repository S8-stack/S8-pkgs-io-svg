package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;
import java.util.List;

import com.s8.api.annotations.S8Field;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Shape;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.styles.SVG_Fill;
import com.s8.pkgs.io.svg.styles.SVG_Stroke;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Path;
import com.s8.pkgs.io.svg.web.shapes.WebSVG_Shape;


/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_Path extends SVG_Shape {



	/**
	 * 
	 * @param branch
	 * @param stroke
	 * @param fill
	 * @param isBoundingBoxUpdating
	 * @param elements
	 * @return
	 */
	public static SVG_Path create(SVG_Stroke stroke, SVG_Fill fill,
			boolean isBoundingBoxUpdating,
			SVG_PathElement... elements) {
		SVG_Path shape = new SVG_Path();
		if(stroke != null) { shape.setStroke(stroke); }
		if(fill != null) { shape.setFill(fill); }
		shape.setBoundingBoxRelevant(isBoundingBoxUpdating);
		shape.setElements(elements);
		return shape;
	}


	public @S8Field(name = "elements") SVG_PathElement[] elements;


	/** S8 constructor */
	public SVG_Path() { super(); }


	/**
	 * 
	 * @param point
	 */
	public void setElements(SVG_PathElement... elements) {
		this.elements = elements;
	}


	/**
	 * 
	 * @param point
	 */
	public void setElements(List<SVG_PathElement> elements) {
		int n = elements.size();
		SVG_PathElement[] array = new SVG_PathElement[n];
		for(int i = 0; i<n; i++) { array[i] = elements.get(i); }
		this.elements = array;
	}

	
	
	@Override
	public WebSVG_Shape createWeb(S8WebFront front) {
		WebSVG_Path polygon = new WebSVG_Path(front);
		polygon.setElements(elements);
		
		applyStyle(polygon);
		applyProperties(polygon);
		return polygon;
	}


	@Override
	public void updateBoundingBox(SVG_BoundingBox2D box) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public SVG_Shape rewrite(SVG_Rewriter rewriter) {
		// TODO Auto-generated method stub
		return null;
	}

}
