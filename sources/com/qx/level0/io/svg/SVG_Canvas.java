package com.qx.level0.io.svg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import com.qx.level0.io.svg.shapes.SVG_Shape;




public class SVG_Canvas {

	public final static DecimalFormat FORMAT = new DecimalFormat("0.##");

	static{
		DecimalFormatSymbols symbols = FORMAT.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
	}



	private final static String HEADER_LINE1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
	private final static String HEADER_LINE2 = "<?xml-stylesheet href='mystyle.css' type='text/css'?>\n";

	private final static String XMLNS = "http://www.w3.org/2000/svg";

	//public final static String xmlRegex = "(<\\?.*\\?>)(<.*>)";

	/*
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 */



	protected List<SVG_Shape> shapes;


	protected boolean hasBeenAdjusted = false;


	protected final static int maxNumberOfShapes = (int) 1e6;
	
	private static final int MARGIN = 20;
	private static final int DEFAULT_WIDTH = 800;
	
	protected int shapeCount;
	protected ViewBox viewBox;

	public SVG_Canvas(){
		super();
		viewBox = new ViewBox();
		initialize();
	}
	
	public SVG_Canvas(double scaling, double padding){
		super();
		viewBox = new ViewBox(scaling, padding);
		initialize();
	}

	
	private void initialize() {
		shapes = new ArrayList<SVG_Shape>();
		shapeCount = 0;
	}
	
	
	/**
	 * Null shape ignored
	 * @param shape
	 */
	public void add(SVG_Shape shape){
		if(shape!=null) {
			if(shapeCount>maxNumberOfShapes){
				throw new RuntimeException("max number of shapes exceed");
			}
			shapes.add(shape);
			shapeCount++;	
		}
	}



	/**
	 * Print shapes
	 * @param writer
	 * @throws IOException
	 */
	public void print(String pathname) {
		print(pathname, DEFAULT_WIDTH);
	}
	
	/**
	 * Print shapes
	 * @param writer
	 * @throws IOException
	 */
	public void print(String pathname, double width) {
		try {
			// print shapes
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(pathname)));

			// add header
			writer.append(HEADER_LINE1);
			writer.append(HEADER_LINE2);

			writer.append(printToHTML(width));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String printToHTML(double width) throws IOException {
		// compute the bounding box
		
		for(SVG_Shape shape : shapes){
			shape.updateBoundingBox(viewBox);
		}


		// initialize transformations

		viewBox.compile();


		double height = viewBox.getFixRatioHeight(width);
		StringBuilder builder = new StringBuilder();
		builder.append("<svg version=\"1.1\" viewBox=\""+viewBox.toString()+"\" width=\""+width+"\" height=\""+height+"\" margin=\""+MARGIN+"\" xmlns=\""+XMLNS+"\">\n");
		for(SVG_Shape shape : shapes){
			shape.print(builder, viewBox);
		}
		builder.append("</svg>");
		return builder.toString();
	}

}
