
import { BoundingBox, WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Shape } from "./WebSVG_Shape.js";


/**
 * 
 */
export class WebSVG_Polyline extends WebSVG_Shape {



	/** @type{number[]} */
	coordinates;


    constructor(){
        super();

		/* <polyline points="100,100 150,25 150,75 200,0" fill="none" stroke="black" /> */
		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "polyline");
		this.SVG_node.classList.add("websvg-shape");
		this.setDefaultStroke();
		
    }


	/**
	 * 
	 * @param {number[]} coordinates 
	 */
	S8_set_coordinates(coordinates){
		this.coordinates = coordinates;
		this.requestRedraw();
	}

	
	/**
	 * 
	 * @param {WebSVG_Viewport} viewport 
	 */
	redraw(viewport){
		this.updateStroke(viewport);

		const nPoints = this.coordinates.length / 2;
		const points = new Array(nPoints);
		for(let i = 0; i<nPoints; i++){
			const x = viewport.xTranform(this.coordinates[2*i+0]);
			const y = viewport.yTranform(this.coordinates[2*i+1]);
			points[i] = `${x.toPrecision(6)},${y.toPrecision(6)}`;
		}
		this.SVG_node.setAttribute("points", points.join(' '));
	}


	/**
	 * 
	 * @param {BoundingBox} boundingBox 
	 */
	updateBoundingBox(boundingBox){
		if(this.isBoundingBoxRelevant){
			const n = this.coordinates.length;
			for(let i = 0; i<n; i+=2){
				boundingBox.update(this.coordinates[i+0], this.coordinates[i+1]);
			}
		}
	}
}