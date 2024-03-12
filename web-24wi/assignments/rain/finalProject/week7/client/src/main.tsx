import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
// import Browser from "./browser"
import "./styles.css";
import axios from 'axios';
import Browser from "./browser";


// const apiCall = () => {
//   axios.get('http://localhost:3001').then((data) => {
//     //this console.log will be in our frontend console
    
//     console.log(data)
    
//   })
// }

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);

// apiCall();