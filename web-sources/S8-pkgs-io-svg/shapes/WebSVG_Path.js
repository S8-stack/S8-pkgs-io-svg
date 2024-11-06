
import { BoundingBox, WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Shape } from "./WebSVG_Shape.js";

import { SVG_Vec, WebSVG_PathElement } from "/S8-pkgs-io-svg/shapes/WebSVG_PathElement.js";




/**
 *  <!-- Simple rect element -->
  <rect x="0" y="0" width="100" height="100" />

  <!-- Rounded corner rect element -->
  <rect x="120" y="0" width="100" height="100" rx="2" ry="15" />
 */
export class WebSVG_Path extends WebSVG_Shape {


	/** @type{WebSVG_PathElement[]} */
	elements;

	/** @type{number} y-coordinate Top left corner of the rectangle */
	yBottomLeftCorner = 0.0;

	/** @type{number} */
	width = 10.0;

	/** @type{number} */
	height = 10.0;

	/** @type{boolean} */
	hasRoundedCorners = false

	/** @type{number} */
	radius = 1.0;

	constructor() {
		super();

		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "path");
		this.SVG_node.classList.add("websvg-shape");
		this.setDefaultStroke();
	}

	S8_set_elements(rawElements) {
		if(rawElements){
			const n = rawElements.length;
			this.elements = new Array(n);
			for(let i = 0; i<n; i++){
				this.elements[i] = WebSVG_PathElement.parse(rawElements[i]);
			}
		}
		else{
			this.elements = [];
		}
		this.requestRedraw();
	}

	static S8_elements_PARSER = function(inflow){
		return WebSVG_PathElement.parse(inflow);
	}


	/**
	 * 
	 * @param {WebSVG_Viewport} viewport 
	 */
	redraw(viewport) {
		this.updateStroke(viewport);

		let build = "";

		const n = this.elements.length;
		for(let i = 0; i<n; i++){
			build = build + (i > 0 ? " " : "") + this.elements[i].SVG_generate(viewport);
		}

		this.SVG_node.setAttribute("d", build);
	}


	/**
	 * 
	 * @param {BoundingBox} boundingBox 
	 */
	updateBoundingBox(boundingBox) {
		if (this.isBoundingBoxRelevant && this.elements) {
			let vec = new SVG_Vec();
			const n = this.elements.length;
			for(let i = 0; i<n; i++){
				this.elements[i].moveToNext(vec);
				boundingBox.update(vec.x, vec.y);
			}
		}
	}
}

