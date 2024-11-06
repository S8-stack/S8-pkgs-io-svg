


import { NeObject } from "/S8-core-bohr-neon/NeObject.js";

import { S8WebFront } from "/S8-pkgs-ui-carbide/S8WebFront.js";

import { BoundingBox, WebSVG_Viewport } from "/S8-pkgs-io-svg/WebSVG.js";
import { WebSVG_Element } from "/S8-pkgs-io-svg/WebSVG_Element.js";



S8WebFront.CSS_import("/S8-pkgs-io-svg/WebSVG.css");


/**
 * 
 */
export class WebSVG_Canvas extends NeObject {



    /**
     * @type{ WebSVG_Element[]}
     */
    elements = [];


    /**
     * @type {WebSVG_Viewport}
     */
    viewport = new WebSVG_Viewport();

    /**
     * @type{boolean}
     */
    isRedrawingRequired = false;


    /**
     * @type{SVGElement}
     */
    canvasNode;

    constructor() {
        super();

        this.canvasNode = document.createElementNS("http://www.w3.org/2000/svg", 'svg');
        this.canvasNode.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        //this.canvasNode.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
      
        this.canvasNode.classList.add("websvg-canvas");
        this.canvasNode.setAttribute("viewBox", `0 0 ${this.viewport.width} ${this.viewport.height}`);
    }


    getEnvelope(){
        return this.canvasNode;
    }


    /* <viewport> */
    S8_set_viewportWidth(width){ this.viewport.width = width;  this.isRedrawingRequired = true; }
    S8_set_viewportHeight(height){  this.viewport.height = height;  this.isRedrawingRequired = true; }
    S8_set_viewportMarginTop(margin){  this.viewport.marginTop = margin;  this.isRedrawingRequired = true; }
    S8_set_viewportMarginRight(margin){ this.viewport.marginRight = margin;  this.isRedrawingRequired = true; }
    S8_set_viewportMarginBottom(margin){  this.viewport.marginBottom = margin;  this.isRedrawingRequired = true; }
    S8_set_viewportMarginLeft(margin){  this.viewport.marginLeft = margin;  this.isRedrawingRequired = true; }
    /* </viewport> */

    /**
     * 
     * @param {WebSVG_Element[]} elements 
     */
    S8_set_elements(elements) {
        // clear canvas current nodes
        while(this.canvasNode.hasChildNodes()){ 
            this.canvasNode.removeChild(this.canvasNode.lastChild); }

        this.elements = elements;

        const nElements = this.elements.length;

        /* update bounding box of the view port */
      

        let element;
        for(let i = 0; i<nElements; i++){
            element = elements[i];

            // link
            element.canvas = this;

            // append node
            this.canvasNode.appendChild(element.SVG_node);
        }

        this.isRedrawingRequired = true;
    }


    /**
     * 
     */
    redraw() {
        
        this.canvasNode.setAttribute("viewBox", `0 0 ${this.viewport.width} ${this.viewport.height}`);

        if (this.elements != null) {
            
            /* update bounding box */
            const boundingBox = new BoundingBox();
            const nElements = this.elements.length;
            for(let i = 0; i<nElements; i++){
                const element = this.elements[i];
                if(element.isBoundingBoxRelevant){
                    element.updateBoundingBox(boundingBox);
                }
            }

            /* rescale */
            this.viewport.rescale(boundingBox);

            /* redraw */
            for(let i = 0; i<nElements; i++){
                this.elements[i].redraw(this.viewport);
            }
        }
    }



    S8_render() {
        if(this.isRedrawingRequired){
            this.redraw();
            this.isRedrawingRequired = false;
        }
    }


	S8_dispose(){  /* nothing to dispose */ }
}


