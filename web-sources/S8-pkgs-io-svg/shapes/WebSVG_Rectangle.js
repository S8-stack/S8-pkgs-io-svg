
import { BoundingBox, WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Shape } from "./WebSVG_Shape.js";




/**
 *  <!-- Simple rect element -->
  <rect x="0" y="0" width="100" height="100" />

  <!-- Rounded corner rect element -->
  <rect x="120" y="0" width="100" height="100" rx="2" ry="15" />
 */
export class WebSVG_Rectangle extends WebSVG_Shape {


	/** @type{number} x-coordinate Top left corner of the rectangle */
	xBottomLeftCorner = 0.0;

	/** @type{number} y-coordinate Top left corner of the rectangle */
	yBottomLeftCorner = 0.0;

	/** @type{number} */
	width = 10.0;

	/** @type{number} */
	height = 10.0;

	/** @type{boolean} */
	hasRoundedCorners = false

	/** @type{number} */
	radius = -1.0;
    
    constructor(){
        super();

		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "rect");
		this.SVG_node.classList.add("websvg-shape");
		this.setDefaultStroke();
		
    }


	/**
	 * 
	 * @param {number[]} coordinates 
	 */
	S8_set_bottomLeftCorner(coordinates){
		this.xBottomLeftCorner = coordinates[0];
		this.yBottomLeftCorner = coordinates[1];
		this.requestRedraw();
	}

	S8_set_width(width){
		this.width = width;
		this.requestRedraw();
	}

	S8_set_height(height){
		this.height = height;
		this.requestRedraw();
	}

	/**
	 * 
	 * @param {number} radius 
	 */
	S8_set_radius(radius){
		if(radius > 1e-8){
			this.hasRoundedCorners = true;
			this.radius = radius;
		}
		else{
			this.hasRoundedCorners = false;
			this.radius = 0;
		}
		this.requestRedraw();
	}

	
	/**
	 * 
	 * @param {WebSVG_Viewport} viewport 
	 */
	redraw(viewport){
		this.updateStroke(viewport);

		this.SVG_node.setAttribute("x", viewport.xTranform(this.xBottomLeftCorner).toPrecision(6));
		this.SVG_node.setAttribute("y", viewport.yTranform(this.yBottomLeftCorner).toPrecision(6));
		this.SVG_node.setAttribute("width", viewport.sTranform(this.width).toPrecision(6));
		this.SVG_node.setAttribute("height", viewport.sTranform(this.height).toPrecision(6));

		if(this.radius > 0){
			this.SVG_node.setAttribute("rx", viewport.sTranform(this.radius).toPrecision(6));
			this.SVG_node.setAttribute("ry", viewport.sTranform(this.radius).toPrecision(6));
		}
		else{
			this.SVG_node.removeAttribute("rx");
			this.SVG_node.removeAttribute("ry");
		}

		if(this.hasRoundedCorners){
			this.SVG_node.setAttribute("r", viewport.sTranform(this.radius).toPrecision(6));
		}
	}


	/**
	 * 
	 * @param {BoundingBox} boundingBox 
	 */
	updateBoundingBox(boundingBox){
		if(this.isBoundingBoxRelevant){
			boundingBox.update(this.xBottomLeftCorner, this.yBottomLeftCorner);
			boundingBox.update(this.xBottomLeftCorner + this.width, this.yBottomLeftCorner - this.height);
		}
	}
}