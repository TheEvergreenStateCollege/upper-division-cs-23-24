import React from "react";
import ReactDOM from "react-dom/client";
import Landform from "../src/components/landForm"
import "../src/styles.css";


ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Landform />
  </React.StrictMode>,
);

