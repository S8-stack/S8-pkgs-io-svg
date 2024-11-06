package com.qx.core.io.svg.devops;

import com.s8.build.js.JS_CodeGenerator;
import com.s8.pkgs.io.svg.styles.SVG_FillColor;
import com.s8.pkgs.io.svg.styles.SVG_StrokeColor;
import com.s8.pkgs.io.svg.styles.SVG_StrokeSolidity;
import com.s8.pkgs.io.svg.styles.SVG_StrokeThickness;

public class GenerateFunc {

	public static void main(String[] args) {
		
		JS_CodeGenerator gen = new JS_CodeGenerator();
		
		SVG_StrokeThickness.JS_generateFunction(gen);
		gen.skipLines(2);
		
		SVG_StrokeSolidity.JS_generateFunction(gen);
		gen.skipLines(2);
		
		SVG_StrokeColor.JS_generateFunction(gen);
		gen.skipLines(2);
		
		SVG_FillColor.JS_generateFunction(gen);
		
		System.out.print(gen.getCode());
	}

}
