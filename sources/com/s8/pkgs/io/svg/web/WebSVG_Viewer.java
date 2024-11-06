package com.s8.pkgs.io.svg.web;

import com.s8.api.web.S8WebFront;
import com.s8.api.web.S8WebObject;

public class WebSVG_Viewer extends S8WebObject {
	
	

	/**
	 * 
	 * @param front
	 * @param typeName
	 */
	public WebSVG_Viewer(S8WebFront front) {
		super(front, WebSources.WEBPATH + "/viewer/WebSVG_Viewer");
	}
	
	

	public void setCanvas(WebSVG_Canvas canvas) {
		vertex.outbound().setObjectField("canvas", canvas);
	}
	

}
