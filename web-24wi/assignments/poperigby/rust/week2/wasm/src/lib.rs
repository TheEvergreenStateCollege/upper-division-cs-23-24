use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
use web_sys::{WebGlProgram, WebGlRenderingContext, WebGlShader};

extern crate js_sys;

#[wasm_bindgen]
pub fn add(a: i32, b: i32) -> i32 {
    a + b
}
