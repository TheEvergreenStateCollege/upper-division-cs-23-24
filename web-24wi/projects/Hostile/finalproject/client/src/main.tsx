import React from "react";
import ReactDOM from "react-dom/client";
import "./styles.css";
import Desktop from "./Desktop";
import Menu from "./components/menu";
import Window from "./components/window";
import Post from "./components/Post";
import Landform from "./components/landForm";
import Browser from "./components/browser";
import Profile from "./components/profile";



ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>


    <Desktop />


  </React.StrictMode>,
);