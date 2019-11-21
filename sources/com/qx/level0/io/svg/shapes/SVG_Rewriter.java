package com.qx.level0.io.svg.shapes;

import com.qx.level0.maths.MathVector2d;

public interface SVG_Rewriter {
	
	public abstract MathVector2d transformPoint(MathVector2d point);
	
	public abstract MathVector2d transformVector(MathVector2d vector);
	
	
	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public abstract double transformScalar(double scalar);
	
	
	/**
	 * 
	 * 
	 * @return true is the transformation is acting as an <b>EXACT</b> quarter, half
	 *         or three quarters of a turn rotation.
	 */
	public abstract boolean isRotationExact();

}
