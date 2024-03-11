import { useState } from "react";
import WindowButtons from "./WindowButtons";

const Window = ({ content }: { content?: any }) => {
  const [enlarged, setEnlarged] = useState(false);

  // Callback functions for button actions
  const handleMinimize = () => {
    // rick roll
    console.log("Minimize action");
  };

  const handleMaximize = () => {
    // max hedrum gif
    console.log("Maximize action");
  };

  const handleClose = () => {
    // go to landing page
    console.log("Close action");
  };

  return (
      <div className="flex flex-col">
        <div
            className={`flex justify-stretch flex-col bg-panelgray 20 border border-cus`}
        >
          <div className={`flex flex-row bg-blue-900 mx-1`}>
            <img
                src="/channels/eolIM.jpeg"
                className="mx-auto h-8"
                alt={"alt=\"evergreen online messenger image\""}
            />
            <p className="w-full px-1 text-lg text-white">Main Menu</p>
            <WindowButtons
                onMinimize={handleMinimize}
                onMaximize={handleMaximize}
                onClose={handleClose}
            />
          </div>
          <div
              className={`mx-2 my-2 border-2 border-bargray md:flex-1 overflow-y-auto`}
          >
            <div
                className={`flex flex-row text-black`}
            >
              {content}
            </div>
          </div>
        </div>
      </div>
  );
};

export default Window;
