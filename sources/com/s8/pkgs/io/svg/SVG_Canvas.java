package com.s8.pkgs.io.svg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import com.s8.api.annotations.S8Field;
import com.s8.api.annotations.S8ObjectType;
import com.s8.api.flow.repository.objects.RepoS8Object;
import com.s8.api.web.S8WebFront;
import com.s8.pkgs.io.svg.elements.SVG_Element;
import com.s8.pkgs.io.svg.maths.SVG_BoundingBox2D;
import com.s8.pkgs.io.svg.web.WebSVG_Canvas;
import com.s8.pkgs.io.svg.web.WebSVG_Element;



/**
 * 
 * @author pierreconvert
 *
 */
@S8ObjectType(name = "com.s8.pkgs.ui.websvg.model.WebSVG_CanvasModel")
public class SVG_Canvas extends RepoS8Object {

	
	/**
	 * 
	 * @param elements
	 * @return
	 */
	public static SVG_Canvas wrap(List<SVG_Element> elements) {
		SVG_Canvas canvas = new SVG_Canvas();
		canvas.setElements(elements);
		return canvas;
	}
	
	/**
	 * 
	 * @param elements
	 * @return
	 */
	public static SVG_Canvas wrap(SVG_Element... elements) {
		SVG_Canvas canvas = new SVG_Canvas();
		canvas.elements = elements;
		return canvas;
	}

	private final static String HEADER_LINE1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
	private final static String HEADER_LINE2 = "<?xml-stylesheet href='mystyle.css' type='text/css'?>\n";

	private final static String XMLNS = "http://www.w3.org/2000/svg";

	//public final static String xmlRegex = "(<\\?.*\\?>)(<.*>)";

	/*
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 */




	protected boolean hasBeenAdjusted = false;


	protected final static int MAX_NUMBER_OF_SHAPES = (int) 1e6;

	protected int shapeCount;

	private final ViewBox viewBox;


	/**
	 * Total width of the canvas (height is size accordingly, keeping ratio)
	 */
	public double width = 1024;

	public double leftPadding = 64;

	public double rightPadding = 64;

	public double topPadding = 16;

	public double bottomPadding = 16;


	public @S8Field(name = "elements") SVG_Element[] elements;


	public @S8Field(name = "viewport-width") double viewportWidth = 1920;

	public @S8Field(name = "viewport-height") double viewportHeight = 1080;



	public @S8Field(name = "margin-top") double marginTop = 10;

	public @S8Field(name = "margin-right") double marginRight = 20;

	public @S8Field(name = "margin-bottom") double marginBottom = 10;

	public @S8Field(name = "margin-left") double marginLeft = 20;


	/**
	 * 
	 * @param front
	 * @param typeName
	 */
	public SVG_Canvas() {
		super();
		viewBox = new ViewBox(this);
	}



	/**
	 * 
	 * @param elements
	 */
	public void setElements(List<SVG_Element> elements) {
		int n = elements.size();
		/*
		if(n > MAX_NUMBER_OF_SHAPES){
			throw new RuntimeException("max number of shapes exceed: "+MAX_NUMBER_OF_SHAPES);
		}
		*/

		SVG_Element[] array = new SVG_Element[n];
		for(int i = 0; i<n; i++) { array[i] = elements.get(i); }
		this.elements = array;
	}





	/**
	 * 
	 * @param width
	 */
	public void setViewportDimensions(double width, double height){
		this.viewportWidth = width;
		this.viewportHeight = height;
	}



	/**
	 * 
	 * @param margin
	 */
	public void setViewportMargins(double marginTop, double marginRight, double marginBottom, double marginLeft){
		setViewportMarginTop(marginTop);
		setViewportMarginRight(marginRight);
		setViewportMarginBottom(marginBottom);
		setViewportMarginLeft(marginLeft);
	}

	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginTop(double margin){
		this.marginTop = margin;
	}


	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginRight(double margin){
		this.marginRight = margin;
	}



	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginBottom(double margin){
		this.marginBottom = margin;
	}


	/**
	 * 
	 * @param margin
	 */
	public void setViewportMarginLeft(double margin){
		this.marginLeft = margin;
	}




	/**
	 * 
	 * @param front
	 * @return
	 */
	public WebSVG_Canvas createWeb(S8WebFront front) {
		WebSVG_Canvas group = new WebSVG_Canvas(front);

		int n = elements.length;
		WebSVG_Element[] array = new WebSVG_Element[n];
		for(int i = 0; i<n; i++) { array[i] = elements[i].createWeb(front); }
		group.setElements(array);

		group.setViewport(viewportWidth, viewportHeight, marginTop, marginRight, marginBottom, marginLeft);

		return group;
	}

	public final static DecimalFormat FORMAT = new DecimalFormat("0.##");

	static{
		DecimalFormatSymbols symbols = FORMAT.getDecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
	}






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

		SVG_BoundingBox2D boundingBox2d = viewBox.getBoundingBox();
		for(SVG_Element element : elements){
			element.updateBoundingBox(boundingBox2d);
		}


		// initialize transformations
		viewBox.compile();


		StringBuilder builder = new StringBuilder();
		builder.append("<svg version=\"1.1\" ");
		viewBox.print(builder);
		builder.append(" xmlns=\""+XMLNS+"\">\n");
		for(SVG_Element element : elements){
			element.print(builder, viewBox);
		}
		builder.append("</svg>");
		return builder.toString();
	}

}
