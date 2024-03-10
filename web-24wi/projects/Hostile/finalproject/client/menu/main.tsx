import React from "react";
import ReactDOM from "react-dom/client";
import Profile from "../src/components/profile"
import "../src/styles.css";
import Menu from "../src/components/menu";
import Window from "../src/components/window";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Window content={<Menu />}/>
  </React.StrictMode>,
);
