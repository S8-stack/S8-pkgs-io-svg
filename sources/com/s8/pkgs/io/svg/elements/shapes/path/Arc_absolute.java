package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class Arc_absolute extends SVG_PathElement {


	public static SVG_PathElement create(double r, boolean isLargeArcFlag, boolean isSweepPositiveFlag, double x, double y) {
		return new Arc_absolute(r, isLargeArcFlag, isSweepPositiveFlag, x, y);
	}
	
	public static Arc_absolute deserialize(ByteInflow inflow) throws IOException {
		return new Arc_absolute(inflow.getFloat32(), inflow.getBool8(), inflow.getBool8(), inflow.getFloat32(), inflow.getFloat32());
	}

	public final static int CODE = 0x31;

	/** radius (no ellipse, only plain circle) */
	public final double r;


	public final boolean isLargeArcFlag;

	public final boolean isSweepPositiveFlag;

	public final double xEnd;

	public final double yEnd;

	public Arc_absolute(double r, boolean isLargeArcFlag, boolean isSweepPositiveFlag, double xEnd, double yEnd) {
		super();
		this.r = r;
		this.isLargeArcFlag = isLargeArcFlag;
		this.isSweepPositiveFlag = isSweepPositiveFlag;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) r);
		outflow.putBool8(isLargeArcFlag);
		outflow.putBool8(isSweepPositiveFlag);
		outflow.putFloat32((float) xEnd);
		outflow.putFloat32((float) yEnd);
	}

	public @Override long computeFootprint() { return 3 * 4 + 2; }
	public @Override S8Serializable deepClone() { return new Arc_absolute(r, isLargeArcFlag, isSweepPositiveFlag, xEnd, yEnd); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) {
		Arc_absolute right = (Arc_absolute) element;
		return this.r == right.r &&
				this.isLargeArcFlag == right.isLargeArcFlag &&
				this.isSweepPositiveFlag == right.isSweepPositiveFlag &&
				this.xEnd == right.xEnd &&
				this.yEnd == right.yEnd;
	}
}

