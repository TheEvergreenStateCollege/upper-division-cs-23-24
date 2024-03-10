import icon from "../static/win.png"
import React, { useState, useEffect } from "react";
import Cookies from 'js-cookie';

function Desktop({content}:{content?:any}) {

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




const post = isSignedIn? <img className="object-contain h-16 w-16" src="/static/notebook.png"/> : <img  src="/static/post.png"/> 
const discover = isSignedIn? <img className="object-contain h-16 w-16" src="/static/discover.png"/> : <img  src="/static/disc.png"/>

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
            <img className="object-contain h-14" src="/static/abyss.png"/>
              Abyss
              </a>
      </button></div>
  )
}
  return (
    
    <div className={`  ${colo} bg-backblue`}>
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row  grow h-full ">
        <div className="flex flex-col grow h-full  min-h-screen ">
        {isSignedIn ?  <Profile/> :  <SignIn/>}
              
               
              <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white  hover:bg-blue-900 hover:bg-opacity-50">
                <a href="/post">
                {post}
                    Post
                </a>
              </button></div>

              <div><button className="mx-1 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
                <a href="/menu">
                  {discover}
                  Discover
                </a>
              </button></div>

              {isSignedIn ?  <Abyss/> : null }
        </div>

        
      
      {content}
      </div>
      <nav className={`fixed bottom-0 bg-panelgray text-black flex flex-row items-center justify-between w-full h-7 text-lg border-t `}>
       {/* <a href="bluescreen"> */}
        <button onClick={invColors}  
                className="mx-2 px-2 shadow-lg border-t-2 border-l-2 border-opacity-75" >
          <div className="flex flex-row">
            <img src="/static/win.png"/>
            <span className="text-sm px-2">Start</span>
          </div>
         </button>
        {/* </a> */}
        
      </nav>
    </div>
    
  );
}

export default Desktop;
