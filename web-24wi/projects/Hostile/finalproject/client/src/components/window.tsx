import React, { useState } from "react";

const Window = ({ content }: { content?: any }) => {
  const [colo, setColo] = useState("bg-backblue");
  const [panl, setPanl] = useState("bg-panelgray");
  const [whi, setWhi] = useState("bg-white");
  const [text, setText] = useState("text-black");
  const [changed, setChanged] = useState(false);

  const [sizeb, setSizeb] = useState(60);
  const [sizer, setSizer] = useState(6);
  const [sizet, setSizet] = useState(8);
  const margb = `mb-${sizeb}`;
  const margr = `mr-${sizer}`;
  const margt = `mt-${sizet}`;
  const [enlarged, setEnlarged] = useState(false);

  const windowButtons = [
    { label: "Sign In", url: "/landing", image: "/desktop/sign.png" },
    { label: "Post", url: "/post", image: "/desktop/post.png" },
    { label: "Discover", url: "", image: "/desktop/disc.png" },
  ];

  const windowControlButtons = [
    { label: "_", onClick: () => console.log("Minimize clicked!") },
    {
      label: "□",
      onClick: () => {
        if (enlarged) {
          setSizeb(60);
          setSizer(6);
          setSizet(8);
          setEnlarged(false);
        } else {
          setEnlarged(true);
          setSizeb(6);
          setSizer(0);
          setSizet(0);
        }
      },
    },
    { label: "x", onClick: () => console.log("Close clicked!") },
  ];

  const toggleColors = () => {
    setColo(changed ? "bg-backblue" : "bg-invert");
    setPanl(changed ? "bg-panelgray" : "bg-invpan");
    setWhi(changed ? "bg-white" : "bg-black");
    setText(changed ? "text-black" : "text-white");
    setChanged(!changed);
  };

  return (
    <div className={` ${colo}`}>
      <div className="flex flex-row grow h-full">
        <div className="flex flex-col grow h-full min-h-screen">
          {windowButtons.map((button, index) => (
            <button
              key={index}
              className="mx-2 mt-8 px-4 text-sm hover:text-white hover:bg-blue-900 hover:bg-opacity-50"
              onClick={() => console.log(`Button ${index + 1} clicked!`)} // replace logic
            >
              <a href={button.url}>
                <img src={button.image} alt={button.label} />
              </a>
              {button.label}
            </button>
          ))}
        </div>
        <div
          className={`flex justify-stretch flex-col ${panl} ${margb} ${margr} ${margt} w-full border border-cus `}
        >
          <div className={`flex flex-row bg-blue-900 border-b mx-1`}>
            <img src="/channels/eolIM.jpeg" className="mx-auto h-8" alt={"alt=\"evergreen online messenger image\""}/>            <p
            className="w-full px-1 text-lg text-white">
          Main Menu
            </p>
            <div className="window-buttons top-0 right-0 w-1/2">
              <div className={"flex justify-end p-1 "}>
                {windowControlButtons.map((button, index) => (
                  <button
                    key={index}
                    onClick={button.onClick}
                    className={`${panl} text-xs pr-1 pl-1 border border-cus`}
                  >
                    {button.label}
                  </button>
                ))}
              </div>
            </div>
          </div>

          <div
            className={`flex flex-col h-full  mr-1  ml-1 mt-1 border-l-2 border-r border-t-2 border-bargray  `}
          >
            <div
              className={`flex flex-row  w-full basis-1/6   ${whi}`}
            >
            </div>
            <div
              className={`flex flex-row basis-5/6 ${whi} ${text} border-b border-bargray border-opacity-60  justify-center h-full`}
            >
              {content}
            </div>
          </div>

          <div className={`flex flex-row ${panl} ${text}  border-b border-black`}>
            <img className="my-1 px-1 self-center" src={"/desktop/inet.png"} alt="Internet Explorer Logo" />
            <p className="text-sm self-center">Done</p>
          </div>
        </div>
      </div>

      <nav
        className={`fixed bottom-0 ${panl} ${text} flex flex-row items-center justify-between w-full h-7 text-lg border-t `}
      >
        <button
          onClick={toggleColors}
          className="mx-2 px-2 shadow-lg border-t-2 border-l-2 border-opacity-75"
        >
          <div className="flex flex-row">
            <img src={"/desktop/win.png"} alt="Windows Logo" />
            <span className="text-sm px-2">Start</span>
          </div>
        </button>
      </nav>
    </div>
  );
};

export default Window;
