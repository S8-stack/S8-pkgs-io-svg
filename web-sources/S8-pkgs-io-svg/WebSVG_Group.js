



import { S8WebFront } from "/S8-pkgs-ui-carbide/S8WebFront.js";

import { WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Element } from "/S8-pkgs-io-svg/WebSVG_Element.js";



S8WebFront.CSS_import("/S8-pkgs-io-svg/WebSVG.css");


/**
 * 
 */
export class WebSVG_Group extends WebSVG_Element {



    /**
     * @type{ WebSVG_Element[]}
     */
    elements = [];


    constructor() {
        super();

        this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "g");

    }


    getEnvelope() {
        return this.SVG_node;
    }


    /**
     * 
     * @param {WebSVG_Element[]} elements 
     */
    S8_set_elements(elements) {
        // clear canvas current nodes
        while (this.SVG_node.hasChildNodes()) { this.SVG_node.removeChild(this.SVG_node.lastChild); }

        this.elements = elements;

        const nElements = this.elements.length;

        /* update bounding box of the view port */


        let element;
        for (let i = 0; i < nElements; i++) {
            element = elements[i];

            // append node
            this.SVG_node.appendChild(element.SVG_node);
        }

        this.isRedrawingRequired = true;
    }


    S8_set_clickable(isClickable){
        if(isClickable){
            this.SVG_node.classList.add("websvg-clickable-group");
        }
        else{
            this.SVG_node.classList.remove("websvg-clickable-group");
        }
    }

    /**
     * 
     * @param {BoundingBox} boundingBox 
     */
    updateBoundingBox(boundingBox) {
        if (this.isBoundingBoxRelevant) {
            const n = this.elements.length;
            for (let i = 0; i < n; i++) {
                this.elements[i].updateBoundingBox(boundingBox);
            }
        }
    }


    /**
     * 
     * @param {WebSVG_Viewport} viewport 
     */
    redraw(viewport) {
        const n = this.elements.length;
        for (let i = 0; i < n; i++) {
            this.elements[i].redraw(viewport);
        }
    }




    S8_render() {
    }


    S8_dispose() {  /* nothing to dispose */ }
}