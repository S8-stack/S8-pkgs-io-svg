package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;

public  class Line_absolute extends SVG_PathElement {
	
	public static SVG_PathElement create(double x, double y) {
		return new Line_absolute(x, y);
	}
	

	public static Line_absolute deserialize(ByteInflow inflow) throws IOException {
		return new Line_absolute(inflow.getFloat32(), inflow.getFloat32());
	}

	public final static int CODE = 0x21;

	public final double x, y;

	public Line_absolute(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) x);
		outflow.putFloat32((float) y);
	}

	public @Override long computeFootprint() { return 2 * 4; }
	public @Override S8Serializable deepClone() { return new Line_absolute(x, y); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { Line_absolute right = (Line_absolute) element;
		return this.x == right.x;
	}

	
}
