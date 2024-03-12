import React from "react";
import ReactDOM from "react-dom/client";
import "../src/styles.css";
import GetUsers from "../src/components/users"


// const apiCall = () => {
//   axios.get('http://localhost:3001').then((data) => {
//     //this console.log will be in our frontend console
    
//     console.log(data)
    
//   })
// }

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <GetUsers />
  </React.StrictMode>,
);

// apiCall();