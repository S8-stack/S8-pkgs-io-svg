package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;



public class VerticalLine_relative extends SVG_PathElement {
	
	public static SVG_PathElement create(double dy) {
		return new VerticalLine_relative(dy);
	}
	

	public static VerticalLine_relative deserialize(ByteInflow inflow) throws IOException {
		return new VerticalLine_relative(inflow.getFloat32());
	}

	public final static int CODE = 0x26;

	public final double dy;

	public VerticalLine_relative(double dy) {
		super();
		this.dy = dy;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) dy);
	}

	public @Override long computeFootprint() { return 4; }
	public @Override S8Serializable deepClone() { return new VerticalLine_relative(dy); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { VerticalLine_relative right = (VerticalLine_relative) element;
		return this.dy == right.dy;
	}

	
}