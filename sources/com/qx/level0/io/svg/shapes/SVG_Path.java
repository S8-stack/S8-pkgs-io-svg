package com.qx.level0.io.svg.shapes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qx.level0.io.svg.ViewBox;
import com.qx.level0.maths.MathVector2D;

public class SVG_Path extends SVG_Shape {


	private class Position {
		public double x, y;
	}

	private abstract class Element {

		public abstract void update(Position position, ViewBox box);

		public abstract void print(StringBuilder builder, ViewBox viewBox);

		public abstract Element transform(SVG_Rewriter transform);

	}

	private class MoveTo extends Element {

		private double x, y;

		public MoveTo(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public MoveTo(MathVector2D point) {
			super();
			this.x = point.x;
			this.y = point.y;
		}

		@Override
		public void update(Position p, ViewBox box) {
			p.x=x;
			p.y=y;
			box.updateBoundingBox(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("M"+box.xTransform(x)+','+box.yTransform(y));
		}


		@Override
		public Element transform(SVG_Rewriter transform) {
			return new MoveTo(transform.transformPoint(new MathVector2D(x, y)));
		}
	}


	private class Line extends Element {

		private double dx, dy;

		public Line(double dx, double dy) {
			super();
			this.dx = dx;
			this.dy = dy;
		}

		@Override
		public void update(Position p, ViewBox box) {
			p.x+=dx;
			p.y+=dy;
			box.updateBoundingBox(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("l"+box.dxScale(dx)+","+box.dyScale(dy));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			MathVector2D transformedVector = transform.transformVector(new MathVector2D(dx, dy));
			return new Line(transformedVector.x, transformedVector.y);
		}
	}

	private class Horizontal extends Element {

		private double dx;

		public Horizontal(double dx) {
			super();
			this.dx = dx;
		}

		@Override
		public void update(Position p, ViewBox box) {
			p.x+=dx;
			box.updateBoundingBox(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("h"+box.dxScale(dx));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			MathVector2D transformedVector = transform.transformVector(new MathVector2D(dx, 0)); 
			if(transform.isRotationExact() && Math.abs(transformedVector.y)<1e-6) {
				return new Horizontal(transformedVector.x);
			}
			else if(transform.isRotationExact() && Math.abs(transformedVector.x)<1e-6){
				return new Vertical(transformedVector.y);
			}
			else {
				return new Line(transformedVector.x, transformedVector.y);
			}
		}
	}

	private class Vertical extends Element {

		private double dy;

		public Vertical(double dy) {
			super();
			this.dy = dy;
		}

		@Override
		public void update(Position p, ViewBox box) {
			p.y+=dy;
			box.updateBoundingBox(p.x, p.y);
		}

		@Override
		public void print(StringBuilder builder, ViewBox box) {
			builder.append("v"+box.dyScale(dy));
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			MathVector2D transformedVector = transform.transformVector(new MathVector2D(0, dy)); 
			if(transform.isRotationExact() && Math.abs(transformedVector.y)<1e-6) {
				return new Horizontal(transformedVector.x);
			}
			else if(transform.isRotationExact() && Math.abs(transformedVector.x)<1e-6){
				return new Vertical(transformedVector.y);
			}
			else {
				return new Line(transformedVector.x, transformedVector.y);
			}
		}

	}


	/**
	 *  a rx ry x-axis-rotation large-arc-flag sweep-flag dx dy
	 * @author pc
	 *
	 */
	private class Arc extends Element {

		private double r;
		private boolean isLargeArc;
		private boolean isCounterClockwise;
		private double dx;
		private double dy;

		public Arc(double r, boolean isLargeArc, boolean isCounterClockwise, double dx, double dy) {
			super();
			this.r = r;
			this.isLargeArc = isLargeArc;
			this.isCounterClockwise = isCounterClockwise;
			this.dx = dx;
			this.dy = dy;
		}

		@Override
		public void update(Position position, ViewBox box) {
			position.x+=dx;
			position.y+=dy;
			box.updateBoundingBox(position.x+r, position.y+r);
			box.updateBoundingBox(position.x-r, position.y+r);
			box.updateBoundingBox(position.x-r, position.y-r);
			box.updateBoundingBox(position.x+r, position.y-r);
		}


		/**
		 *  a rx ry x-axis-rotation large-arc-flag sweep-flag dx dy
		 */
		@Override
		public void print(StringBuilder builder, ViewBox box) {
			double scaledRadius = box.scale(r);
			builder.append("a"+scaledRadius+','+scaledRadius+' '+0+' '
					+(isLargeArc?'1':'0')+' ' // large-arc-flag
					+(isCounterClockwise?'1':'0')+' ' // sweep-flag
					+box.dxScale(dx)+','+box.dyScale(dy));
		}

	
		@Override
		public Element transform(SVG_Rewriter transform) {
			MathVector2D transformedVector = transform.transformVector(new MathVector2D(dx, dy)); 
			return new Arc(transform.transformScalar(r), isLargeArc, isCounterClockwise, transformedVector.x, transformedVector.y);
		}
	
	}

	private class CloseLoop extends Element {

		@Override
		public void update(Position position, ViewBox box) {
			// nothing to update
		}

		@Override
		public void print(StringBuilder builder, ViewBox viewBox) {
			builder.append("z");
		}

		
		@Override
		public Element transform(SVG_Rewriter transform) {
			return new CloseLoop();
		}
	

	}

	private List<Element> elements;


	public SVG_Path() {
		super();
		initialize();
	}

	public SVG_Path(String className) {
		super(className);
		initialize();
	}
	
	
	private SVG_Path(String className, List<Element> elements) {
		super(className);
		this.elements = elements;
	}
	
	
	private void initialize() {
		elements = new ArrayList<>();
	}

	public SVG_Path moveTo(double x, double y) {
		elements.add(new MoveTo(x, y));
		return this;
	}

	public SVG_Path line(double dx, double dy) {
		elements.add(new Line(dx, dy));
		return this;
	}

	public SVG_Path horizontal(double dx) {
		elements.add(new Horizontal(dx));
		return this;
	}

	public SVG_Path vertical(double dy) {
		elements.add(new Vertical(dy));
		return this;
	}

	public SVG_Path arc(double r, boolean isLargeArc, boolean isCounterClockwise, double dx, double dy) {
		elements.add(new Arc(r, isLargeArc, isCounterClockwise, dx, dy));
		return this;
	}

	public void close() {
		elements.add(new CloseLoop());
	}

	@Override
	public void updateBoundingBox(ViewBox box) {
		Position position = new Position();
		for(Element element : elements) {
			element.update(position, box);
		}
	}

	@Override
	public void print(StringBuilder builder, ViewBox viewBox) throws IOException {
		builder.append("<path"); // start path
		printAttributes(builder);
		builder.append(" d=\"");
		boolean isFirst = true;
		for(Element element : elements) {
			if(!isFirst) { builder.append(" "); } else { isFirst = false; }
			element.print(builder, viewBox);
		}
		builder.append("\"/>"); // end path	

	}

	
	@Override
	public SVG_Shape rewrite(SVG_Rewriter transform) {
		List<Element> transformedElements = new ArrayList<>(elements.size());
		for(Element element : elements) {
			transformedElements.add(element.transform(transform));
		}
		return new SVG_Path(className, transformedElements);
	}

}
