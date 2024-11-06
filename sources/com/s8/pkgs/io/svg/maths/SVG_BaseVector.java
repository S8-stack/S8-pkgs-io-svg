package com.s8.pkgs.io.svg.maths;

/**
 * 
 * @author pierreconvert
 *
 */
public class SVG_BaseVector implements SVG_Vector {
	
		/**
		 * 
		 * @return x coordinate of the vector
		 */
		public double x;
		
		
		/**
		 * 
		 * @return y coordinate of the vector
		 */
		public double y;
		
		
		public SVG_BaseVector() {
			super();
		}
		
		public SVG_BaseVector(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}


		@Override
		public double getX() {
			return x;
		}


		@Override
		public double getY() {
			return y;
		}
	
}
