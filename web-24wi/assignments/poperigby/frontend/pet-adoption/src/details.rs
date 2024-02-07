use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct DetailsProps {
    id: i64,
}

pub fn Details(cx: Scope<DetailsProps>) -> Element {
    cx.render(rsx! {
        h2 {
            cx.props.id.to_string()
        }
    })
}
