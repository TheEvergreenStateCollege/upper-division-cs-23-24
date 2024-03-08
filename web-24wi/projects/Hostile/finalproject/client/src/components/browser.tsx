import React, { useState } from "react";

import { render } from "react-dom";
import Landing from "./landing"
import WindowButtons from "./WindowButtons";


function Browser ({content}: {content?:any}) {
  
  
 
  const [colo,setColo] = useState("bg-backblue");
  const [panl,setPanl] = useState("bg-panelgray");
  const [whi, setWhi] = useState("bg-white");
  const [text, setText] = useState("text-black");
  const [changed,change] = useState(false)
  
  

  const url = window.location.href;
  const [sizeb,setSizeb] = useState(60);
  const [sizer,setSizer] = useState(6);
  const [sizet,setSizet] = useState(8);
  const[enlarged,enlarge]= useState(false);
  
  
  function invColors() {
    if (!changed){
      setColo("bg-invert");
      setPanl("bg-invpan");
      setWhi("bg-black");
      setText("text-white");
      change(true);
    }
    else {
      setColo("bg-backblue");
      setPanl("bg-panelgray");
      setWhi("bg-white");
      setText("text-black");
      change(false);
    }
    
  }

  console.log("sizeb:", sizeb);
  console.log("sizer:", sizer);
  console.log("sizet:", sizet)
  return (
    
    <div className={` ${colo}`}>
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row  grow h-full ">
        <div className=" flex flex-col grow h-full  min-h-screen ">
              <div><button className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
              <a href="/landing">
              <img src="/static/sign.png" /> </a>
                Sign In</button></div>
              <div><button  className="mx-2 mt-8 px-4 text-sm hover:text-white  hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/post">
              <img src="/static/post.png"/></a>
                Post
                </button></div>
              <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/menu">
              <img src="/static/disc.png"/>
                Discover</a>
                </button></div>

                <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/">
              <img className="object-contain h-14" src="/static/abyss.png"/>
                Abyss
                </a>
                </button></div>
        </div>
        <div className={` flex justify-stretch flex-col  ${panl} ${enlarged ? 'mb-6 mr-0 mt-0' : 'mb-60 mr-6 mt-8'} w-full border border-cus `}>
          <div className={`flex flex-row bg-bargray border-b  mx-1`}>
            
            <img className="object-contain" src="/static/inet.png"/>
            <p className="w-full px-1 text-sm text-white">Hostile - Microsoft Internet Explorer</p>
            {/* <WindowButtons /> */}
            <div className="window-buttons top-0 right-0 w-1/2 ">

            <div className={" flex justify-end p-1 "}>

                <a href="/bluescreen">
                <button
                    className={`${panl} text-xs pr-1 pl-2 border border-cus`}>
                    _
                </button>
                </a>
                <a>
                <button onClick={() =>{
                  if (enlarged){
                    enlarge(false);
                    
                  }
                  else{
                    enlarge(true);
                  }
                  
                  }} 
                  className= {`${panl} text-xs pr-1 pl-1 border border-cus`}>
                    □
                </button>
                </a>
                <a  href="/">
                <button className={`${panl} text-xs ml-1 pr-2 pl-2  border border-cus`}>
                    
                    x
                </button>
                </a>
            </div>
        </div>
          </div>
          
          <div className="flex flex-row mt-1  ">
            <div className="ml-1 px-1 border border-cus">Address</div>
            <div className={`${whi} w-full border border-cus2 ${text} mr-1 px-1 flex flex-row`}>
              <img className=" pr-1 self-center" src="/static/inet.png"/>
              <span>{url}</span>
              
            </div>
          </div>
          <div className="flex flex-col h-full  mr-1  ml-1 mt-1 border-l-2 border-r border-t-2 border-bargray  ">
            <div className={`flex flex-row  w-full basis-1/6   ${whi}`}>
              <img className="object-contain h-16 " src="/static/logo2.png"/>
              
            </div>
            <div className={`flex flex-row basis-5/6 ${whi} ${text} border-b border-bargray border-opacity-60  justify-center h-full`}>
              
              {content}
              {/* <Landing></Landing> */}
              

              
            </div>
          </div>

          <div className={`flex flex-row ${panl} ${text}  border-b border-black`}>
          <img className="my-1 px-1 self-center" src="/static/inet.png"/>
          <p className="text-sm self-center">Done</p>
          </div>
        </div>

        
      </div>
      
      <nav className={`fixed bottom-0 ${panl} ${text} flex flex-row items-center justify-between w-full h-7 text-lg border-t `}>
       
       <button onClick={invColors} className="mx-2 px-2 shadow-lg border-t-2 border-l-2 border-opacity-75" >
          <div className="flex flex-row">
            <img src="/static/win.png"/>
            <span className="text-sm px-2">Start</span>
          </div>
         </button>
       
        
      </nav>
    </div>
    
  );
}

export default Browser
