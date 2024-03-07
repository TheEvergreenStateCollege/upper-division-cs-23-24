import React from "react";
import ReactDOM from "react-dom/client";
// import Browser from "./browser"
import "./styles.css";
import axios from 'axios';
import Browser from "./components/browser";
import Register from "./components/landForm";
import Profile from "./components/profile";
import Desktop from "./Desktop";
import Post from "./components/Post"
import Menu from "./components/menu";
import Window from "./components/window";
import CatchAll from "./components/CatchAll";
import Filled from "./components/test";
import GetUsers from "./components/users";



// const apiCall = () => {
//   axios.get('http://localhost:3001').then((data) => {
//     //this console.log will be in our frontend console
    
//     console.log(data)
    
//   })
// }

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Window content={<Menu />}/>
  </React.StrictMode>,
);

// apiCall();