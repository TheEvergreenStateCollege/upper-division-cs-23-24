import React from "react";
import icon from "../static/win.png"



function App() {
  const handleSelectItem = (item: string) => {
    console.log(item);
  };
  const subb = () => { 
    handleSelectItem("submit")
  } 
  const canc = () => {
      handleSelectItem("cancel")
  }





  return (
    <div className="">
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row">
        <div className="basis-2/5 flex flex-col">
              <div><button onClick={subb} className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
              <a href="landing">
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

        
      </div>
      
      <nav className="fixed bottom-0 bg-gray-400 flex flex-wrap items-center justify-between w-full h-10 text-lg border-t-2 border-opacity-75">
       <a href="bluescreen">
        <button  className="mx-2 px-2 shadow-lg border-t-2 border-l-2 border-opacity-75" >
          <div className="flex flex-row">
          <img src="/static/win.png"/>
          <span className="text-sm px-2">Start</span>
          </div>
          

        </button>
        </a>
        
 </nav>
    </div>
    
  );
}

export default App;
