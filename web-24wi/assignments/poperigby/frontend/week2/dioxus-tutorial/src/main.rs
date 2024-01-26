use dioxus::prelude::*;

fn main() {
    dioxus_web::launch(app);
}

fn app(context: Scope) -> Element {
    context.render(rsx! {
    div {
        "Hello, world!"
    }
    })
}
