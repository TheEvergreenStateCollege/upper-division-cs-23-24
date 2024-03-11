import React, { useState } from "react";
import WindowButtons from "./WindowButtons";
import VideoModal from "./VideoModal";

const Window = ({ content }: { content?: any }) => {
  const [videoModalOpen, setVideoModalOpen] = useState(false);
  const [videoType, setVideoType] = useState("");

  const handleMinimize = () => {
    setVideoType("minimize");
    setVideoModalOpen(true);
    console.log("Minimize action");
  };

  const handleMaximize = () => {
    setVideoType("maximize");
    setVideoModalOpen(true);
    console.log("Maximize action");
  };

  const handleClose = () => {
    setVideoType("close");
    setVideoModalOpen(true);
    console.log("Close action");
  };

  return (
      <div className="flex flex-col justify-evenly">
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
        {videoModalOpen && (
            <VideoModal
                isOpen={videoModalOpen}
                onRequestClose={() => setVideoModalOpen(false)}
                contentLabel="Video Modal"
                videoId={
                  videoType === "minimize" ? "dQw4w9WgXcQ" :
                      videoType === "maximize" ? "TLfZ5Sb1EGw" :
                          "lAkuJXGldrM"
                }
            />
        )}
      </div>
  );
};

export default Window;
