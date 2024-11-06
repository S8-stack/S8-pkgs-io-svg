package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.serial.S8Serializable;


/**
 * 
 */
public class HorizontalLine_absolute extends SVG_PathElement {

	public static HorizontalLine_absolute deserialize(ByteInflow inflow) throws IOException {
		return new HorizontalLine_absolute(inflow.getFloat32());
	}

	public final static int CODE = 0x23;

	public final double x;

	public HorizontalLine_absolute(double x) {
		super();
		this.x = x;
	}

	@Override
	public void serialize(ByteOutflow outflow) throws IOException {
		outflow.putUInt8(CODE);
		outflow.putFloat32((float) x);
	}

	public @Override long computeFootprint() { return 4; }
	public @Override S8Serializable deepClone() { return new HorizontalLine_absolute(x); }
	public @Override int getCode() { return CODE; }
	
	@Override
	public boolean isEqualTo(SVG_PathElement element) { HorizontalLine_absolute right = (HorizontalLine_absolute) element;
		return this.x == right.x;
	}
}