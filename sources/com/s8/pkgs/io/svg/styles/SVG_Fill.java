package com.s8.pkgs.io.svg.styles;



public class SVG_Fill {
	
	
	public static SVG_Fill createDefault() {
		return new SVG_Fill(SVG_FillColor.WHITE);
	}
	
	
	public final long color;

	public SVG_Fill(long color) {
		super();
		this.color = color;
	}

}
