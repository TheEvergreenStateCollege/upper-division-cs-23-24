import React from "react";
import ReactDOM from "react-dom/client";
import Profile from "../src/components/profile"
import "../src/styles.css";
import Menu from "../src/components/menu";
import Window from "../src/components/window";


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