import React from "react";
import Form from "./form"
import { render } from "react-dom";


function Browser() {
  const handleSelectItem = (item: string) => {
    console.log(item);
  };
  const subb = () => { 
    handleSelectItem("submit")
  } 
  const canc = () => {
      handleSelectItem("cancel")
  }

 
  const url = window.location.href;


 


  return (
    
    <div>
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row  grow h-full ">
        <div className=" flex flex-col grow h-full  min-h-screen ">
              <div><button onClick={subb} className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
              <a href="entry">
              <img src="/static/sign.png" /> </a>
                Sign In</button></div>
              <div><button onClick={canc} className="mx-2 mt-8 px-4 text-sm hover:text-white  hover:bg-blue-900 hover:bg-opacity-50">
              <img src="/static/post.png"/>
                Post
                </button></div>
              <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <img src="/static/disc.png"/>
                Discover
                </button></div>
        </div>
        <div className="flex justify-stretch flex-col overflow-y-scroll bg-panelgray mb-20 mt-8  w-full mr-6 border border-cus ">
          <div className="flex flex-row bg-bargray border-b  mx-1">
            <img className="" src="/static/inet.png"/>
            <p className=" px-1 text-sm text-white">Hostile - Microsoft Internet Explorer</p>
          </div>
          <div className="flex flex-row mt-1  ">
            <div className="ml-1 px-1 border border-cus">Address</div>
            <div className="bg-white w-full border border-cus2  mr-1 px-1 flex flex-row">
              <img className=" pr-1 self-center" src="/static/inet.png"/>
              <span>{url}</span>
              
            </div>
          </div>
          <div className="flex flex-col h-full  mr-1  ml-1 mt-1 border-l-2 border-r border-t-2 border-bargray  ">
            <div className="flex flex-row text-yellow-300 w-full basis-1/6 justify-center bg-white">
              <p className="text-4xl mt-4">Hostile logo</p>
            </div>
            <div className="flex flex-row basis-5/6 bg-white border-b border-bargray border-opacity-60  justify-center h-full">
              {/* <form action="">
                <label htmlFor="uname">User Name:</label>
                <input className=" mx-2 mt-20 px-2 border border-cus min-w-40" type="text" name="Username" id="uname"/><br></br>
                <label htmlFor="pword">Password:</label>
                <input className=" mx-2 mt-1 px-3 border border-cus min-w-40" type="password" name="Password" id="pword"/> <br></br>
                <button className="mt-2 px-2 bg-panelgray border border-cus" type="submit">  Register  </button>
              </form> */}
              <Form></Form>

              
            </div>
          </div>

          <div className="flex flex-row bg-panelgray  border-b border-black">
          <img className="my-1 px-1 self-center" src="/static/inet.png"/>
          <p className="text-sm self-center">Done</p>
          </div>
        </div>

        
      </div>
      
      <nav className="fixed bottom-0 bg-panelgray flex flex-row items-center justify-between w-full h-7 text-lg border-t ">
       <a href="bluescreen">
        <button  className=" mt-2 px-2 shadow-lg border-2 border-cus " >
          <div className="flex flex-row">
          <img src="/static/win.png"/>
          <span className="text-sm self-center px-2">Start</span>
          </div>
          

        </button>
        </a>
        
 </nav>
    </div>
    
  );
}

export default Browser;
