package com.s8.pkgs.io.svg.web;

import com.s8.api.web.S8WebFront;
import com.s8.api.web.S8WebObject;

public class WebSVG_Element extends S8WebObject {

	public WebSVG_Element(S8WebFront branch, String typeName) {
		super(branch, typeName);
	}
	
	
	public void setBoundingBoxRelevant(boolean state) {
		vertex.outbound().setBool8Field("isBoundingBoxRelevant", state);
	}
	
	
}
