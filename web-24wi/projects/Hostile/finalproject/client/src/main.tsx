import React from "react";
import ReactDOM from "react-dom/client";
import "./styles.css";
import Desktop from "./Desktop";
import Menu from "./components/menu";
import Window from "./components/window";



ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>

    <Desktop content={ <Window content={ <Menu /> } /> } />

  </React.StrictMode>,
);