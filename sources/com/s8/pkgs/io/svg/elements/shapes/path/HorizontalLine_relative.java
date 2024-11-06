package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class HorizontalLine_relative extends SVG_PathElement {
	
	
	public static HorizontalLine_relative create(double dx) {
		return new HorizontalLine_relative(dx);
	}

	public static HorizontalLine_relative deserialize(ByteInflow inflow) throws IOException {
		return new HorizontalLine_relative(inflow.getFloat32());
	}

	public final static int CODE = 0x24;

	public final double dx;

	public HorizontalLine_relative(double dx) {
		super();
		this.dx = dx;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) dx);
	}

	public @Override long computeFootprint() { return 4; }
	public @Override S8Serializable deepClone() { return new HorizontalLine_relative(dx); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { HorizontalLine_relative right = (HorizontalLine_relative) element;
		return this.dx == right.dx;
	}
}