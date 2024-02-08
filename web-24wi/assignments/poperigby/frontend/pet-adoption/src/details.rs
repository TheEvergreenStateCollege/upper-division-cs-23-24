use crate::api::{fetch_pet, QueryKeys};
use dioxus::prelude::*;
use dioxus_query::prelude::*;

#[derive(PartialEq, Props)]
pub struct DetailsProps {
    id: usize,
}

pub fn Details(cx: Scope<DetailsProps>) -> Element {
    let pet = use_query(cx, || vec![QueryKeys::Pet(cx.props.id)], fetch_pet);

    cx.render(rsx! {
        h2 {
            cx.props.id.to_string()
        }
    })
}
