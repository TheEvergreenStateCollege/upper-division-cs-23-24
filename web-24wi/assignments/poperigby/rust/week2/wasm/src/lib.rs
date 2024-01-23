use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
use web_sys::{HtmlCanvasElement, WebGlProgram, WebGlRenderingContext, WebGlShader};

extern crate js_sys;

pub fn init_webgl_context(canvas_id: &str) -> Result<WebGlRenderingContext, JsValue> {
    // Create an HTML document context with some very naughty unwraps that will probably
    // crash our program.
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
