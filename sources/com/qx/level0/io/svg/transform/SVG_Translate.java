package com.qx.level0.io.svg.transform;

import com.qx.level0.io.svg.ViewBox;

public class SVG_Translate extends SVG_Transform {

	private double dx;

	private double dy;


	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	public SVG_Translate(double dx, double dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}


	@Override
	public void print(StringBuilder builder, ViewBox box) {
		builder.append("translate(");
		builder.append(box.dxScale(dx));
		builder.append(",");
		builder.append(box.dxScale(dy));
		builder.append(")");
	}

}
