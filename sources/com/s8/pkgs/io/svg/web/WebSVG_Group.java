package com.s8.pkgs.io.svg.web;

import java.util.List;

import com.s8.api.web.S8WebFront;


/**
 * 
 * @author pierreconvert
 *
 */
public class WebSVG_Group extends WebSVG_Element {

	
	/**
	 * 
	 * @param front
	 * @param typeName
	 */
	public WebSVG_Group(S8WebFront front) {
		super(front, WebSources.WEBPATH + "/WebSVG_Group");
	}
	
	

	public void setElements(List<WebSVG_Element> elements) {
		vertex.outbound().setObjectListField("elements", elements);
	}
	
	public void setElements(WebSVG_Element[] elements) {
		vertex.outbound().setObjectListField("elements", elements);
	}
	
	
	public void setClickable(boolean isClickable) {
		vertex.outbound().setBool8Field("clickable", isClickable);
	}
	

}
