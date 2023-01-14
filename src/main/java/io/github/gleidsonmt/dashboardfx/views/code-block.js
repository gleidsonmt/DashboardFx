
class CodeTab extends HTMLElement {

  constructor() {
    super();
    this.attachShadow({ mode: "open" });
  }
  connectedCallback() {
    // The Shadow DOM HTML for a <code-tab> element.
    this.shadowRoot.innerHTML = `
        <style>
         :host{display:block;}
        </style>

       <div class="code-snippet">
           <pre><code><slot></slot></code></pre>
       </div>
       `;
  }
}
window.customElements.define("code-tab", CodeTab);