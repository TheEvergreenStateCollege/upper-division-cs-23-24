use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
use web_sys::{HtmlCanvasElement, WebGlProgram, WebGlRenderingContext, WebGlShader};

extern crate js_sys;

pub fn init_webgl_context(canvas_id: &str) -> Result<WebGlRenderingContext, JsValue> {
    // Create an HTML document context
    let document = web_sys::window().unwrap().document().unwrap();

    // Create a WebGL canvas
    let canvas = document.get_element_by_id(canvas_id).unwrap();
    let canvas = canvas.dyn_into::<HtmlCanvasElement>()?;

    // Create a rendering context (duh)
    let rendering_context = canvas
        .get_context("webgl")?
        .unwrap()
        .dyn_into::<WebGlRenderingContext>()
        .unwrap();

    // Setup a viewport in the rendering context, making it the size of the canvas.
    rendering_context.viewport(
        0,
        0,
        canvas.width().try_into().unwrap(),
        canvas.height().try_into().unwrap(),
    );

    Ok(rendering_context)
}

pub fn create_shader(
    rendering_context: &WebGlRenderingContext,
    shader_type: u32,
    source: &str,
) -> Result<WebGlShader, JsValue> {
    // Attempt to create a new shader with given type. Return a JS error if it can't
    let shader = rendering_context
        .create_shader(shader_type)
        .ok_or_else(|| JsValue::from_str("Unable to create shader object"))?;

    // Set the source code of the newly (hopefully) created shader to the source provided. Then we
    // can compile the shader.
    rendering_context.shader_source(&shader, source);
    rendering_context.compile_shader(&shader);

    // If the shader has successfully compiled, then we can return it from the function.
    if rendering_context
        .get_shader_parameter(&shader, WebGlRenderingContext::COMPILE_STATUS)
        .as_bool()
        .unwrap_or(false)
    {
        Ok(shader)
    // If it fails, return the error from the shader log. If we can't get the error from the shader
    // log, uh oh.
    } else {
        Err(JsValue::from_str(
            &rendering_context
                .get_shader_info_log(&shader)
                .unwrap_or_else(|| "Unknown error while creating shader".to_string()),
        ))
    }
}

/// Creates a WebGL shader program, attaches our vertex and fragment shaders to it, checks for
/// errors, and then returns it.
pub fn setup_shaders(rendering_context: &WebGlRenderingContext) -> Result<WebGlProgram, JsValue> {
    // Create a vertex shader
    let vertex_shader_source = "
        attribute vec3 cordinates;

        void main(void) {
            gl_Position = vec4(coordinates, 1.0);
        }
        ";
    let vertex_shader = create_shader(
        rendering_context,
        WebGlRenderingContext::VERTEX_SHADER,
        vertex_shader_source,
    )
    .unwrap();

    // Create a fragment shader
    let fragment_shader_source = "
        precision mediump float;
        uniform vec4 fragColor;

        void main(void) {
            gl_FragColor = fragColor;
        }
        ";
    let fragment_shader = create_shader(
        rendering_context,
        WebGlRenderingContext::FRAGMENT_SHADER,
        fragment_shader_source,
    )
    .unwrap();

    // Create a new WebGL shader program
    let shader_program = rendering_context.create_program().unwrap();

    // Attach the vertex and fragment shader to our shader program
    rendering_context.attach_shader(&shader_program, &vertex_shader);
    rendering_context.attach_shader(&shader_program, &fragment_shader);

    // Link the shader program to the rendering context
    rendering_context.link_program(&shader_program);

    // If the shader program is able to link to the rendering context, we can set it as
    // part of the current rendering state and return it.
    if rendering_context
        .get_program_parameter(&shader_program, WebGlRenderingContext::LINK_STATUS)
        .as_bool()
        .unwrap()
    {
        rendering_context.use_program(Some(&shader_program));
        Ok(shader_program)
    // Return an error if we're unable to link
    } else {
        Err(JsValue::from_str(
            &rendering_context
                .get_program_info_log(&shader_program)
                .unwrap_or_else(|| "Unknown error while linking shader program".to_string()),
        ))
    }
}

// Setup up the given array of vertices, and sets them up so WebGL can use them
pub fn setup_vertices(
    rendering_context: &WebGlRenderingContext,
    vertices: &[f32],
    shader_program: &WebGlProgram,
) {
    // Create a JS array based on our passed in vertices
    let vertices = unsafe { js_sys::Float32Array::view(vertices) };

    // Create a vertex buffer
    let vertex_buffer = rendering_context.create_buffer().unwrap();

    // Bind the vertex buffer to our rendering context at the ARRAY_BUFFER binding port (stores vertex
    // attributes, such as coordinates)
    rendering_context.bind_buffer(WebGlRenderingContext::ARRAY_BUFFER, Some(&vertex_buffer));
    rendering_context.buffer_data_with_array_buffer_view(
        WebGlRenderingContext::ARRAY_BUFFER,
        &vertices,
        WebGlRenderingContext::STATIC_DRAW,
    );

    // Find the coordinates attribute in our shader program, which tells us how to interpret the
    // vertex data
    let coordinates_location = rendering_context.get_attrib_location(shader_program, "coordinates");

    // rendering_context.bind_buffer(WebGlRenderingContext::ARRAY_BUFFER, Some(&vertex_buffer));
    // Tell WebGL how to read the vertex data from the buffer:
    //   * Store them in the coordinates array attribute
    //   * A vertex is made up of three values (x, y, z)
    //   * Each coordinates is a float
    //   * They're normalized
    //   * They're tightly packed
    rendering_context.vertex_attrib_pointer_with_i32(
        coordinates_location as u32,
        3,
        WebGlRenderingContext::FLOAT,
        false,
        0,
        0,
    );
    rendering_context.enable_vertex_attrib_array(coordinates_location as u32);
}
