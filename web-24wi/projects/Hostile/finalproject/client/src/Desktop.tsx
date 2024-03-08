import React, { useState } from "react";


function Desktop({content}:{content?:any}) {
  const handleSelectItem = (item: string) => {
    console.log(item);
  };
  const subb = () => { 
    handleSelectItem("submit")
  } 
  const canc = () => {
      handleSelectItem("cancel")
  }



  const [colo,setColo] = useState("backblue")
  var changed = false;
  const backgrnd = "bg-"+colo
  return (
    // div className={`flex justify-stretch flex-col  bg-panelgray ${margb} ${margt} ${margr}  w-full border border-cus `}>
    <div className={`h-screen ${backgrnd}`}>
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row  grow h-full ">
        <div className="flex flex-col grow h-full  min-h-screen ">
              <div><button onClick={subb} className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
              <a href="landing">
              <img src="/static/sign.png" /> </a>
                Sign In</button></div>
                    <div><button onClick={canc} className="mx-2 mt-8 px-4 text-sm hover:text-white  hover:bg-blue-900 hover:bg-opacity-50">
                    <a href="/post">
              <img src="/static/post.png"/></a>
                Post
                </button></div>
              <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/menu">
              <img src="/static/disc.png"/>
                Discover
                </a>
                </button></div>

                <div><button className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50">
              <a href="/">
              <img className="object-contain h-14" src="/static/abyss.png"/>
                Abyss
                </a>
                </button></div>
        </div>

        
      
      {content}
      </div>
      <nav className="fixed bottom-0 bg-gray-400 flex flex-wrap items-center justify-between w-full h-10 text-lg border-t-2 border-opacity-75">
       {/* <a href="bluescreen"> */}
        <button onClick={() =>{
                  if (!changed){
                    setColo("invert");
                    changed=true;
                  }
                  else {
                    setColo("backblue")
                    changed=false;
                  }
                  
                  }}  
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
