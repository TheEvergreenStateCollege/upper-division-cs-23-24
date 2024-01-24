# Tutorial 
https://blog.logrocket.com/implement-webassembly-webgl-viewer-using-rust

# Things I Learned

* WebGL
    * Canvas/rendering context: The place where you draw all your graphics 🌈
    * Shaders: Small programs that run on the GPU and can manipulate pixels to create rendering effects
        * Vertex shaders: Manipulate vertices
        * Fragment shaders: Can manipulate colors inside a shape. Trying to lookup what it did beyond that broke my brain.
    * Vertex buffer: A buffer where vertex data can be uploaded and modified before drawing to the canvas, instead of directly drawing to the canvas and manipulating vertices afterwards.
