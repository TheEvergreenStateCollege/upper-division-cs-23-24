import React from "react";
import { Button } from "./components/Button";
import { ButtonProps } from "./components/Button";



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

  const buttons: ButtonProps[] = [
    {
      onClick: () => handleSelectItem("submit"),
      children: "Button 1",
    },
    {
      onClick: () => handleSelectItem("cancel"),
      children: "Button 2",
    },
    // Add more buttons as needed
  ];

  return (
    <div className="">
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row">
        <div className="basis-2/5 flex flex-col">
              <div><button onClick={subb} className=" mx-2 mt-8 px-4 text-sm   hover:text-white  hover:bg-blue-900 hover:bg-opacity-50" >
              <img src="/static/sign.png" />
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
        <div className="basis-2/5 flex flex-col">
        {buttons.map((buttonProps, index) => (
          <div key={index} className="mx-2 mt-8 p-2 border border-gray-700">
            <Button {...buttonProps} />
          </div>
        ))}
        </div>
        
      </div>
      
      <nav className="fixed bottom-0 bg-gray-400 flex flex-wrap items-center justify-between w-full h-10 text-lg border-t-2 border-opacity-75">
        <button className="mx-2 px-2 shadow-lg border-t-2 border-l-2 border-opacity-75" >
          <div className="flex flex-row">
          <img src="/static/win.png"/>
          <span className="text-sm px-2">Start</span>
          </div>

        </button>
 </nav>
    </div>
    
  );
}

export default App;
