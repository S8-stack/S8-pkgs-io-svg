package com.s8.pkgs.io.svg.elements;

import java.io.IOException;

import com.s8.api.annotations.S8Field;
import com.s8.api.annotations.S8ObjectType;
import com.s8.api.flow.repository.objects.RepoS8Object;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.ViewBox;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Rewriter;
import com.s8.pkgs.io.svg.elements.shapes.SVG_Shape;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.web.WebSVG_Element;


@S8ObjectType(name = "com.s8.pkgs.ui.websvg.model.WebSVG_ModelElement", sub = {
		SVG_Shape.class,
		SVG_Group.class
})
public abstract class SVG_Element extends RepoS8Object {
	
	@S8Field(name = "isBoundingBoxRelevant")
	public boolean isUpdatingBoundingBox = true;
	
	/** S8 constructor  */
	public SVG_Element() { super(); }
	
	
	public void setBoundingBoxRelevant(boolean state) {
		this.isUpdatingBoundingBox = state;
	}
	
	
	
	protected void applyProperties(WebSVG_Element element) {
		element.setBoundingBoxRelevant(isUpdatingBoundingBox);
	}
	
	

	/**
	 * 
	 * @param front
	 * @return
	 */
	public abstract WebSVG_Element createWeb(S8WebFront front);


	public abstract void updateBoundingBox(SVG_BoundingBox2D box);
	

	public abstract SVG_Element rewrite(SVG_Rewriter rewriter);


	public abstract void print(StringBuilder builder, ViewBox viewBox) throws IOException;
	
	
}
