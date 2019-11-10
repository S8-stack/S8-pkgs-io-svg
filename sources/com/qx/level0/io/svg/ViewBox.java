package com.qx.level0.io.svg;

import java.text.DecimalFormat;

public class ViewBox {

	/**
	 * 4 meters objects print as 800px wide in view port
	 */
	public final static double DEFAULT_SCALING = 800/4.0;
	
	public final static double DEFAULT_PADDING = 0;


	private boolean isInitialized;

	private double x0;

	private double x1;

	private double y0;

	private double y1;

	private double scaling = 1.0;

	private double padding = 0.0;
	
	
	private double xViewBox0, xViewBox1, yViewBox0, yViewBox1;

	
	/**
	 * 
	 * @param scaling
	 * @param padding
	 */
	public ViewBox(){
		super();
		this.scaling = DEFAULT_SCALING;
		this.padding = DEFAULT_PADDING;
	}
	
	/**
	 * 
	 * @param scaling
	 * @param padding
	 */
	public ViewBox(double scaling, double padding){
		super();
		this.scaling = scaling;
		this.padding = padding;
	}


	public void updateBoundingBox(double x, double y) {
		if(isInitialized) {
			x0 = Math.min(x0, x);
			x1 = Math.max(x1, x);
			y0 = Math.min(y0, y);
			y1 = Math.max(y1, y);
		}
		else {
			x0 = x;
			x1 = x;
			y0 = y;
			y1 = y;
			isInitialized = true;
		}
	}



	public double xTransform(double x) {
		return (x-x0)*scaling;
	}

	public double dxScale(double dx) {
		return (dx)*scaling;
	}

	public double yTransform(double y) {
		return (y1-y)*scaling;
	}

	public double dyScale(double dy) {
		return (-dy)*scaling;
	}

	public double scale(double d) {
		return d*scaling;
	}
	
	
	
	public void compile() {
		xViewBox0 = -padding;
		xViewBox1 = (x1-x0)*scaling+padding;
		yViewBox0 = -padding;
		yViewBox1 = (y1-y0)*scaling+padding;
	}


	/**
	 * 
	 * @param builder
	 */
	@Override
	public String toString() {

		

		DecimalFormat format = new DecimalFormat("0.####");

		StringBuilder builder = new StringBuilder();
		builder.append(format.format(xViewBox0)); builder.append(' ');
		builder.append(format.format(yViewBox0)); builder.append(' ');
		builder.append(format.format(xViewBox1)); builder.append(' ');
		builder.append(format.format(yViewBox1));
		return builder.toString();
	}

	public double getFixRatioHeight(double width){
		return width*(yViewBox1-yViewBox0)/(xViewBox1-xViewBox0);
	}

}
