package com.qx.core.io.svg.demos;

import java.util.ArrayList;
import java.util.List;

import com.s8.pkgs.io.svg.SVG_Canvas;
import com.s8.pkgs.io.svg.elements.SVG_Element;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Circle;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Line;
import com.s8.pkgs.io.svg.elements.shapes.primitives.SVG_Rectangle;

/**
 * 
 * @author pierreconvert
 *
 */
public class Demo {

	public static void main(String[] args){

		SVG_Canvas canvas = new SVG_Canvas();
		List<SVG_Element> elements = new ArrayList<>();

		int exe = 2;
		switch(exe){
		case 1:
			elements.add(new SVG_Circle("point", 0, 0, 2.0));
			elements.add(new SVG_Circle("point", 0.5, 3, 2.0));
			elements.add(new SVG_Line("dashed", 0, 0, 2,2));
			break;
		case 2:
			elements.add(new SVG_Rectangle("interior", 0.,  0., 300., 200.));
			elements.add(new SVG_Line("dashed-grey", 0., 50., 300.,50.));
			elements.add(new SVG_Line("dashed-grey", 0., 100., 300.,100.));
			elements.add(new SVG_Line("dashed-grey", 0., 150., 300.,150.));
			
			elements.add(new SVG_Line("dashed-grey", 75., 0., 75.,200.));
			elements.add(new SVG_Line("dashed-grey", 150., 0., 150.,200.));
			elements.add(new SVG_Line("dashed-grey", 225., 0., 225.,200.));
			break;
		}
		

		canvas.setElements(elements);
		canvas.print("output/myFirstSVG.svg");
	}
}
