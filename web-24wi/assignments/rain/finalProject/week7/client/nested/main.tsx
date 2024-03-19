import React from "react";
import ReactDOM from "react-dom/client";
import Browser from "./browser";
import "./styles.css";
import axios from 'axios';


// const apiCall = () => {
//   axios.get('http://localhost:3001').then((data) => {
//     //this console.log will be in our frontend console
    
//     console.log(data)
    
//   })
// }

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Browser />
  </React.StrictMode>,
);

// apiCall();