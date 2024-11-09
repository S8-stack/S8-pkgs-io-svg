


import { S8WebFront } from "/S8-pkgs-ui-carbide/S8WebFront.js";

import { WebSVG, WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Element } from "/S8-pkgs-io-svg/WebSVG_Element.js";



S8WebFront.CSS_import("/S8-pkgs-io-svg/WebSVG.css");


/**
 * 
 */
export class WebSVG_Shape extends WebSVG_Element {



	/**
	 * @type{String} the css suffix for the solid style
	 */
	strokeSolidityStyle = WebSVG.STROKE_SOLIDITY_DEFAULT;


	/**
	 * @type{number}
	 * -1 : disabled
	 */
	strokeThickness = -1;

	strokeSolidity = "SOLID";


	constructor() {
		super();
	}



	setDefaultStroke() {
		this.SVG_node.classList.add("websvg-element");
		this.SVG_node.setAttribute("stroke", "black");
		this.SVG_node.setAttribute("stroke-width", "1");
	}


	/**
	 * 
	 * @param {WebSVG_Viewport} viewport 
	 */
	updateStroke(viewport) {
		let thickness = this.strokeThickness * viewport.width / 1920 * 1.0;
		this.SVG_node.setAttribute("stroke-width", thickness);
	}



	/**
	 * 
	 * @returns 
	 */
	getEnvelope() {
		return this.SVG_node;
	}


	/**
	 * 
	 * @param solidity
	 */
	S8_set_strokeSolidity(code) {
		if(code != WebSVG.DISABLED_FEATURE_CODE){
			this.SVG_node.setAttribute("stroke-dasharray", WebSVG.getStrokeSolidityByCode(code));
		}
		else{
			this.SVG_node.removeAttribute("stroke-dasharray");
		}
	}


	/**
	 * 
	 * @param {number} value 
	 */
	S8_set_strokeThickness(code) {
		if(code != WebSVG.DISABLED_FEATURE_CODE){
			let thickness = WebSVG.getStrokeThicknessByCode(code);
			this.strokeThickness = thickness;
			this.SVG_node.setAttribute("stroke-width", thickness);
		}
		else{
			this.SVG_node.removeAttribute("stroke-width");
		}
	}


	/**
	 * 
	 * @param {number} color
	 */
	S8_set_strokeColor(code) {
		if(code != WebSVG.DISABLED_FEATURE_CODE){
			this.SVG_node.setAttribute("stroke", WebSVG.getStrokeColorByCode(code));
		}
		else{
			this.SVG_node.setAttribute("stroke", "none");
		}
	}


	/**
	 * 
	 * @param {number} color
	 */
	S8_set_fillColor(hexEncoding) {
		if(hexEncoding != 0xa8003d920e) {
			const a = (hexEncoding >> 24) & 0xff;
			const r = (hexEncoding >> 16) & 0xff;
			const g = (hexEncoding >> 8) & 0xff;
			const b = (hexEncoding) & 0xff;
			this.SVG_node.setAttribute("fill", `rgba(${r}, ${g}, ${b}, ${(0xff - a) / 0xff})`);
		}
		else {
			this.SVG_node.setAttribute("fill", 'none');
		}
	}


	requestRedraw() {
		if (this.canvas != null) {
			// notify required redrawing
			this.canvas.isRedrawingRequired = true;
		}
	}


	S8_render() { /* no post-processing */ }

	S8_dispose() {  /* nothing to dispose */ }
}