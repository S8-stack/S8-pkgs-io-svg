package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class VerticalLine_absolute extends SVG_PathElement {

	public static VerticalLine_absolute deserialize(ByteInflow inflow) throws IOException {
		return new VerticalLine_absolute(inflow.getFloat32());
	}

	public final static int CODE = 0x25;

	public final double y;

	public VerticalLine_absolute(double y) {
		super();
		this.y = y;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) y);
	}

	public @Override long computeFootprint() { return 4; }
	public @Override S8Serializable deepClone() { return new VerticalLine_absolute(y); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { VerticalLine_absolute right = (VerticalLine_absolute) element;
		return this.y == right.y;
	}
}