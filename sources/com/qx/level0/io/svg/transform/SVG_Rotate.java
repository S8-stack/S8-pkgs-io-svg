package com.qx.level0.io.svg.transform;

import com.qx.level0.io.svg.ViewBox;

public class SVG_Rotate extends SVG_Transform {
	
	private double angle;
	
	public SVG_Rotate(double angle) {
		super();
		this.angle = angle;
	}

	@Override
	public void print(StringBuilder builder, ViewBox box) {
		builder.append("rotate(");
		builder.append(angle);
		builder.append(")");
	}

}
