package com.s8.core.io.svg.transform;

import com.s8.core.io.svg.ViewBox;


/**
 * 
 * @author pierreconvert
 *
 */
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
