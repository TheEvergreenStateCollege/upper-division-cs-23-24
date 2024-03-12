import React from "react";
import ReactDOM from "react-dom/client";
import "../src/styles.css";
import Content from "../src/components/feed"

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Content/>
  </React.StrictMode>,
);
