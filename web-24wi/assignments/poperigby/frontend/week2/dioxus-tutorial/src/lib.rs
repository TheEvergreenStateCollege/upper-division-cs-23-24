mod hacker_news;

use chrono::{DateTime, Utc};
use dioxus::prelude::*;
use hacker_news::get_stories;
use serde::{Deserialize, Serialize};

#[component]
pub fn Stories(cx: Scope) -> Element {
    let stories = use_future(cx, (), |_| get_stories(10));

    // Check if the future is resolved
    match stories.value() {
        Some(Ok(list)) => {
            // If it is, render the stories
            render! {
                div {
                    for story in list {
                        StoryListing { story: story.clone() }
                    }
                }
            }
        }
        Some(Err(err)) => {
            render! { "An error occurred while fetching stories: {err}" }
        }
        None => {
            // If a future is yet to resolve, render a loading page
            render! { "Loading items..." }
        }
    }
}

#[component]
pub fn Preview(cx: Scope) -> Element {
    let preview_state = use_shared_state::<PreviewState>(cx)?;

    match &*preview_state.read() {
        PreviewState::Unset => render! {
            "Hover over a story to preview it here"
        },
        PreviewState::Loading => render! {
            "Loading..."
        },
        PreviewState::Loaded(story) => {
            let title = &story.item.title;
            let url = story.item.url.as_deref().unwrap_or_default();
            let text = story.item.text.as_deref().unwrap_or_default();

            render! {
                div {
                    padding: "0.5rem",
                    div {
                        font_size: "1.5rem",
                        a {
                            href: "{url}",
                            "{title}"
                        }
                    },
                    div {
                        dangerous_inner_html: "{text}",
                    }
                    for comment in &story.comments {
                        Comment { comment: comment.clone() }
                    }
                }
            }
        }
    }
}

#[derive(Clone, Debug)]
pub enum PreviewState {
    Unset,
    Loading,
    Loaded(StoryPageData),
}

#[component]
fn Comment(cx: Scope, comment: Comment) -> Element<'a> {
    render! {
        div {
            padding: "0.5rem",
            div {
                color: "gray",
                "by {comment.by}",
            }
            div {
                dangerous_inner_html: "{comment.text}"
            }
            for kid in &comment.sub_comments {
                Comment { comment: kid.clone() }
            }
        }
    }
}

#[component]
fn StoryListing(cx: Scope, story: StoryItem) -> Element {
    let preview_state = use_shared_state::<PreviewState>(cx).unwrap();

    let StoryItem {
        title,
        url,
        by,
        score,
        time,
        kids,
        id,
        ..
    } = story;

    let url = url.as_deref().unwrap_or_default();
    let hostname = url
        .trim_start_matches("https://")
        .trim_start_matches("http://")
        .trim_start_matches("www.");
    let score = format!("{score} {}", if *score == 1 { "point" } else { "points" });
    let comments = format!(
        "{} {}",
        kids.len(),
        if kids.len() == 1 {
            " comment"
        } else {
            " comments"
        }
    );
    let time = time.format("%D %l:%M %p");

    cx.render(rsx! {
        div {
            padding: "0.5rem",
            position: "relative",
            onmouseenter: move |_| {
                // Set the preview_state to this story
                *preview_state.write() = PreviewState::Loaded(StoryPageData {
                    item: story.clone(),
                    comments: vec![]
                })
            },
            div {
                font_size: "1.5rem",
                a {
                    href: url,
                    onfocus: move |_event| {
                    // Set the preview_state to this story
                        *preview_state.write() = PreviewState::Loaded(StoryPageData {
                            item: story.clone(),
                            comments: vec![]
                        })
                    },
                    "{title}"
                }
                a {
                    color: "gray",
                    href: "https://news.ycombinator.com/from?site={hostname}",
                    text_decoration: "none",
                    " ({hostname})"
                }
            }
            div {
                display: "flex",
                flex_direction: "row",
                color: "gray",
                div {
                    "{score}"
                }
                div {
                    padding_left: "0.5rem",
                    "by {by}"
                }
                div {
                    padding_left: "0.5rem",
                    "{time}"
                }
                div {
                    padding_left: "0.5rem",
                    "{comments}"
                }
            }
        }
    })
}

/// Represents a Hacker News story page
#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct StoryPageData {
    #[serde(flatten)]
    item: StoryItem,
    #[serde(default)]
    comments: Vec<Comment>,
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct StoryItem {
    id: i64,
    title: String,
    url: Option<String>,
    text: Option<String>,
    #[serde(default)]
    by: String,
    #[serde(default)]
    score: i64,
    #[serde(default)]
    descendants: i64,
    #[serde(with = "chrono::serde::ts_seconds")]
    time: DateTime<Utc>,
    #[serde(default)]
    kids: Vec<i64>,
    r#type: String,
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct Comment {
    id: i64,
    #[serde(default)]
    by: String,
    #[serde(default)]
    text: String,
    #[serde(with = "chrono::serde::ts_seconds")]
    time: DateTime<Utc>,
    #[serde(default)]
    kids: Vec<i64>,
    #[serde(default)]
    sub_comments: Vec<Comment>,
    r#type: String,
}
