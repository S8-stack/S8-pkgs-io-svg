package com.qx.io.svg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;




public class SVG_Canvas {


	public final static DecimalFormat FORMAT = new DecimalFormat("0.##");

	static{
		DecimalFormatSymbols symbols = FORMAT.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
	}



	private final static String headerLine1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
	private final static String headerLine2 = "<?xml-stylesheet href='mystyle.css' type='text/css'?>\n";

	private final static String xmlns = "http://www.w3.org/2000/svg";

	public final static String xmlRegex = "(<\\?.*\\?>)(<.*>)";

	/*
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 */



	protected List<SVG_Shape> shapes;


	protected boolean hasBeenAdjusted = false;


	protected final static int maxNumberOfShapes = (int) 1e6;
	protected int shapeCount;
	protected ViewBox viewBox;

	public SVG_Canvas(){
		shapes = new ArrayList<SVG_Shape>();
		shapeCount = 0;

	}

	public void add(SVG_Shape shape){
		if(shapeCount>maxNumberOfShapes){
			throw new RuntimeException("max number of shapes exceed");
		}
		shapes.add(shape);
		shapeCount++;
	}


	private double size = 800;
	private final static double margin = 60;


	/**
	 * Print shapes
	 * @param writer
	 * @throws IOException
	 */
	public void print(String pathname) {
		try {
			// print shapes
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(pathname)));

			// add header
			writer.append(headerLine1);
			writer.append(headerLine2);

			writer.append(printToHTML());

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setViewBoxSize(double size){
		this.size = size;
	}

	public String printToHTML() throws IOException {
		// compute the bounding box
		viewBox = new ViewBox(size, margin);
		for(SVG_Shape shape : shapes){
			shape.updateBoundingBox(viewBox);
		}


		// initialize transformations

		viewBox.initializedViewTransform();


		StringWriter writer = new StringWriter();
		writer.append("<svg version=\"1.1\" viewBox=\""+viewBox.toString()+"\" width=\""+size+"\" height=\""+size+"\" margin=\""+margin+"\" xmlns=\""+xmlns+"\">\n");
		for(SVG_Shape shape : shapes){
			shape.print(writer, viewBox);
		}
		writer.append("</svg>");
		return writer.toString();
	}

}
