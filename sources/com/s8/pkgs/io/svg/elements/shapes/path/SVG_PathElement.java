package com.s8.pkgs.io.svg.elements.shapes.path;

import java.io.IOException;

import com.s8.api.bytes.ByteInflow;
import com.s8.api.serial.S8ExplicitSerialCodes;
import com.s8.api.serial.S8ExplicitSerialPrototype;
import com.s8.api.serial.S8ExplicitSerializable;
import com.s8.pkgs.io.svg.WebSVG_SerialCodes;


/**
 * 
 */
public abstract class SVG_PathElement implements S8ExplicitSerializable {




	@Override
	public S8ExplicitSerialPrototype<?> getSerialPrototype() {
		return PROTO;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public abstract int getCode();
	
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public abstract boolean isEqualTo(SVG_PathElement element);





	public final S8ExplicitSerialPrototype<SVG_PathElement> PROTO =
			new S8ExplicitSerialPrototype<SVG_PathElement>() {

		@Override
		public SVG_PathElement deserialize(ByteInflow inflow) throws IOException {
			int code;
			switch((code = inflow.getUInt8())){
				
			case ClosePath.CODE : return ClosePath.deserialize(inflow);
			
			case MoveTo_absolute.CODE : return MoveTo_absolute.deserialize(inflow);
			case MoveTo_relative.CODE : return MoveTo_relative.deserialize(inflow);
			
			case Line_absolute.CODE : return Line_absolute.deserialize(inflow);
			case Line_relative.CODE : return Line_relative.deserialize(inflow);
			case HorizontalLine_absolute.CODE : return HorizontalLine_absolute.deserialize(inflow);
			case HorizontalLine_relative.CODE : return HorizontalLine_relative.deserialize(inflow);
			case VerticalLine_absolute.CODE : return VerticalLine_absolute.deserialize(inflow);
			case VerticalLine_relative.CODE : return VerticalLine_relative.deserialize(inflow);
			
			case Arc_absolute.CODE : return Arc_absolute.deserialize(inflow);
			case Arc_relative.CODE : return Arc_relative.deserialize(inflow);
			
			default : throw new IOException("Unsupported Path Element with code: " + code);
			
			}
		}

		@Override
		public boolean hasDelta(SVG_PathElement left, SVG_PathElement right) {
			if((left != null && right == null) || (left == null && right != null)) {
				return true;
			}
			else if(left == null && right == null) {
				return false;
			}
			else {
				if(left.getCode() != right.getCode()) { return true; }
				else {
					return left.isEqualTo(right);
				}
			}
		}

		@Override
		public Class<?> getSerialType() {
			return SVG_PathElement.class;
		}

		@Override
		public byte[] getSignature() {
			return new byte[] { S8ExplicitSerialCodes.IMAGE_SVG, WebSVG_SerialCodes.PATH_ELEMENT };
		}
	};


}
