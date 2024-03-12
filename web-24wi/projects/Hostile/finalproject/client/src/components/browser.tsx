import React, { useState, useEffect } from "react";

import { render } from "react-dom";
import Landing from "./landing"
import WindowButtons from "./WindowButtons";
import Cookies from 'js-cookie';
import ButtonPicker from "./ButtonPicker"
import Desktop from "../Desktop";

function ParitalBrowser ({content}: {content?:any}) {
  
  const [isSignedIn, setIsSignedIn] = useState(false);
  const [colo, setColo] = useState("invert-0");  
  const [changed, change] = useState(false);
  useEffect(() => {
    const uname = Cookies.get('user');
    setIsSignedIn(!!uname);

    if (!!uname) {
      setColo("invert");
      change(true);
    } else {
      setColo("invert-0");
      change(false);
    }
  }, []);
 

  
  const post = isSignedIn? <img className="object-contain " src="/static/notebook.png"/> : <img  src="/static/post.png"/> 
  const discover = isSignedIn? <img className="object-contain " src="/static/discover.png"/> : <img  src="/static/disc.png"/>


  const url = window.location.href;
  const[enlarged,enlarge]= useState(false);
  
  

  function invColors() {
    if (!changed){
      setColo("invert");
  
      change(true);
    }
    else {
      setColo("invert-0");
      change(false);
    }
    
  }


  const SignIn = () => {
    return(
      <div><button className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
          <a href="/landing">
            <img src="/static/sign.png" /> 
              Sign In 
          </a>
        </button></div>
    )
  }
  
  const Profile = () => {
    return(
    <div><button className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
                  <a href="/api/profile">
                    <img className="object-contain " src="/static/profi.jpg" /> 
                      Profile
                  </a>
                </button></div>
    )
  }

  const Abyss = () => {
    return (
      <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/">
              <img className="object-contain " src="/static/abyss.png"/>
                Abyss
                </a>
        </button></div>
    )
  }

  return (
    
    <div className={` flex  flex-col  bg-panelgray shrink h-5/6  w-full ${enlarged ? 'mb-6 mr-0 mt-0' : 'mb-60 mr-6 mt-8'} border border-cus overflow-auto  `}>
          <div className={`flex flex-row bg-bargray border-b  mx-1`}>
            
            <img className="object-contain" src="/static/inet.png"/>
            <p className="w-full px-1 text-sm text-white">Hostile - Microsoft Internet Explorer</p>
            {/* <WindowButtons /> */}
            <div className="window-buttons top-0 right-0 w-1/2 ">

            <div className={" flex justify-end p-1 "}>

                <a href="/bluescreen">
                <button
                    className={`bg-panelgray text-xs pr-1 pl-2 border border-cus`}>
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
                  className= {`bg-panelgray text-xs pr-1 pl-1 border border-cus`}>
                    □
                </button>
                </a>
                <a  href="/">
                <button className={`bg-panelgray text-xs  pr-2 pl-2  border border-cus`}>
                    
                    x
                </button>
                </a>
            </div>
        </div>
          </div>
          
          <div className="flex flex-row mt-1  ">
            <div className="ml-1 px-1 border border-cus">Address</div>
            <div className={`bg-white w-full border border-cus2 text-black mr-1 px-1 flex flex-row`}>
              <img className=" pr-1 self-center" src="/static/inet.png"/>
              <span>{url}</span>
              
            </div>
          </div>
          <div className="flex flex-col   mr-1  ml-1 mt-1 border-l-2 border-r border-t-2 border-bargray  ">
            <div className="shrink max-h-96 overflow-auto">
              <div className={`flex flex-row  w-full  bg-white`}>
                <img className="object-contain h-20 " src="/static/logo.png"/>
                
              </div>
              <div className={` bg-white text-black  border-opacity-60  justify-center w-full `}>
                  {content}
              </div>
              <div className="flex flex-row w-full justify-center bg-white border-b border-bargray ">
                          <ButtonPicker/>
              </div>
            </div>
          </div>
          

          <div className={`flex flex-row bg-panelgray text-black  border-b border-black`}>
          <img className="my-1 px-1 self-center" src="/static/inet.png"/>
          <p className="text-sm self-center">Done</p>
          </div>
        </div>
        
    
  );
}
function Browser({ content }) {
  return (
    <Desktop content={<ParitalBrowser content={content} />} />
  );
}

export default Browser;


