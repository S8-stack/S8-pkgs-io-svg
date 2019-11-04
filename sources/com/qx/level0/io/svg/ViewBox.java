package com.qx.level0.io.svg;



public class ViewBox {


	
	// bounding box
	private double xMin, yMin, xMax, yMax;
	private boolean isInitialized = false;
	
	
	// viewport
	private double size, scalingFactor, margin, xMargin, yMargin; 
	
	
	/**
	 * @param size
	 */
	public ViewBox(double size, double margin) {
		super();
		this.size = size;
		this.margin = margin;
	}


	public void updateBoundingBox(double x, double y){
		if(!isInitialized){
			xMin = x;
			xMax = x;
			yMin = y;
			yMax = y;
			isInitialized = true;
		}
		else{
			xMin = Math.min(xMin, x);
			xMax = Math.max(xMax, x);
			yMin = Math.min(yMin, y);
			yMax = Math.max(yMax, y);
		}
	}
	
	
	
	public void initializedViewTransform(){
		double aperture = size - 2*margin;
		scalingFactor = aperture/Math.max(xMax-xMin, yMax-yMin);
		xMargin = (aperture - scalingFactor*(xMax-xMin))/2;
		yMargin = (aperture - scalingFactor*(yMax-yMin))/2;
	}
	
	
	public double xTransform(double xCoordinate){
		return (xCoordinate-xMin)*scalingFactor+xMargin+margin;
	}
	
	public double yTransform(double yCoordinate){
		return (yMax-yCoordinate)*scalingFactor+yMargin+margin;
	}
	
	
	public double scale(double length){
		return length*scalingFactor;
	}
	
	@Override
	public String toString(){
		return "0 0 "+size+" "+size;
	}
	
	public double getSize(){
		return size;
	}

}
