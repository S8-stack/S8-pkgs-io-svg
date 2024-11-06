package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class Line_relative extends SVG_PathElement {

	public static Line_relative deserialize(ByteInflow inflow) throws IOException {
		return new Line_relative(inflow.getFloat32(), inflow.getFloat32());
	}

	public final static int CODE = 0x22;

	public final double dx, dy;

	public Line_relative(double dx, double dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) dx);
		outflow.putFloat32((float) dy);
	}

	public @Override long computeFootprint() { return 2 * 4; }
	public @Override S8Serializable deepClone() { return new Line_absolute(dx, dy); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { Line_relative right = (Line_relative) element;
		return this.dx == right.dx;
	}
}

