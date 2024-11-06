
import { NeObject } from "/S8-core-bohr-neon/NeObject.js";

import { S8WebFront } from "/S8-pkgs-ui-carbide/S8WebFront.js";

import { WebSVG_Canvas } from "/S8-pkgs-io-svg/WebSVG_Canvas.js";

S8WebFront.CSS_import("/S8-pkgs-io-svg/viewer/WebSVG_Viewer.css");


/**
 * 
 */
export class WebSVG_Viewer extends NeObject {


    /**
     * @type{SVGElement}
     */
    canvasNode;

    /**
     * @type{HTMLDivElement}
     */
    wrapperNode;

    /**
    * @type{HTMLDivElement}
    */
    clippingNode;

    /**
     * @type{HTMLDivElement}
     */
    annotationsNode;

    /**
     * @type{HTMLDivElement}
     */
    touchScreenNode;


    constructor() {
        super();

        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("websvg-viewer");

        this.clippingNode = document.createElement("div");
        this.clippingNode.classList.add("websvg-layer-clipping");        
        this.wrapperNode.appendChild(this.clippingNode);

        
        this.annotationsNode = document.createElement("div");
        this.annotationsNode.classList.add("websvg-layer-annotations");       
        this.wrapperNode.appendChild(this.annotationsNode);

        this.touchScreenNode = document.createElement("div");
        this.touchScreenNode.classList.add("websvg-layer-touchscreen");
        this.wrapperNode.appendChild(this.touchScreenNode);
        

    }


    getEnvelope() {
        return this.wrapperNode;
    }


    /**
     * 
     * @param {WebSVG_Canvas} canvas 
     */
    S8_set_canvas(canvas) {

        /** clear already present nodes */
        while (this.clippingNode.lastChild) { this.clippingNode.removeChild(this.clippingNode.lastChild); }

        this.canvasNode = canvas.canvasNode;
        this.clippingNode.appendChild(this.canvasNode);

        /*
        this.canvasNode.style.width = "200vw";
        this.canvasNode.style.position = "absolute";
        this.canvasNode.style.top = "50%";
        this.canvasNode.style.left = "50%";
        this.canvasNode.style.transform = "translate(-50vw, -50vh)";
        */

        /*
        width: 200vw;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-100vw, -50vh);
}   */

    }

  


    S8_render() {
       
    }


    S8_dispose() {  /* nothing to dispose */ }
}