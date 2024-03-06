import React from "react";
import ReactDOM from "react-dom/client";
import Profile from "../src/components/profile"
import "../src/styles.css";
import axios from 'axios';
import CatchAll from "../src/components/CatchAll";


// const apiCall = () => {
//   axios.get('http://localhost:3001').then((data) => {
//     //this console.log will be in our frontend console
    
//     console.log(data)
    
//   })
// }

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <CatchAll />
  </React.StrictMode>,
);

// apiCall();