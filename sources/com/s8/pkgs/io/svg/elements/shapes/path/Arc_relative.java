package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class Arc_relative extends SVG_PathElement {
	
	public static SVG_PathElement create(double r, boolean isLargeArcFlag, boolean isSweepPositiveFlag, double dx, double dy) {
		return new Arc_relative(r, isLargeArcFlag, isSweepPositiveFlag, dx, dy);
	}

	public static Arc_relative deserialize(ByteInflow inflow) throws IOException {
		return new Arc_relative(inflow.getFloat32(), inflow.getBool8(), inflow.getBool8(), inflow.getFloat32(), inflow.getFloat32());
	}

	public final static int CODE = 0x32;

	/** radius (no ellipse, only plain circle) */
	public final double r;


	public final boolean isLargeArcFlag;

	public final boolean isSweepPositiveFlag;

	public final double dx;

	public final double dy;

	public Arc_relative(double r, boolean isLargeArcFlag, boolean isSweepPositiveFlag, double dx, double dy) {
		super();
		this.r = r;
		this.isLargeArcFlag = isLargeArcFlag;
		this.isSweepPositiveFlag = isSweepPositiveFlag;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) r);
		outflow.putBool8(isLargeArcFlag);
		outflow.putBool8(isSweepPositiveFlag);
		outflow.putFloat32((float) dx);
		outflow.putFloat32((float) dy);
	}

	public @Override long computeFootprint() { return 3 * 4 + 2; }
	public @Override S8Serializable deepClone() { return new Arc_relative(r, isLargeArcFlag, isSweepPositiveFlag, dx, dy); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) {
		Arc_relative right = (Arc_relative) element;
		return this.r == right.r &&
				this.isLargeArcFlag == right.isLargeArcFlag &&
				this.isSweepPositiveFlag == right.isSweepPositiveFlag &&
				this.dx == right.dx &&
				this.dy == right.dy;
	}

	
}