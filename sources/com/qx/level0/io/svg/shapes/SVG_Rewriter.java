package com.qx.level0.io.svg.shapes;

import com.qx.level0.maths.MathVector2D;

public interface SVG_Rewriter {
	
	public abstract MathVector2D transformPoint(MathVector2D point);
	
	public abstract MathVector2D transformVector(MathVector2D vector);
	
	
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
